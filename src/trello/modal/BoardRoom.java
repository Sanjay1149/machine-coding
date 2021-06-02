package trello.modal;

import trello.entities.PrivacyStatus;

import java.util.ArrayList;
import java.util.List;

public class BoardRoom {
    private static int boardIndex = 0;
    private String boardId;
    private String boardName;
    private PrivacyStatus boardStatus;
    private String boardUrl;
    private List<User> allowedMembers;
    private List<JobList> jobLists;

    public BoardRoom(String boardName) {
        this.boardName = boardName;
        boardStatus = PrivacyStatus.PUBLIC;
        boardId = generateBoardId();
        boardUrl = "https://trello.board.com/" + boardId;
        allowedMembers = new ArrayList<>();
        jobLists = new ArrayList<>();
    }

/*    BoardRoom(String boardName, PrivacyStatus boardStatus) {
        this.boardName = boardName;
        this.boardStatus = boardStatus;
        boardId = generateBoardId();
        boardUrl = "https://trello.board.com/" + boardId;
        allowedMembers = new ArrayList<>();
        jobLists = new ArrayList<>();
    }*/

    void addMemberToTheRoom(User user) {
        allowedMembers.add(user);
    }

    void removeMemberFromRoom(User user) {
        allowedMembers.remove(user);
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public PrivacyStatus getBoardStatus() {
        return boardStatus;
    }

    public void setBoardStatus(String boardStatus) {
        if (PrivacyStatus.PUBLIC.toString().equals(boardStatus)) {
            this.boardStatus = PrivacyStatus.PUBLIC;
        } else if (PrivacyStatus.PRIVATE.toString().equals(boardStatus)) {
            this.boardStatus = PrivacyStatus.PRIVATE;
        }
    }

    public String getBoardUrl() {
        return boardUrl;
    }

    public String getBoardId() {
        return boardId;
    }

    String generateBoardId() {
        return "board" + ++boardIndex;
    }

    public void addMember(User user) {
        allowedMembers.add(user);
    }

    public List<User> getMembersInTheRoom() {
        return allowedMembers;
    }

    public void removeMember(User user) {
        allowedMembers.remove(user);
    }

    public void addJobList(JobList job) {
        jobLists.add(job);
    }

    public List<JobList> getJobLists() {
        return jobLists;
    }

    public void removeJobList(JobList job) {
        jobLists.remove(job);
    }

    public String toString() {
        StringBuilder boardRoomContext = new StringBuilder("{ ID: " + getBoardId() + ", NAME: " + getBoardName() + ", PRIVACY: " + getBoardStatus().toString());
        if (jobLists.size() > 0) {
            boardRoomContext.append(", Jobs: [");
            for (JobList jobs : jobLists) {
                boardRoomContext.append(jobs.toString()).append(", ");
            }
            boardRoomContext.append(" ]");
        }
        if (allowedMembers.size() > 0) {
            boardRoomContext.append(", Members: [");
            for (User member : allowedMembers) {
                boardRoomContext.append("{ userId: ").append(member.getUserId()).append(", userName: ").append(member.getUserName()).append(", userEmail: ").append(member.getUserEmail()).append(" }, ");
            }
            boardRoomContext.append(" ]");
        }
        boardRoomContext.append(" }");
        return boardRoomContext.toString();
    }

}
