package edu.yu.intro;

/** " Authors and Books " Assignment 18
*
* @author Tom Bohbot
*/

import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.util.NoSuchElementException;
import java.lang.String;

public class AuthorsAndBooks {
	private Set <String> hashSetISBN = new HashSet<String>();										// To check for duplicate ISBN												
	private Set <String> hashSetAuthors = new HashSet<String>();									// To find total unique authors
	private Set <String> hashSetPublishers = new HashSet<String>();									// To find total unique publishers		
	private HashMap <String , Set<Book>> authorHashMap = new HashMap<String , Set<Book>>();			// To add aut
	private HashMap <String , Set<Book>> publisherHashMap = new HashMap<String , Set<Book>>();
	private HashMap <String , Set<String>> titlesHashMap = new HashMap<String , Set<String>>();		// This line is made to return all the titles of a given author. Have to do this but the librrary closed. 
	private HashMap <String , Set<String>> pubWAuthHashMap = new HashMap<String , Set<String>>();
	private int numberOfBooks;
	private int authorWithMostBooksCounter;
	private String authorWithMostBooks;
	private int publisherWithMostBooksCounter;
	private String publisherWithMostBooks;
	private int publisherWithMostAuthorsCounter;
	private String publisherWithMostAuthors = null;

	public AuthorsAndBooks () {}

	public Book parseLine (String input) {

		Book bookInstance = new Book ("isbn" , "title" , "author" , "yearPublished" , "publisher" );
		String isbn = "Sample";
		String title = "Sample";
		String author = "Sample";
		String yearPublished = "Sample";
		String publisher = "Sample";
		String [] tokens = input.split(";");
		// Remove the ";" marks and the "\"" marks through .replace.
		isbn = tokens[0].replace("\"" , ""); 
		title = tokens[1].replace("\"" , ""); 
		author = tokens[2].replace("\"" , ""); 
		yearPublished = tokens[3].replace("\"" , ""); 
		publisher = tokens[4].replace("\"" , ""); 
		// Final return value.
		bookInstance = new Book (isbn , title , author , yearPublished , publisher);
		return bookInstance;
	}

	public void add (final Book book) {
		if (book == null) {
			throw new IllegalArgumentException("ERROR: This book is null.");
		}
		String bookISBN = book.getISBN();
		if (hashSetISBN.contains(bookISBN) ) {
			throw new IllegalArgumentException("ERROR: Duplicate book.");
		}
		else {
			hashSetISBN.add(bookISBN);
			hashSetAuthors.add(book.getAuthor() );
			hashSetPublishers.add(book.getPublisher() );
			numberOfBooks ++;
			// adding author set to map to find specific set of all books of author later.
			if (authorHashMap.containsKey(book.getAuthor() ) ) {
				Set <Book> newSetOfAuthors = authorHashMap.get(book.getAuthor());
				newSetOfAuthors.add(book);
				authorHashMap.put(book.getAuthor() , newSetOfAuthors);
				if (newSetOfAuthors.size() > authorWithMostBooksCounter) {
					authorWithMostBooksCounter = newSetOfAuthors.size();
					authorWithMostBooks = book.getAuthor();
				}
			}
			else {
				Set <Book> newEntryToMap = new HashSet <Book>();
				newEntryToMap.add(book);
				authorHashMap.put(book.getAuthor() , newEntryToMap);
				if (newEntryToMap.size() > authorWithMostBooksCounter) {
					authorWithMostBooksCounter = newEntryToMap.size();
					authorWithMostBooks = book.getAuthor();
				}
			}
			// adding publisher set to map to find specific set of all books of publisher later.
			if (publisherHashMap.containsKey(book.getPublisher() ) ) {
				Set <Book> newSetOfPublishers = publisherHashMap.get(book.getPublisher());
				newSetOfPublishers.add(book);
				publisherHashMap.put(book.getPublisher() , newSetOfPublishers);
				if (newSetOfPublishers.size() > publisherWithMostBooksCounter) {
					publisherWithMostBooksCounter = newSetOfPublishers.size();
					publisherWithMostBooks = book.getPublisher();
				}
			}
			else {
				Set <Book> newEntryToPublisherMap = new HashSet <Book>();
				newEntryToPublisherMap.add(book);
				publisherHashMap.put(book.getPublisher() , newEntryToPublisherMap);
				if (newEntryToPublisherMap.size() > publisherWithMostBooksCounter) {
					publisherWithMostBooksCounter = newEntryToPublisherMap.size();
					publisherWithMostBooks = book.getPublisher();
				}
			}
			// returning titles of a specific author.
			if (titlesHashMap.containsKey(book.getAuthor() ) ) {
				Set <String> newSetOfTitles = titlesHashMap.get(book.getAuthor());
				newSetOfTitles.add(book.getTitle() );
				titlesHashMap.put(book.getAuthor() , newSetOfTitles);
			}
			else {
				Set <String> newEntryToTitlesMap = new HashSet <String>();
				newEntryToTitlesMap.add(book.getTitle() );
				titlesHashMap.put(book.getAuthor() , newEntryToTitlesMap);
			}
			// returning publisher with most unique authors.
			if (pubWAuthHashMap.containsKey(book.getPublisher() ) ) {
				Set <String> newSetOfPubWAuth = pubWAuthHashMap.get(book.getPublisher());
				newSetOfPubWAuth.add(book.getAuthor() );
				pubWAuthHashMap.put(book.getPublisher() , newSetOfPubWAuth);
				if(newSetOfPubWAuth.size() > publisherWithMostAuthorsCounter) {
					publisherWithMostAuthorsCounter = newSetOfPubWAuth.size();
					publisherWithMostAuthors = book.getPublisher();
				}
			}
			else {
				Set <String> newEntryToPubWAuthMap = new HashSet <String>();
				newEntryToPubWAuthMap.add(book.getAuthor() );
				pubWAuthHashMap.put(book.getPublisher() , newEntryToPubWAuthMap);
				if(newEntryToPubWAuthMap.size() > publisherWithMostAuthorsCounter) {
					publisherWithMostAuthorsCounter = newEntryToPubWAuthMap.size();
					publisherWithMostAuthors = book.getPublisher();
				}
			}
		}
	}	

	public Set<Book> booksByAuthor (final String author) {	
		if (author.isEmpty() || author == null ) {
			throw new IllegalArgumentException("Author must be nonempty.");
		}
		if (authorHashMap.containsKey(author) ) {
			Set <Book> setOfAuthors = (Set <Book>)authorHashMap.get(author);
			Set <Book> unmodifiableSetOfAuthors = Collections.unmodifiableSet(setOfAuthors);
			return unmodifiableSetOfAuthors;
		}
		else {
			Set <Book> emptySetOfAuthors = new HashSet<Book>();
			Set <Book> unmodifiableSetOfEmptyAuthors = Collections.unmodifiableSet(emptySetOfAuthors);
			return unmodifiableSetOfEmptyAuthors;
		}
	}

	public Set<Book> booksByPublisher(final String publisher) {
		if (publisher.isEmpty() || publisher == null) {
			throw new IllegalArgumentException("Publisher must be nonempty.");
		}
		if (publisherHashMap.containsKey(publisher) ) {
			Set <Book> setOfPublishers = (Set <Book>)publisherHashMap.get(publisher);
			Set <Book> unmodifiableSetOfPublishers = Collections.unmodifiableSet(setOfPublishers);
			return unmodifiableSetOfPublishers;
		}
		else {
			Set <Book> emptySetOfPublishers = new HashSet<Book>();
			Set <Book> unmodifiableSetOfEmptyPublishers = Collections.unmodifiableSet(emptySetOfPublishers);
			return unmodifiableSetOfEmptyPublishers;
		}
	}

	public Set<String> allTitles (final String author) {
		try { 
			if (author.isEmpty() || author == null ) {
				throw new IllegalArgumentException("Author must be nonempty.");
			}
		} catch (NullPointerException e) {}

		if (titlesHashMap.containsKey(author) ) {
			Set <String> setOfTitles = (Set <String>)titlesHashMap.get(author);
			// Set <String> unmodifiableSetOfTitles = Collections.unmodifiableSet(setOfTitles);
			return setOfTitles;
		}
		else {
			Set <String> emptySetOfTitles = new HashSet<String>();
			// Set <String> unmodifiableSetOfEmptyTitles = Collections.unmodifiableSet(emptySetOfTitles);
			return emptySetOfTitles;
		}
	}

	public String publisherMostUniqueAuthors () {
		return publisherWithMostAuthors; 
	}

	public int numberOfDistinctBooks () {
		return numberOfBooks;
	}

	public int numberOfDistinctAuthors () {
		return hashSetAuthors.size();
	}

	public int numberOfDistinctPublishers () {
		return hashSetPublishers.size();
	}

	public String authorWithMostBooksMethod () {
		return authorWithMostBooks;
	}

	public String publisherWithMostBooksMethod () {
		return publisherWithMostBooks;
	}

	public static void main (final String [] args) {

		if(args.length != 1) {
			final String msg = "Usage: AuthorsAndBooks name_of_input file"; System.err.println(msg);
			throw new IllegalArgumentException(msg);
		}
		final String inputFile = args [0];
		final File input = new File(inputFile);

		AuthorsAndBooks authorsInstance = new AuthorsAndBooks();
		try {
			final Scanner scanner = new Scanner (input);
			String firstLineOfTxt = "\"ISBN\";\"Book-Title\";\"Book-Author\";\"Year-Of-Publication\";\"Publisher\";\"Image-URL-S\";\"Image-URL-M\";\"Image-URL-L\"";
			// This try catch block is made for the case that the txt file is empty.
			try {
				String firstLineSkip = scanner.nextLine();
			} catch (NoSuchElementException e) {}
			while (scanner.hasNextLine() ) {
				try {
					String nextLine = scanner.nextLine();
					if(nextLine.equals(firstLineOfTxt) || nextLine.isEmpty() ) {
						continue;
					}
					else {
						Book bookOutput = authorsInstance.parseLine(nextLine);
						authorsInstance.add(bookOutput);
					}
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage() );
				}			
			} 
			System.out.println("*************************************************");
			System.out.println("AuthorsAndBooks main - Number of distinct books: " + authorsInstance.numberOfDistinctBooks() );
			System.out.println("AuthorsAndBooks main - Number of distinct authors: " + authorsInstance.numberOfDistinctAuthors() );
			System.out.println("AuthorsAndBooks main - Number of distinct publishers: " + authorsInstance.numberOfDistinctPublishers() );
			System.out.println("AuthorsAndBooks main - Author with the most books: " + authorsInstance.authorWithMostBooksMethod() );
			System.out.println("AuthorsAndBooks main - Publisher with the most books: " + authorsInstance.publisherWithMostBooksMethod() );
			System.out.println("*************************************************");

		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found.");
			System.exit(0);
		}
	}
}
// Final Version :)
// Final Version after adjusting for non empty strings. :)