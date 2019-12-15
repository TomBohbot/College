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