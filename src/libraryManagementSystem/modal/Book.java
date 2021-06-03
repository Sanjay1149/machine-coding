package libraryManagementSystem.modal;

public class Book {
    int bookId;
    String bookCopyId;
    Rack rack;
    boolean isTaken;

    Book(String bookCopyId, int bookId, Rack rack) {
        this.bookCopyId = bookCopyId;
        this.rack = rack;
        this.bookId = bookId;
        isTaken = false;
    }

    public int getRackId() {
        return isTaken ? -1 : rack.getRackId();
    }

    public boolean isTaken() {
        return isTaken;
    }

    public String getBookCopyId() {
        return bookCopyId;
    }

    public int getBookId() {
        return bookId;
    }

    public Rack getRack() {
        return rack;
    }

    public void setBookTakenStatus(boolean taken) {
        isTaken = taken;
    }
}
