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
	private Set <Book> hashSet = new HashSet<Book>();												// Don't know why I have this
	private Set <String> hashSetISBN = new HashSet<String>();										// To check for duplicate ISBN												
	private Set <String> hashSetAuthors = new HashSet<String>();									// To find total unique authors
	private Set <String> hashSetPublishers= new HashSet<String>();									// To find total unique publishers		
	private HashMap <String , Set<Book>> authorHashMap = new HashMap<String , Set<Book>>();			// To add aut
	private HashMap <String , Set<Book>> publisherHashMap = new HashMap<String , Set<Book>>();
	private HashMap <String , Set<String>> titlesHashMap = new HashMap<String , Set<String>>();		// This line is made to return all the titles of a given author. Have to do this but the librrary closed. 
	private HashMap <String , Set<String>> pubWAuthHashMap = new HashMap<String , Set<String>>();
	private int numberOfBooks;
	private int authorWithMostBooksCounter;
	private String authorWithMostBooks;
	private ArrayList <String> arrayListAuthor = new ArrayList <String>();							// To find out who is most frequent as author.
	private ArrayList <String> arrayListPublisher = new ArrayList <String>();						// To find out who is most frequent as publisher.
	private int publisherWithMostBooksCounter;
	private String publisherWithMostBooks;
	private int publisherWithMostAuthorsCounter;
	private String publisherWithMostAuthors;
	public AuthorsAndBooks () {}

	public Book parseLine (String input) {
	/** TODO LIST:
	 *	1) Get rid of quote marks. DONE
	 *  2) If a string is leading with a space bar I should count for that. DONE
	 *  3) Get rid of the first line of text. DONE
	 */

		Book bookInstance = new Book ("isbn" , "title" , "author" , "yearPublished" , "publisher" );
		String isbn = "Sample";
		String title = "Sample";
		String author = "Sample";
		String yearPublished = "Sample";
		String publisher = "Sample";
		String urlS = "Sample";
		String urlM = "Sample";
		String urlL = "Sample";
		//for loop made to remove the ";" marks.
		String [] tokens = input.split(";");
		for (int i = 0; i < tokens.length; i ++) {
			if (i == 0) {
				isbn = tokens[i]; 
			}
			else if (i == 1) {
				title = tokens[i]; 
			}
			else if (i == 2) {
				author = tokens[i]; 
			}
			else if (i == 3) {
				yearPublished = tokens[i]; 
			}
			else if (i == 4) {
				publisher = tokens[i]; 
			}
			else {
				break;
			}
		}
		// Next five for loops made to remove the quotation marks. 
		try {
			String [] splitISBN = isbn.split("\"");
			for (int i = 0; i < splitISBN.length; i ++) {
				isbn = splitISBN[1];
				break;
			}
			String [] splitTitle = title.split("\"");
			for (int i = 0; i < splitTitle.length; i ++) {
				title = splitTitle[1];
				break;
			}
			String [] splitAuthor = author.split("\"");
			for (int i = 0; i < splitAuthor.length; i ++) {
				author = splitAuthor[1];
				break;
			}
			String [] splitYearPublished = yearPublished.split("\"");
			for (int i = 0; i < splitYearPublished.length; i ++) {
				yearPublished = splitYearPublished[1];
				break;
			}
			String [] splitPublisher = publisher.split("\"");
			for (int i = 0; i < splitPublisher.length; i ++) {
				publisher = splitPublisher[1];
				break;
			}
		} catch (ArrayIndexOutOfBoundsException e) {}
		if (isbn.equals("ISBN") && title.equals("Book-Title") && author.equals("Book-Author") && yearPublished.equals("Year-Of-Publication") && publisher.equals("Publisher") ){
			throw new IllegalArgumentException("The first line of the txt file is not a real file.");
		}
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
			hashSet.add(book);
			arrayListAuthor.add(book.getAuthor() );
			arrayListPublisher.add(book.getPublisher() );
			numberOfBooks ++;
			// adding author set to map to find specific set of all books of author later.
			if (authorHashMap.containsKey(book.getAuthor() ) ) {
				Set <Book> newSetOfAuthors = authorHashMap.get(book.getAuthor());
				newSetOfAuthors.add(book);
				authorHashMap.put(book.getAuthor() , newSetOfAuthors);
			}
			else {
				Set <Book> newEntryToMap = new HashSet <Book>();
				newEntryToMap.add(book);
				authorHashMap.put(book.getAuthor() , newEntryToMap);
			}
		
			// adding publisher set to map to find specific set of all books of publisher later.
			if (publisherHashMap.containsKey(book.getPublisher() ) ) {
				Set <Book> newSetOfPublishers = publisherHashMap.get(book.getPublisher());
				newSetOfPublishers.add(book);
				publisherHashMap.put(book.getPublisher() , newSetOfPublishers);
			}
			else {
				Set <Book> newEntryToPublisherMap = new HashSet <Book>();
				newEntryToPublisherMap.add(book);
				publisherHashMap.put(book.getPublisher() , newEntryToPublisherMap);
			}
			// returning titles of a specific author.
			if (titlesHashMap.containsKey(book.getAuthor() ) ) {
				Set <String> newSetOfTitles = titlesHashMap.get(book.getTitle());
				newSetOfTitles.add(book.getTitle() );
				titlesHashMap.put(book.getTitle() , newSetOfTitles);
			}
			else {
				Set <String> newEntryToTitlesMap = new HashSet <String>();
				newEntryToTitlesMap.add(book.getTitle() );
				titlesHashMap.put(book.getTitle() , newEntryToTitlesMap);
			}
			// returning publisher with most unique authors.
			if (pubWAuthHashMap.containsKey(book.getPublisher() ) ) {
				Set <String> newSetOfPubWAuth = pubWAuthHashMap.get(book.getPublisher());
				newSetOfPubWAuth.add(book.getAuthor() );
				titlesHashMap.put(book.getPublisher() , newSetOfPubWAuth);
				if(newSetOfPubWAuth.size() > publisherWithMostAuthorsCounter) {
					publisherWithMostAuthorsCounter = newSetOfPubWAuth.size();
					publisherWithMostAuthors = book.getPublisher();
					System.out.println(publisherWithMostAuthors);
				}
			}
			else {
				Set <String> newEntryToPubWAuthMap = new HashSet <String>();
				newEntryToPubWAuthMap.add(book.getAuthor() );
				pubWAuthHashMap.put(book.getPublisher() , newEntryToPubWAuthMap);
			}
		}
		// counting max author here. 
		if(Collections.frequency(arrayListAuthor , book.getAuthor() ) > authorWithMostBooksCounter) {
			authorWithMostBooksCounter = Collections.frequency(arrayListAuthor , book.getAuthor() );
			authorWithMostBooks = book.getAuthor();
		}
		// counting max publisher here. 
		if(Collections.frequency(arrayListPublisher , book.getPublisher() ) > publisherWithMostBooksCounter) {
			publisherWithMostBooksCounter = Collections.frequency(arrayListPublisher , book.getPublisher() );
			publisherWithMostBooks = book.getPublisher();
		}
	}	

	public Set<Book> booksByAuthor (final String author) {	
		Set <Book> setOfAuthors = (Set <Book>)authorHashMap.get(author);
		return setOfAuthors;
	}

	public Set<Book> booksByPublisher(final String publisher) {
		Set <Book> setOfPublishers = (Set <Book>)publisherHashMap.get(publisher);
		return setOfPublishers;
	}

	public Set<String> allTitles (final String author) {
		Set <String> setOfTitles = (Set <String>)titlesHashMap.get(author);
		return setOfTitles;
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
			// testing the parseline method.
			while (scanner.hasNextLine() ) {
				try {
					Book bookOutput = authorsInstance.parseLine(scanner.nextLine() );
					String isbn = bookOutput.getISBN();
					String title = bookOutput.getTitle();
					String author = bookOutput.getAuthor();
					String yearPublished = bookOutput.getYearPublished();
					String publisher = bookOutput.getPublisher();
					authorsInstance.add(bookOutput);
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