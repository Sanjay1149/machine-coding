package trello.modal;

import java.util.ArrayList;
import java.util.List;

public class JobList {
    static int listIndex = 0;
    private final String listId;
    private final String boardId;
    private String listName;
    private List<Card> cardList;

    public JobList(String boardId, String listName) {
        this.boardId = boardId;
        this.listName = listName;
        this.listId = generateListId();
        cardList = new ArrayList<>();
    }

    String generateListId() {
        return "list" + ++listIndex + "-" + boardId;
    }

    public void addCardToTheList(Card incomingCard) {
        cardList.add(incomingCard);
    }

    public void deleteCardFromList(Card trashCard) {
        cardList.remove(trashCard);
    }

    public String getListId() {
        return listId;
    }

    public String getBoardId() {
        return boardId;
    }

    public String getListName() {
        return listName;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public String toString() {
        StringBuilder jobListContext = new StringBuilder("{ Id: " + getListId() + ", listName: " + getListName());
        if ( cardList.size() > 0 ) {
            jobListContext.append(", Cards: [ ");
            for (Card card : cardList) {
                jobListContext.append(card.toString()).append(", ");
            }
            jobListContext.append(" ]");
        }
        jobListContext.append(" }");
        return jobListContext.toString();
    }
}
