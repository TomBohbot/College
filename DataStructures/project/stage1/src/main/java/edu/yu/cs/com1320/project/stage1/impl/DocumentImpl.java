/** "Document Store Implementation" Stage 1
*
* @author Tom Bohbot
* Code to compile: javac -cp src/main/java src/main/java/edu/yu/cs/com1320/project/stage1/impl/DocumentImpl.java
* Code to run:     java -cp target/classes edu.yu.cs.com1320.project.stage1.impl.DocumentImpl
* This Table is currently an accurate map, but have not yet integrated hashing, so it is not a HashMap yet.
* FB Ea have same hashcode - 2236
*/

package edu.yu.cs.com1320.project.stage1.impl;
import edu.yu.cs.com1320.project.stage1.Document;
import edu.yu.cs.com1320.project.impl.HashTableImpl;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

// import org.apache.pdfbox.pdmodel.PDDocument;
// import org.apache.pdfbox.io.IOUtils;
// import java.io.IOException;
// import org.apache.pdfbox.pdmodel.PDDocument;
// import org.apache.pdfbox.pdmodel.PDPage;
// import org.apache.pdfbox.pdmodel.PDPageContentStream;
// import org.apache.pdfbox.pdmodel.font.PDFont;
// import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class DocumentImpl implements Document {

        private URI uri;
        private String txt;
        private byte [] inputStream;
        private int hashCode;
        private HashTableImpl <URI , DocumentImpl> hashTableOfDocs  = new HashTableImpl <URI , DocumentImpl>  ();

    // Constructer for txt files:
    public DocumentImpl (URI uri , String txt , int hashCode) {
        this.uri = uri;
        this.txt = txt;
        this.hashCode = hashCode;
    }

    // Constructor for pdf files:
    public DocumentImpl (URI uri , String txt , int hashCode , byte[] inputStream) {
        this.uri = uri;
        this.txt = txt;
        this.inputStream = inputStream;
        this.hashCode = hashCode;
    }

     /**
     * @return the document as a PDF
     */
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

    @Override
    public String toString() {
        return "DocumentImpl{" +
            "txt='" + txt + '\'' +
            '}';
    }
}
