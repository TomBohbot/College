# Necessary Imports:
import pandas as pd
import numpy as np
import re
from tqdm import tqdm
import nltk
from nltk.tokenize import word_tokenize
nltk.download('punkt')
import pickle
import os

from keras.preprocessing.text import Tokenizer
from keras.preprocessing.sequence import pad_sequences
from keras.models import Sequential
from keras.layers import Embedding,LSTM,Dense,SpatialDropout1D, Dropout, embeddings
from keras.initializers import Constant
from keras import backend as K
from sklearn.model_selection import train_test_split
from tensorflow.keras.optimizers import Adam
from xgboost import XGBRegressor

def get_data():
    return  pd.read_csv('train.csv')

def remove_url(string):
  if string is None or type(string) == float:
    return ""
  url = re.compile(r'https?://\S+|www\.\S+')
  return url.sub(r'',string)

def remove_non_letters(string):
  if string is None or type(string) == float:
    return ""
  return re.sub(r'[\W_]+', ' ', string)

def transform_data(df):
    df['text']=df['text'].apply(lambda x : remove_url(x))
    df['text']=df['text'].apply(lambda x : remove_non_letters(x))
    df['text']=df['text'].apply(lambda x : x.lower())
    return df

def get_corpus(df):
  corpus=[]
  for text in tqdm(df['text']):
    words=[word for word in word_tokenize(text)]
    corpus.append(words)
  return corpus   

def get_pad(corpus):
  tokenizer = Tokenizer()
  tokenizer.fit_on_texts(corpus)
  sequences = tokenizer.texts_to_sequences(corpus)
  return pad_sequences(
    sequences,
    maxlen=50,
    truncating='post',
    padding='post'
  ), tokenizer

def get_embeddings():
  embeddings = {}
  with open('glove.6B.50d.txt','r') as glove_file:
    for line in glove_file:
      words = line.split()
      vectors = np.asarray(words[1:],'float32')
      embeddings[words[0]] = vectors
  glove_file.close()
  return embeddings

def get_embedding_object(tokenizer, embeddings):
    words = tokenizer.word_index
    num_words = len(words)+1
    matrix = np.zeros((num_words,50)) # initialize the matrix that connects embeddings to corpus
    for word,i in tqdm(words.items()):
        if i <= num_words:
            matrix[i] = embeddings.get(word) if embeddings.get(word) is not None else matrix[i]

    return Embedding(
    num_words,
    50,
    embeddings_initializer=Constant(matrix),
    input_length=50,
    trainable=False
    )

def recall_m(y_true, y_pred):
    true_positives = K.sum(K.round(K.clip(y_true * y_pred, 0, 1)))
    possible_positives = K.sum(K.round(K.clip(y_true, 0, 1)))
    recall = true_positives / (possible_positives + K.epsilon())
    return recall

def precision_m(y_true, y_pred):
    true_positives = K.sum(K.round(K.clip(y_true * y_pred, 0, 1)))
    predicted_positives = K.sum(K.round(K.clip(y_pred, 0, 1)))
    precision = true_positives / (predicted_positives + K.epsilon())
    return precision

def f1_m(y_true, y_pred):
    precision = precision_m(y_true, y_pred)
    recall = recall_m(y_true, y_pred)
    return 2*((precision*recall)/(precision+recall+K.epsilon()))

def keras_model(df, embedding, pad):
    # Form model:
    model=Sequential()
    model.add(embedding)
    model.add(SpatialDropout1D(0.2))
    model.add(LSTM(64, dropout=0.2, recurrent_dropout=0.2))
    model.add(Dense(1, activation='sigmoid'))
    # Compile Model:
    model.compile(
        loss='binary_crossentropy',
        optimizer=Adam(learning_rate=1e-5),
        metrics=['accuracy', f1_m]
    )
    # Train/Test Split:
    train = pad[:df.shape[0]]
    test = pad[df.shape[0]:]
    print(train)
    X_train, X_test, y_train, y_test = train_test_split(
        train, 
        df['target'].values, 
        test_size=0.15
    )
    # Fit Model:
    history=model.fit(
        X_train,
        y_train,
        batch_size=16,
        epochs=10,
        validation_data=(
            X_test,
            y_test
        ),
        verbose=2
    )
    # Save Model:
    model.save('keras_model')

def xg_boost_model(df, pad):
    train = pad[:df.shape[0]]
    test = pad[df.shape[0]:]
    X_train, X_test, y_train, y_test = train_test_split(
        train, 
        df['target'].values, 
        test_size=0.15
    )
    model = XGBRegressor()
    model.fit(X_train, y_train)    
    # save the model to be reused in future:
    pickle.dump(model, open('xgb_model.txt', 'wb') )
    return model

def predict_tweet(df=None):
    model_df = get_data()
    model_df = transform_data(df=model_df)
    df = transform_data(df=df)
    corpus = get_corpus(df=model_df)
    pad, tokenizer = get_pad(corpus=corpus)
    embeddings_dict = get_embeddings()
    embedding = get_embedding_object(tokenizer=tokenizer, embeddings=embeddings_dict)
    try: 
        path = str(os.path.join(os.path.dirname(os.path.abspath(__file__) ), 'xgb_model.txt'))
        model = pickle.load(open(path, 'rb'))
        # new_model = tf.keras.models.load_model('saved_model/my_model')
    except:
        print("Must Generate New Model:")
        model = xg_boost_model(df=model_df, pad=pad)
        # model = keras_model(df=model_df, embedding=embedding, pad=pad)
    return model.predict(pad[:df.shape[0]])