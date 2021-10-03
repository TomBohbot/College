# DSA Homework 2 -- HR Analytics Job Change of Data Scientists:

The purpose of this assignment is to determine the likelyhood that a data scientist will leave their current job. For this assignment we can imagine that we were hired by a company that is actively recruiting for data scientists. This project will give useful information to recruiters so that they can better determine if they should attempt to poach a person from company X to their company. The recruiter will have information that may seem irrelevant about a person and we will make machine learning models to find patterns between that many people they provide and the information provided about them.

# What Metrics Will Be Tracked:
The metrics that will be tracked in this project is the accuracy of our models, the recall, and the precision of each model.

# Deliverables to the Recruiting Team and/or Client:
The recruiters currently have a spreadsheet of thousands of potential data scientists. The deliverable is to indicate which of these data scientists are likely to move to another company. This will save much time for recruiters as they will not have to go through the rejections of people who would not like to leave their job.

# What Is Success Here:
Success here is to build a model that is as accurate as possible while being able to label people who are seeking jobs with high precision. Since there are so many more people that are content with their jobs and do not want to leave their company it is easy to have high accuracy since predicting that everyone will not want to leave their job will give around 75% accuracy as only 25% of data scientist want to leave their job. Therefore maintaining a high precision and recall along with the accuracy is very important as this ensures that the machine learning model is also predicting people who do want to leave their job as well. This is important as recruiters are only interested in people who want to leave their job, so precision towards the 1.0 target is ideal as this is exactly what the recruiters are seeking. 

# What Are Some Trade-Offs to Discuss:
Initially I wanted to optimize for accuracy as I find this very important in any model. However, since the data is imbalanced as noted above, I believe it is important to optimize for precision and recall even if accuracy dips a bit as it is important to be able to correctly identify the people that want to leave their job as it may is a minority and less common. Another notable trade-off is how much data is in the model. Since the model has many more people that want to stay in their job I decided to make the data set larger to produce a more balanced dataset. Although it increases the size of the data set it can lead to higher precision as it allows the machine learning model to see more people that want to leave their job and in turn can learn more fromt them to be more accurate.

# How to Run The Python Files:
To observe the Exploratory Data Analysis (EDA) you can open the .ipynb file called week_02_eda.ipynb 

To run the models:
```bash
python scripts/Models.py
```

The other files are helper files to Models.py to better modularize the project and have reusable code for future projects.

# Analysis:

The two machine learning models used in this repo are logistic regression and a random forest model. When beginning the project much of the data was missing so I dedicated a python file to clean the data set called CleanData.py. This file is not reusable for other projects as it is case specific to the columns and to how they should imputed. To fill the missing values an imputer technique called Multiple Imputation by Chained Equations (MICE) was utilized as this is better then a simple mean imputer. After imputing the data set and analyzing the importance of each feature I decided to only remove the enrollee_id as it is not standard to  Following cleaning the data, that data set was still imbalanced. The models were run on both a balanced and imbalanced data set which can be seen below.
have unique indentifiers in a machine learning model. After testing the other features I ultimately decided to use all the other features in both machine learning models which resulted in good metrics.

Please see the metrics of each model before and after balancing the data set directlty below:

```bash
Logistic Regression Metrics Before Balancing Data:
              precision    recall  f1-score   support

         0.0       0.78      0.95      0.85      2829
         1.0       0.62      0.24      0.35      1003

    accuracy                           0.76      3832
   macro avg       0.70      0.59      0.60      3832
weighted avg       0.74      0.76      0.72      3832

Random Foreset Metrics Before Balancing Data:
              precision    recall  f1-score   support

         0.0       0.86      0.94      0.90      2829
         1.0       0.76      0.57      0.65      1003

    accuracy                           0.84      3832
   macro avg       0.81      0.75      0.77      3832
weighted avg       0.83      0.84      0.83      3832


Logistic Regression Metrics After Balancing Data:
              precision    recall  f1-score   support

         0.0       0.72      0.71      0.71      2868
         1.0       0.71      0.73      0.72      2885

    accuracy                           0.72      5753
   macro avg       0.72      0.72      0.72      5753
weighted avg       0.72      0.72      0.72      5753

Random Foreset Metrics After Balancing Data:
              precision    recall  f1-score   support

         0.0       0.87      0.87      0.87      2868
         1.0       0.87      0.87      0.87      2885

    accuracy                           0.87      5753
   macro avg       0.87      0.87      0.87      5753
weighted avg       0.87      0.87      0.87      5753
```

Analysis of metrics:
Before Balancing the data we can clearly see that the random forest model is superior. Not suprisingly, both models have a high precision and recall pre-balancing for the 0.0 target. This is to be expected as there is many more of this and the model does not have to very well crafted to constantly predict 0.0 if there are dominantly this in the data set. I believe that things get more interesting when the data set is balanced. We can see that the precision and recall dip for the 0.0 target but drastically rise for the 1.0 or seeking job target value. This shows us that the model is now better apt to predict if a person is likely to leave their job. The goal of this project is not to identify people who don't want to leave their job, but to identify people that do want to leave their job as the recruiters are only intested in people willing to leave their job. I chose to use the latter model, specifically the Random Forest as it had the highest accuracy, recall, and precision out of all the models. 

Analysis of the difference between machine learning models:
To repeat what was stated above.. the file to see the differences between machine learning models is called Compare_Models.csv and can be found under the directory called data. The path to this file is 
``` bash
data/CompareModels.csv
```
Here we can see that the random forest model is dominantly correct which in not suprising as it is the superior model. However, something interesting that can come out of this model is that the logistic regression model (when it is correct) tends to be correct when it is identifying people who are not seeking a new job. This is intersting and very useful because it means that the random forest is at times identifying that people are seeking a new job even though they are not which in turn wastes the recruiters time. An interesting idea that can be implemented is to stack the logistic regression model on top of the random forest and to use it when the random forest believes a data scientist is seeking a new job with low confidence and the logistic regression believes they are content at their current job with high confidence. 

# Reusability:
The models.py file can be reused for future projects as it contains functions that take already cleaned data and returns the model in case future testing is desired and the predictions it made to be able to implement code to retrieve metrics on the model. Another file that can be reusable is the GetData.py file as it is there to train/test split any file that has 'target' as the name of the target value which is standard, balance and data set or not depending on arguements passed in paramaters, retrieve metrics of any model that has an array of its predictions and an array in same order of the actual output, and return a data set of differences between any logistic regression and random forest models.
