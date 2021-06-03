package libraryManagementSystem.modal;

public class Rack {
    static int rackPrimaryKey;
    int rackId;
    boolean isOccupied;
    Book book;

    public Rack() {
        this.rackId = generateRackId();
        isOccupied = false;
    }

    public int getRackId() {
        return rackId;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    int generateRackId() {
        return ++rackPrimaryKey;
    }

    public void setRackOccupied() {
        isOccupied = true;
    }

    public void setRackFree() {
        isOccupied = false;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void removeBook() {
        this.book = null;
    }

}
