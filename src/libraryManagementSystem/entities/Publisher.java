package libraryManagementSystem.entities;

import libraryManagementSystem.modal.Book;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    static int publisherPrimaryKey;
    String publisherId;
    String publisherName;
    List<Book> bookList;

    public Publisher(String publisherName) {
        this.publisherName = publisherName;
        this.publisherId = generatePublisherId();
        bookList = new ArrayList<>();
    }

    String generatePublisherId() {
        return "publisher" + ++publisherPrimaryKey;
    }

    public void addPublishedBook(Book book) {
        bookList.add(book);
    }

    public void removePublishedBook(Book book) {
        bookList.remove(book);
    }

    public String getPublisherId() {
        return publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }
}
