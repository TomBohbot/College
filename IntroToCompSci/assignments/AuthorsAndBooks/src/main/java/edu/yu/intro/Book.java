package edu.yu.intro;

/** " Authors and Books " Assignment 18
*
* @author Tom Bohbot
*/

import java.util.Scanner;
import java.io.*;
import java.util.NoSuchElementException;

public class Book {

	private String isbn;
	private String title;
	private String author;
	private String yearPublished;
	private String publisher;

	public Book(final String isbn , final String title , final String author , final String yearPublished , final String publisher ) {

		if (isbn.isEmpty() || title.isEmpty() || author.isEmpty() || yearPublished.isEmpty() || publisher.isEmpty() ) {
			throw new IllegalArgumentException("None of these paramters can be empty.");
		}
		if (isbn == null || title == null || author == null || yearPublished == null || publisher == null ) {
			throw new IllegalArgumentException("None of these paramters can be null.");
		}

		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.yearPublished = yearPublished;
		this.publisher = publisher;
	}
	public String getISBN () {
		return isbn;
	}
	public String getAuthor() {
		return author;
	}
	public String getTitle() {
		return title;
	}
	public String getYearPublished() {
		return yearPublished;
	}
	public String getPublisher() {
		return publisher;
	}

}
// Final Version :)
// Added if statement to exclude emoty and null cases. :)