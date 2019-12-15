package edu.yu.intro.test;

/** " FindingRationalNumbersTest " Assignment #8
*
* @author Tom Bohbot
*/

import java.util.*;
import edu.yu.intro.AuthorsAndBooks;
import edu.yu.intro.Book;
import org.junit.*;
import static org.junit.Assert.*;


public class AuthorsAndBooksTest {

	AuthorsAndBooks authorsInstance = new AuthorsAndBooks();
	Book bookInstance = authorsInstance.parseLine("\"ISBN\";\"Book-Title\";\"Book-Author\";\"Year-Of-Publication\";\"Publisher\";\"Image-URL-S\";\"Image-URL-M\";\"Image-URL-L\" ");

	@Test
	public void parseLineMethod() {
		assertTrue ("testing ISBN of book." , "ISBN".equals(bookInstance.getISBN() ) );
		assertTrue ("testing Title of book." , "Book-Title".equals(bookInstance.getTitle() ) );
		assertTrue ("testing Author of book." , "Book-Author".equals(bookInstance.getAuthor() ) );
		assertTrue ("testing Year of Pub. of book." , "Year-Of-Publication".equals(bookInstance.getYearPublished() ) );
		assertTrue ("testing Publisher of book." , "Publisher".equals(bookInstance.getPublisher() ) );
	}
	@Test 
	public void addMethod() {
		authorsInstance.add(bookInstance);
		assertEquals("testing if unique number of books is correct." , 1 , authorsInstance.numberOfDistinctBooks() );
	}
	@Test (expected = IllegalArgumentException.class)
	public void addMethodNullError() {
		Book nullBook = null;
		authorsInstance.add(nullBook);
	}
	@Test (expected = IllegalArgumentException.class)
	public void addMethodDuplicateError() {
		Book bookInstanceCopy = authorsInstance.parseLine("\"ISBN\";\"Book-Title\";\"Book-Author\";\"Year-Of-Publication\";\"Publisher\";\"Image-URL-S\";\"Image-URL-M\";\"Image-URL-L\" ");
		authorsInstance.add(bookInstance);
		authorsInstance.add(bookInstanceCopy);
	}
	@Test 
	public void countNumberOfBooks() {
		Book bookInstance1 = authorsInstance.parseLine("\"ISBN\";\"Book-Title\";\"Book-Author\";\"Year-Of-Publication\";\"Publisher\";\"Image-URL-S\";\"Image-URL-M\";\"Image-URL-L\" ");
		Book bookInstance2 = authorsInstance.parseLine("\"ISBN1\";\"Book-Title\";\"Book-Author\";\"Year-Of-Publication\";\"Publisher\";\"Image-URL-S\";\"Image-URL-M\";\"Image-URL-L\" ");
		Book bookInstance3 = authorsInstance.parseLine("\"ISBN2\";\"Book-Title\";\"Book-Author\";\"Year-Of-Publication\";\"Publisher\";\"Image-URL-S\";\"Image-URL-M\";\"Image-URL-L\" ");
		authorsInstance.add(bookInstance1);
		authorsInstance.add(bookInstance2);
		authorsInstance.add(bookInstance3);
		assertEquals("testing if unique number of books is correct." , 3 , authorsInstance.numberOfDistinctBooks() );
	}
	@Test 
	public void booksByAuthorMethodTest() {
		Book bookInstance1 = authorsInstance.parseLine("\"ISBN\";\"Book-Title\";\"Book-Author\";\"Year-Of-Publication\";\"Publisher\";\"Image-URL-S\";\"Image-URL-M\";\"Image-URL-L\" ");
		Book bookInstance2 = authorsInstance.parseLine("\"ISBN1\";\"Book-Title\";\"Book-Authos\";\"Year-Of-Publication\";\"Publisher\";\"Image-URL-S\";\"Image-URL-M\";\"Image-URL-L\" ");
		Book bookInstance3 = authorsInstance.parseLine("\"ISBN2\";\"Book-Title\";\"Book-Author\";\"Year-Of-Publication\";\"Publisher\";\"Image-URL-S\";\"Image-URL-M\";\"Image-URL-L\" ");
		authorsInstance.add(bookInstance1);
		authorsInstance.add(bookInstance2);
		authorsInstance.add(bookInstance3);
		Set <Book> booksByAuthorSet = authorsInstance.booksByAuthor("Book-Author");
		int lengthofBooksByAuthor = booksByAuthorSet.size();
		assertEquals("testing if set of authors is correct." , 2 , lengthofBooksByAuthor );
	}


}