package libraryManagementSystem.modal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    static int libraryPrimaryKey;
    int totalRackCount;
    int racksOccupied;
    String libraryId;
    Map<Integer, BookProperties> bookPropertiesMap;
    Map<Integer, Rack> rackMap;

    public Library(int totalRackCount) {
        this.totalRackCount = totalRackCount;
        libraryId = generateLibraryId();
        rackMap = new HashMap<>(totalRackCount);
        bookPropertiesMap = new HashMap<>();
        createRacks();
    }

    void createRacks() {
        for (int i = 0; i<totalRackCount; i++ ) {
            Rack rack = new Rack();
            addRackInDataStore(rack);
        }
    }

    String generateLibraryId() {
        return "library" + ++libraryPrimaryKey;
    }

    public boolean canAccommodateBook() {
        return racksOccupied < totalRackCount;
    }

    public void newBooksAddedToTheRack() {
        racksOccupied+=1;
    }

    public void bookRemovedFromRack() {
        racksOccupied-=1;
    }

    public void addBookInDataStore(BookProperties bookProperties) {
        bookPropertiesMap.put(bookProperties.getBookId(), bookProperties);
    }

    public BookProperties getBookPropertiesFromDataStore(int bookId) {
        return bookPropertiesMap.get(bookId);
    }

    public void addRackInDataStore(Rack rack) {
        rackMap.put(rack.getRackId(), rack);
    }

    public Rack getRackDetailsFromDataStore(int rackId) {
        return rackMap.get(rackId);
    }

    public Rack getNextAvailableRack() {
        Rack rack = null;
        for ( Map.Entry<Integer,Rack> rackData : rackMap.entrySet() ){
            if ( !rackData.getValue().isOccupied ) {
                rack = rackData.getValue();
                break;
            }
        }
        return rack;
    }


}
