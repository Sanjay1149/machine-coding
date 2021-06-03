package libraryManagementSystem.modal;

import libraryManagementSystem.entities.Author;
import libraryManagementSystem.entities.Publisher;
import libraryManagementSystem.utility.Utilities;

import java.util.*;

public class BookProperties {
    int bookId;
    String bookTitle;
    int remainingCopiesInLibrary;
    int totalBookCount;
    Map<String, Book> bookMap;
    List<Author> authorList;
    List<Publisher> publisherList;

    public BookProperties(int bookId, String bookTitle, String[] authors, String[] publishers) {
        this.bookTitle = bookTitle;
        this.bookId = bookId;
        bookMap = new LinkedHashMap<>();
        authorList = new ArrayList<>(authors.length);
        publisherList = new ArrayList<>(publishers.length);
        defaultBookPropertySetting(authors, publishers);
    }

    void defaultBookPropertySetting(String[] authors, String[] publishers) {
        Arrays.stream(authors).forEach((author) -> {
            Author newAuthor = Utilities.checkAndAddAuthorToLibraryDb(author);
            authorList.add(newAuthor);
        });
        Arrays.stream(publishers).forEach((publisher) -> {
            Publisher newPublisher = Utilities.checkAndAddPublisherToLibraryDb(publisher);
            publisherList.add(newPublisher);
        });
    }

    public Book assignBookToTheRacks(String bookCopyId, Rack rack) {
        Book book = new Book(bookCopyId, bookId, rack);
        bookMap.put(bookCopyId, book);
        Utilities.addBookToTheCentralLibrary(book, authorList, publisherList);
        return book;
    }

    public void removeBookCopy(Book bookCopy) {
        bookMap.remove(bookCopy.getBookCopyId());
        Rack rack = bookCopy.getRack();
        rack.setRackFree();
    }

    public void setTotalBookCount(int totalBookCount) {
        this.totalBookCount = totalBookCount;
        this.remainingCopiesInLibrary = totalBookCount;
    }

    public void oneCopyBorrowedByUser() {
        this.remainingCopiesInLibrary -= 1;
    }

    public void oneCopyReturnedByUser() {
        this.remainingCopiesInLibrary += 1;
    }

    public boolean isAllCopiedPresent() {
        return remainingCopiesInLibrary == totalBookCount;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Map<String, Book> getBookMap() {
        return bookMap;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public List<Publisher> getPublisherList() {
        return publisherList;
    }
}
