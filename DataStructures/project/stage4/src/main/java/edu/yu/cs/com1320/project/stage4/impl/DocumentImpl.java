package edu.yu.cs.com1320.project.stage4.impl;

/**
* Stage 4:
* @author Tom Bohbot
*/

import edu.yu.cs.com1320.project.stage4.Document;
import edu.yu.cs.com1320.project.impl.HashTableImpl;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Objects;

public class DocumentImpl implements Document {

        private URI uri;
        private String txt;
        private byte [] inputStream;
        private int hashCode;
        private HashTableImpl <URI , DocumentImpl> hashTableOfDocs  = new HashTableImpl <URI , DocumentImpl>  ();  // doing stage 3 rn and have no idea why I made this in stage 1 honestly... :/
        private HashMap<String, Integer> howManyTimesAWordAppears = new HashMap<String, Integer>();
        private long timeSinceDocWasUsed;


    // Constructor for txt files:
    public DocumentImpl (URI uri , String txt , int hashCode) {
        if (txt == null || uri == null) {
            throw new IllegalArgumentException();
        }
        this.uri = uri;
        this.txt = txt;
        this.hashCode = hashCode;
        fillHashTable(txt);
    }

    // Constructor for pdf files:
    public DocumentImpl (URI uri , String txt , int hashCode , byte[] inputStream) {
        if (txt == null || uri == null) {
            throw new IllegalArgumentException();
        }
        this.uri = uri;
        this.txt = txt;
        this.inputStream = inputStream;
        this.hashCode = hashCode;
        fillHashTable(txt);
    }

    private void fillHashTable(String txt) {
        String parsedDocContent = parseSpecialCharacters(txt);
        String [] allWordsInDoc = parsedDocContent.split(" ");
        for (int i = 0; i < allWordsInDoc.length; i ++) {
            Integer wordDups = 0;
            if (howManyTimesAWordAppears.get(allWordsInDoc[i]) == null) {
                wordDups = 1;
            }
            else {
                wordDups = howManyTimesAWordAppears.get(allWordsInDoc[i]) + 1;
            }
            howManyTimesAWordAppears.put(allWordsInDoc[i] , wordDups);
        }
    }

    @Override
    public byte[] getDocumentAsPdf() {

        if (hashTableOfDocs == null) { return null; }
        PDDocument doc = new PDDocument();
        String dataOfFile = this.txt;
        PDPage page = new PDPage();
        PDFont font = PDType1Font.HELVETICA;
        doc.addPage(page);
        try {
            PDPageContentStream data = new PDPageContentStream(doc , page);
            data.beginText();
            data.setFont(font , 12);
            data.newLineAtOffset(100, 700);
            data.showText(dataOfFile);
            data.endText();
            ByteArrayOutputStream bytes = new ByteArrayOutputStream ();
            data.close();
            doc.save(bytes);
            doc.close();

            byte [] returnValue = bytes.toByteArray();
            return returnValue;

        } catch (IOException e) {}
        return null;
    }

    /**
     * @return the document as a Plain String
     */
    @Override
    public String getDocumentAsTxt() {
        return txt;
    }

    /**
     * @return hash code of the plain text version of the document
     */
    @Override
    public int getDocumentTextHashCode() {
        return hashCode;
    }

    /**
     * @return URI which uniquely identifies this document
     */
    @Override
    public URI getKey() {
        return uri;
    }


    // Override methods:
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentImpl document = (DocumentImpl) o;
        return txt.equals(document.txt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(txt);
    }

    /**
     * how many times does the given word appear in the document?
     * @param word
     * @return the number of times the given words appears in the document
     */
    @Override
    public int wordCount(String word) {
        if (word == null) {return 0; }
        word = parseSpecialCharacters(word);
        return howManyTimesAWordAppears.get(word);
    }

    private String parseSpecialCharacters (String str) {
		str = str.toLowerCase();
		String parsedString = new String ();
		for (char seekRegChar: str.toCharArray() ) {
			int AsciiVal = (int) seekRegChar;
			if (AsciiVal >= 48 && AsciiVal <= 57 || AsciiVal >= 97 && AsciiVal <= 122 || AsciiVal == ' ') {
				parsedString = parsedString + seekRegChar;
			} 
		}
		return parsedString;
    }

    @Override
    public long getLastUseTime() {
        return timeSinceDocWasUsed;
    }

    @Override
    public void setLastUseTime(long timeInMilliseconds) {
        this.timeSinceDocWasUsed = timeInMilliseconds;
    }

    @Override
    public int compareTo(Document o) {
        // TODO Auto-generated method stub
        return 0;
    }
}
