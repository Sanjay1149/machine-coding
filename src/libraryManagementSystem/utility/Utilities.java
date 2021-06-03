package libraryManagementSystem.utility;

import libraryManagementSystem.entities.Author;
import libraryManagementSystem.entities.Publisher;
import libraryManagementSystem.modal.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utilities {

    public static Map<String, Author> authorMap = new HashMap<>();
    public static Map<String, Publisher> publisherMap = new HashMap<>();
    public static Map<String, Book> centralBookMap = new HashMap<>();

    public static Author checkAndAddAuthorToLibraryDb(String authorName) {
        Author author =  authorMap.get(authorName);
        if ( author == null ) {
            author = new Author(authorName);
            authorMap.put(authorName, author);
        }
        return author;
    }

    public static Publisher checkAndAddPublisherToLibraryDb(String publisherName) {
        Publisher publisher =  publisherMap.get(publisherName);
        if ( publisher == null ) {
            publisher = new Publisher(publisherName);
            publisherMap.put(publisherName, publisher);
        }
        return publisher;
    }

    public static void addBookToTheCentralLibrary(Book book, List<Author> authors, List<Publisher> publishers) {
        centralBookMap.put(book.getBookCopyId(), book);
        authors.forEach((author -> {
            author.addBookByAuthor(book);
        }));
        publishers.forEach((publisher -> {
            publisher.addPublishedBook(book);
        }));
    }

    public static Map<String, Book> getCentralBookMap() {
        return centralBookMap;
    }

    public static Book removeBookFromLibrary(String bookCopyId) {
        Book book = centralBookMap.remove(bookCopyId);
        return book;
    }

}
