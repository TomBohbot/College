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
	Book bookInstance = authorsInstance.parseLine("\"ISBN1\";\"Book-Title1\";\"Book-Author1\";\"Year-Of-Publication1\";\"Publisher1\";\"Image-URL-S\";\"Image-URL-M\";\"Image-URL-L\" ");

	@Test
	public void parseLineMethod() {
		assertTrue ("testing ISBN of book." , "ISBN1".equals(bookInstance.getISBN() ) );
		assertTrue ("testing Title of book." , "Book-Title1".equals(bookInstance.getTitle() ) );
		assertTrue ("testing Author of book." , "Book-Author1".equals(bookInstance.getAuthor() ) );
		assertTrue ("testing Year of Pub. of book." , "Year-Of-Publication1".equals(bookInstance.getYearPublished() ) );
		assertTrue ("testing Publisher of book." , "Publisher1".equals(bookInstance.getPublisher() ) );
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
		Book bookInstanceCopy = authorsInstance.parseLine("\"ISBN1\";\"Book-Title1\";\"Book-Author1\";\"Year-Of-Publication1\";\"Publisher1\";\"Image-URL-S\";\"Image-URL-M\";\"Image-URL-L\" ");
		authorsInstance.add(bookInstance);
		authorsInstance.add(bookInstanceCopy);
	}
	@Test 
	public void countNumberOfBooks() {
		Book book1 = new Book ("ISBN1", "Book-Title" ,"Book-Author" ,"Year-Of-Publication" ,"Publisher");
		Book book2 = new Book ("ISBN2", "Book-Title" ,"Book-Athor" ,"Year-Of-Publication" ,"Publsher");
		Book book3 = new Book ("ISBN3", "Book-Title" ,"Book-Author" ,"Year-Of-Publication" ,"Publisher");
		authorsInstance.add(book1);
		authorsInstance.add(book2);
		authorsInstance.add(book3);
		assertEquals("testing if unique number of books is correct." , 3 , authorsInstance.numberOfDistinctBooks() );
	}
	@Test 
	public void booksByAuthorMethodTest() {
		Book book1 = new Book ("ISBN1", "Book-Title" ,"Book-Author" ,"Year-Of-Publication" ,"Publisher");
		Book book2 = new Book ("ISBN2", "Book-Title" ,"Book-Athor" ,"Year-Of-Publication" ,"Publsher");
		Book book3 = new Book ("ISBN3", "Book-Title" ,"Book-Author" ,"Year-Of-Publication" ,"Publisher");
		authorsInstance.add(book1);
		authorsInstance.add(book2);
		authorsInstance.add(book3);
		Set <Book> booksByAuthorSet = authorsInstance.booksByAuthor("Book-Author");
		int lengthofBooksByAuthor = booksByAuthorSet.size();
		assertEquals("testing if set of authors is correct." , 2 , lengthofBooksByAuthor );
	}
	@Test 
	public void booksByAuthorMethodEmptySet() {
		Set <Book> booksByAuthorSet = authorsInstance.booksByAuthor("Book-Author");
		int lengthofBooksByAuthor = booksByAuthorSet.size();
		assertEquals("testing if author does not exist in hashMap." , 0 , lengthofBooksByAuthor );
	}
	@Test 
	public void booksByPublisherMethodTest() {
		Book book1 = new Book ("ISBN1", "Book-Title" ,"Book-Author" ,"Year-Of-Publication" ,"Publisher");
		Book book2 = new Book ("ISBN2", "Book-Title" ,"Book-Athor" ,"Year-Of-Publication" ,"Publsher");
		Book book3 = new Book ("ISBN3", "Book-Title" ,"Book-Author" ,"Year-Of-Publication" ,"Publisher");
		authorsInstance.add(book1);
		authorsInstance.add(book2);
		authorsInstance.add(book3);
		Set <Book> booksByPublisherSet = authorsInstance.booksByPublisher("Publisher");
		int lengthofBooksByPublisher = booksByPublisherSet.size();
		assertEquals("testing if set of publishers is correct." , 2 , lengthofBooksByPublisher );
	}
	@Test 
	public void booksByPublisherMethodEmptySet() {
		Set <Book> booksByPublisherSet = authorsInstance.booksByPublisher("Publisher");
		int lengthofBooksByPublisher = booksByPublisherSet.size();
		assertEquals("testing if set of publishers is correct when the publisher does not exist." , 0 , lengthofBooksByPublisher );
	}
	@Test
	public void allTitlesMethodTest() {
		Book book1 = new Book ("ISBN1", "Book-Title1" ,"Book-Author" ,"Year-Of-Publication" ,"Publisher");
		Book book2 = new Book ("ISBN2", "Book-Title2" ,"Book-Athor" ,"Year-Of-Publication" ,"Publsher");
		Book book3 = new Book ("ISBN3", "Book-Title3" ,"Book-Author" ,"Year-Of-Publication" ,"Publisher");
		authorsInstance.add(book1);
		authorsInstance.add(book2);
		authorsInstance.add(book3);
		Set <String> booksByAuthorGetTitlesSet = authorsInstance.allTitles("Book-Author");
		int lengthofBooksByAuthorGetTitle = booksByAuthorGetTitlesSet.size();
		assertEquals("testing if set of titles by author is correct." , 2 , lengthofBooksByAuthorGetTitle );
	}
	@Test  
	public void allTitlesMethodTestEmptySet() {
		Set <String> booksByAuthorGetTitlesSet = authorsInstance.allTitles("Book-Author");
		int lengthofBooksByAuthorGetTitle = booksByAuthorGetTitlesSet.size();
		assertEquals("testing if set of titles by author is correct when author does not exist." , 0 , lengthofBooksByAuthorGetTitle );
	}
	@Test 
	public void PublisherWithMostUniqueAuthorsMethod() {
		Book book1 = new Book ("ISBN1", "Book-Title" ,"Author1" ,"Year-Of-Publication" ,"PublisherMost");
		Book book2 = new Book ("ISBN2", "Book-Title" ,"Author2" ,"Year-Of-Publication" ,"Publsher");
		Book book3 = new Book ("ISBN3", "Book-Title" ,"Author2" ,"Year-Of-Publication" ,"Publisher");
		Book book4 = new Book ("ISBN4", "Book-Title" ,"Author2" ,"Year-Of-Publication" ,"Publisher");
		Book book5 = new Book ("ISBN5", "Book-Title" ,"Author4" ,"Year-Of-Publication" ,"PublisherMost");
		authorsInstance.add(book1);
		authorsInstance.add(book2);
		authorsInstance.add(book3);
		authorsInstance.add(book4);
		authorsInstance.add(book5);
		String publisherWMostUniqAuthors = authorsInstance.publisherMostUniqueAuthors();
		assertTrue("testing publisher with most unique authors." , "PublisherMost".equals(publisherWMostUniqAuthors) );
	}
}
// Final Version :)