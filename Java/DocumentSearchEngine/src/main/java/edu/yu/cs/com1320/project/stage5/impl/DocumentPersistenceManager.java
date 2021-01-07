package edu.yu.cs.com1320.project.stage5.impl;

import edu.yu.cs.com1320.project.stage5.Document;
import edu.yu.cs.com1320.project.stage5.PersistenceManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import edu.yu.cs.com1320.project.impl.BTreeImpl;
import edu.yu.cs.com1320.project.stage5.impl.DocumentImpl;

/**
 * created by the document store and given to the BTree via a call to
 * BTree.setPersistenceManager
 * THINGS STILL DO:
 *   1) fix the directory structure
 *   2) 
 * 
 */
public class DocumentPersistenceManager implements PersistenceManager<URI, Document> {

    private HashMap <URI, JsonElement> bTree = new HashMap<URI, JsonElement>();
    private HashMap <URI , String> uriToFilePath = new HashMap <URI , String>();
    private HashSet <File> files = new HashSet <File> ();
    private File baseDir;

    public DocumentPersistenceManager(File baseDir) {
        if (baseDir == null) {
            File newBaseDir = new File (System.getProperty("user.dir") );
            baseDir = newBaseDir;
        }
        else {
            this.baseDir = baseDir;
        }
    }

    @Override
    public void serialize(URI uri, Document val) throws IOException {
        JsonElement elem = lambda.serialize(val, val.getClass(), context);
        try {
            URI genericUri = makeFileGeneric(uri);
            String filePath = uri.toString();
            moveToDisk(elem , genericUri);
            bTree.put(genericUri, elem);
        } catch (URISyntaxException e) { }

    }

    private URI makeFileGeneric (URI uri) throws URISyntaxException {
        String uriAsString = uri.toString();
		String newUri = "";
		for (char letter: uriAsString.toCharArray() ) {
			String letterAsString = Character.toString(letter);
            if (letterAsString.equals(File.separator) ) { 
                letterAsString = File.separator; 
            }
			newUri = newUri + letterAsString;
        }
        URI returnUri = new URI (newUri);
		return returnUri;
	}

    @Override
    public DocumentImpl deserialize(URI uri) throws IOException {
        if (bTree.get(uri) == null) {
            return null;
        }
        JsonElement serializedDoc = bTree.get(uri);
        DocumentImpl doc = (DocumentImpl) desearlizer.deserialize(serializedDoc, DocumentImpl.class, deserializationContext);
        deleteFromDisk(uri);
        bTree.put(uri, null);
        return doc;
    }

    private void moveToDisk (JsonElement json , URI uri) throws IOException {
        String txt = json.toString();
        String uriAsString = uri.toString();
        uriAsString = uriAsString.substring(uriAsString.indexOf("//")+1) + ".json";
        if (baseDir == null) {
            String filePath = System.getProperty("user.dir") + uriAsString;
            File mkDirs = new File (baseDir , uriAsString);
            mkDirs.getParentFile().mkdirs();
            FileWriter file = new FileWriter(mkDirs);
            file.write(txt);
            file.close();
            uriToFilePath.put(uri , filePath);
        }
        else {
            String baseDirAsString = baseDir.toString();
            String filePath = baseDirAsString + uriAsString;
            File mkDirs = new File (baseDir , uriAsString);
            mkDirs.getParentFile().mkdirs();
            FileWriter file = new FileWriter(mkDirs);
            file.write(txt);
            file.close();
            uriToFilePath.put(uri , filePath);
        }
    }

    private void deleteFromDisk (URI uri) {
        if (uriToFilePath.get(uri) == null ) {
            return;
        }
        String filePath = uriToFilePath.get(uri);
        File file = new File (filePath);
        // file.delete();
        deleteEmptyDirectory(filePath);
    }

    private void deleteEmptyDirectory (String filePath) {
        File file = new File (filePath);
        if (file.delete() == false) {
            return;
        }
        String newFilePath = parseOneSlash(filePath);
        deleteEmptyDirectory(newFilePath);
    }

    private String parseOneSlash (String uri) {
		int countSlashes = 0;
		for (char letter: uri.toCharArray() ) {
			String letterAsString = Character.toString(letter);
			if (letterAsString.equals(File.separator) ) { countSlashes ++; }
		}
		int deleteSlash = countSlashes;
		int countSlashesAgain = 0;
		String newUri = "";
		for (char letter: uri.toCharArray() ) {
			String letterAsString = Character.toString(letter);
			if (letterAsString.equals(File.separator) ) { countSlashesAgain ++; }
			if (countSlashesAgain == deleteSlash) { break; }
			newUri = newUri + letterAsString;
		}
		return newUri;
	}

    JsonSerializer lambda = new JsonSerializer<DocumentImpl>() {
        @Override
        public JsonElement serialize(DocumentImpl src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject json = new JsonObject();
            json.addProperty("text", src.getDocumentAsTxt());
            json.addProperty("uri", src.getKey().toString());
            json.addProperty("hashcode", src.getDocumentTextHashCode());
            json.addProperty("wordcount", src.getWordMap().toString() );
            // json.addProperty("wordcount", parseMapToString(src.getWordMap()));
            return json;
        }
    };

    JsonSerializationContext context = new JsonSerializationContext() {
        @Override
        public JsonElement serialize(Object src) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public JsonElement serialize(Object src, Type typeOfSrc) {
            // TODO Auto-generated method stub
            return null;
        }
    };

    JsonDeserializationContext deserializationContext = new JsonDeserializationContext () {

        @Override
        public <T> T deserialize(JsonElement json, Type typeOfT) throws JsonParseException {
            // TODO Auto-generated method stub
            return null;
        }

    };

    JsonDeserializer desearlizer = new JsonDeserializer() {
        @Override
        public DocumentImpl deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            DocumentImpl doc;
            try {
                String docText = json.getAsJsonObject().get("text").getAsString();
                String uriString = json.getAsJsonObject().get("uri").getAsString();
                URI uri = new URI(uriString);
                int hashCode = json.getAsJsonObject().get("hashcode").getAsInt();
                String map = json.getAsJsonObject().get("wordcount").getAsString();
                doc = new DocumentImpl (uri, docText, hashCode , "already has wordmap");
                HashMap wordMap = tokenizeMap (map);
                doc.setWordMap((Map) wordMap);
                return doc;
            } catch (URISyntaxException e) { e.printStackTrace(); }
            return null;
        }
    };

    private HashMap tokenizeMap (String map) {
		HashMap <String , Integer> hashMap = new HashMap <String , Integer> ();
		String newMap = "";
		for (char parser: map.toCharArray() ) {
			if (parser == '}' || parser == '{' ||  parser == ',') {
				continue;
			}
			if (parser == '=') {
				newMap = newMap + ' ';
				continue;
			}
			newMap = newMap + parser;
		}        
		String [] splitMap = newMap.split(" ");
		for (int i = 0; i < splitMap.length; i = i + 2) {
            int newInt = Integer.parseInt(splitMap[1 + i]);
			hashMap.put(splitMap[i] , newInt);
        }
        return hashMap;
    }
}
