package libraryManagementSystem.service;

import libraryManagementSystem.entities.Author;
import libraryManagementSystem.entities.Publisher;
import libraryManagementSystem.modal.Book;
import libraryManagementSystem.modal.BookProperties;
import libraryManagementSystem.modal.Library;
import libraryManagementSystem.modal.Rack;
import libraryManagementSystem.utility.Utilities;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibraryManagementService {
    //    Map<String, Library> libraryMap;
    Library library;

    public void accommodateBookInRacks(String bookId, String[] books) {

    }

    public void createLibrary(int totalRackCount) {
        library = new Library(totalRackCount);
    }

    public String addBookEntries(String[] inputArray) {
        int bookId = Integer.parseInt(inputArray[1]);
        String bookTitle = inputArray[2];
        String[] authors = inputArray[3].split(",");
        String[] publishers = inputArray[4].split(",");
        String[] bookCopies = inputArray[5].split(",");
        StringBuilder rackIds = new StringBuilder("Added Book to racks: ");

        /**
         * @Sanjay - Should check if bookProperties already exist
         */
        BookProperties bookProperty = library.getBookPropertiesFromDataStore(bookId);
         if ( bookProperty == null) {
             bookProperty = new BookProperties(bookId, bookTitle, authors, publishers);
         }
        library.addBookInDataStore(bookProperty);
        int i = 0;
        for (i = 0; i < bookCopies.length; i++) {
            if (library.canAccommodateBook()) {
                Rack rack = library.getNextAvailableRack();
                rack.setRackOccupied();
                Book book = bookProperty.assignBookToTheRacks(bookCopies[i], rack);
                rack.setBook(book);
                rackIds.append(rack.getRackId()).append(",");
                library.newBooksAddedToTheRack();
            } else {
                if (i == 0) {
                    rackIds.setLength(0);
                }
                rackIds.append("\nRack not available for ").append(bookCopies[i]);
            }
        }
/*        if (i != 0) {
            library.newBooksAddedToTheRack(i);
        }*/
        return rackIds.toString();
    }

    public String searchBookId(int bookId) {
        StringBuilder bookDetails = new StringBuilder();
        BookProperties bookProperties = library.getBookPropertiesFromDataStore(bookId);
        Map<String, Book> bookMap = bookProperties.getBookMap();
        List<Author> authors = bookProperties.getAuthorList();
        List<Publisher> publishers = bookProperties.getPublisherList();
        String authorNames = authors.stream().map(Author::getAuthorName).collect(Collectors.joining(","));
        String publisherNames = publishers.stream().map(Publisher::getPublisherName).collect(Collectors.joining(","));
        for (Map.Entry<String, Book> bookDetail : bookMap.entrySet()) {
            Book book = bookDetail.getValue();
            if (!book.isTaken()) {
                bookDetails.append("Book Copy: ").append(book.getBookCopyId()).append(" ").append(book.getBookId())
                        .append(" ").append(authorNames).append(" ").append(publisherNames).append(" ").append(book.getRackId());
            }
            bookDetails.append("\n");
        }
        return bookDetails.toString();
    }

    public String removeBookCopy(String bookCopyId) {
        StringBuilder removeStatus = new StringBuilder();
        Book bookCopy = Utilities.removeBookFromLibrary(bookCopyId);
        if ( bookCopy != null ) {
            BookProperties bookProperties = library.getBookPropertiesFromDataStore(bookCopy.getBookId());
            List<Author> authors = bookProperties.getAuthorList();
            List<Publisher> publishers = bookProperties.getPublisherList();
            authors.forEach((author -> author.removeAuthoredBook(bookCopy)));
            publishers.forEach((publisher -> publisher.removePublishedBook(bookCopy)));
            bookProperties.removeBookCopy(bookCopy);
            library.bookRemovedFromRack();

            removeStatus.append("Removed book copy: ").append(" ").append(bookCopyId).append(" from rack ").append(bookCopy.getRackId());
        }
        return removeStatus.toString();
    }

}
