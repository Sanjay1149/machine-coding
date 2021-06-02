package trello.modal;

import java.util.ArrayList;
import java.util.List;

public class Card {
    static int cardIndex = 0;
    private String cardId;
    private String boardId;
    private String listId;
    private String cardName;
    private String cardDesc;
    private List<User> assignedUsers;

    public Card(String boardId, String listId, String cardName) {
        this.boardId = boardId;
        this.listId = listId;
        this.cardName = cardName;
        cardId = generateCardId();
        assignedUsers = new ArrayList<>();
    }

    String generateCardId() {
        return "card" + ++cardIndex + "-" + listId;
    }

    public void assignUser(User user) {
        assignedUsers.add(user);
    }

    public void unAssignUser(User user) {
        assignedUsers.remove(user);
    }

    public List<User> getAssignedUsers() {
        return assignedUsers;
    }

    public String getCardId() {
        return cardId;
    }

    public String getListId() {
        return listId;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardDesc() {
        if ( cardDesc == null)
            return "Nothing Given";
        return cardDesc;
    }

    public void setCardDesc(String cardDesc) {
        this.cardDesc = cardDesc;
    }

    public void renameCardName(String cardName) {
        this.cardName = cardName;
    }

    public void changeCardId(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        StringBuilder cardContext = new StringBuilder("{ cardName " + getCardName() + ", cardDesc: " + getCardDesc());
        if ( assignedUsers.size() > 0 ) {
            cardContext.append(", Assignees: [");
            for (User member : assignedUsers) {
                cardContext.append("{ userId: ").append(member.getUserId()).append(", userName: ").append(member.getUserName()).append(", userEmail: ").append(member.getUserEmail()).append(" }, ");
            }
            cardContext.append(" ] ");
        }
        cardContext.append(" }");
        return cardContext.toString();
    }
}
