package trello.service;

import trello.modal.BoardRoom;
import trello.modal.Card;
import trello.modal.JobList;
import trello.modal.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrelloService {
    /**
     * key -> boardRoomId
     */
    Map<String, BoardRoom> boardRoomMap = new HashMap<>();
    /**
     * key -> jobListId
     */
    Map<String, JobList> jobListMap = new HashMap<>();
    /**
     * key -> cardId
     */
    Map<String, Card> cardDataStore = new HashMap<>();

    public String showBoards() {
        if (boardRoomMap.size() == 0)
            return "No Boards";

        String boardRoomContexts = "{ boards: [ ";
        for (String boardRoomKey : boardRoomMap.keySet()) {
            boardRoomContexts += showBoardData(boardRoomKey);
            boardRoomContexts += ", ";
        }
        boardRoomContexts += " ] }";
        return boardRoomContexts;
    }

    public String showBoardData(String boardId) {
        BoardRoom boardRoom = boardRoomMap.get(boardId);
        if (boardRoom == null) {
            return "No Boards";
        }
        return boardRoom.toString();
    }

    public void deleteBoardRoom(String boardId) {
        boardRoomMap.remove(boardId);
    }

    public String showJobListData(String jobId) {
        JobList job = jobListMap.get(jobId);
        if (job == null) {
            return "No Jobs";
        }
        return job.toString();
    }

    public String showCardData(String cardId) {
        Card card = cardDataStore.get(cardId);
        if (card == null) {
            return "No Cards";
        }
        return card.toString();
    }

    public User createUser(String userName, String userEmail) {
        User incomingUser = new User(userName, userEmail);
        return incomingUser;
    }

    public String createBoard(String boardName) {
        BoardRoom boardRoom = new BoardRoom(boardName);
        String boardId = boardRoom.getBoardId();
        boardRoomMap.put(boardId, boardRoom);
        return boardId;
    }

    public void renameBoardName(String boardId, String newBoardName) {
        BoardRoom boardRoom = boardRoomMap.get(boardId);
        boardRoom.setBoardName(newBoardName);
    }

    public void setBoardPrivacy(String boardId, String boardPrivacy) {
        BoardRoom boardRoom = boardRoomMap.get(boardId);
        boardRoom.setBoardStatus(boardPrivacy);
    }

    public void addMemberToTheBoard(String boardId, User user) {
        BoardRoom boardRoom = boardRoomMap.get(boardId);
        boardRoom.addMember(user);
    }

    public void removeMemberFromBoard(String boardId, String userId) {
        BoardRoom boardRoom = boardRoomMap.get(boardId);
        List<User> membersOfTheRoom = boardRoom.getMembersInTheRoom();
        List<User> leavingMember = membersOfTheRoom.stream().filter((user) -> user.getUserId().equals(userId)).collect(Collectors.toList());
        boardRoom.removeMember(leavingMember.get(0));
    }

    public String createJobList(String boardId, String jobName) {
        BoardRoom boardRoom = boardRoomMap.get(boardId);
        JobList jobList = new JobList(boardId, jobName);
        boardRoom.addJobList(jobList);
        String jobId = jobList.getListId();
        jobListMap.put(jobId, jobList);

        return jobId;
    }

    public String createCard(String jobId, String cardName) {
        JobList jobList = jobListMap.get(jobId);
        Card card = new Card(jobList.getBoardId(), jobId, cardName);
        jobList.addCardToTheList(card);
        cardDataStore.put(card.getCardId(), card);
        return card.getCardId();
    }

    public void assignUserToCard(String cardId, String userId) {
        Card card = cardDataStore.get(cardId);
        User assigningUser = checkIfCardOperationCanBeDone(userId, card);
        if ( assigningUser != null) {
            card.assignUser(assigningUser);
        }
    }

    private User checkIfCardOperationCanBeDone(String userId, Card card) {
        if (card == null) {
            System.out.println("No Cards");
            return null;
        }
        JobList jobList = jobListMap.get(card.getListId());
        BoardRoom boardRoom = boardRoomMap.get(jobList.getBoardId());
        List<User> accessibleUsers = boardRoom.getMembersInTheRoom();
        List<User> assigningUser = accessibleUsers.stream().filter((user) -> user.getUserId().equals(userId)).collect(Collectors.toList());
        if (assigningUser.size() == 0){
            System.out.println("User Doesn't have access");
            return null;
        }
        return assigningUser.get(0);
    }

    public void unAssignUserFromCard(String cardId, String userId) {
        Card card = cardDataStore.get(cardId);
        User assigningUser = checkIfCardOperationCanBeDone(userId, card);
        if ( assigningUser != null) {
            card.unAssignUser(assigningUser);
        }
    }

    public void changeCardLocation(String cardId, String newCardId) {
        Card card = cardDataStore.get(cardId);
        cardDataStore.remove(cardId);
        JobList jobList = jobListMap.get(card.getListId());
        jobList.deleteCardFromList(card);

        String[] idSplit = newCardId.split( "-");
        jobList = jobListMap.get(idSplit[1]+"-"+idSplit[2]);

        card.changeCardId(newCardId);
        cardDataStore.put(newCardId, card);
        jobList.addCardToTheList(card);
    }

    public void renameCardName(String cardId, String cardName) {
        Card incomingCard = cardDataStore.get(cardId);
        if (incomingCard == null) {
            System.out.println("No Cards");
            return;
        }

        incomingCard.renameCardName(cardName);
    }

    public void addDescToCard(String cardId, String cardDesc) {
        Card incomingCard = cardDataStore.get(cardId);
        if (incomingCard == null) {
            System.out.println("No Cards");
            return;
        }

        incomingCard.setCardDesc(cardDesc);
    }

}
