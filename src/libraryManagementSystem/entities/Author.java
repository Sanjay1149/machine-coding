package libraryManagementSystem.entities;

import libraryManagementSystem.modal.Book;

import java.util.ArrayList;
import java.util.List;

public class Author {
    static int authorPrimaryKey;
    String authorId;
    String authorName;
    List<Book> bookList;

    public Author(String authorName) {
        this.authorName = authorName;
        this.authorId = generateAuthorId();
        bookList = new ArrayList<>();
    }

    String generateAuthorId() {
        return "author" + ++authorPrimaryKey;
    }

    public String getAuthorId(){
        return authorId;
    }

    public void addBookByAuthor(Book book) {
        bookList.add(book);
    }

    public void removeAuthoredBook(Book book) {
        bookList.remove(book);
    }

    public String getAuthorName() {
        return authorName;
    }
}
