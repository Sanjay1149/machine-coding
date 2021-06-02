package trello;

import trello.modal.BoardRoom;
import trello.modal.User;
import trello.service.TrelloService;

import java.util.Scanner;

public class Trello {

    TrelloService trelloService;

    public static void main(String args[]) {
        Trello trelloApp = new Trello();
        trelloApp.trelloService = new TrelloService();
        trelloApp.startStimulation();
    }

    void startStimulation() {
        String input[];
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine().split(" ");
            if (input[0].equals("EXIT")) {
                break;
            } else if (input[0].equals("SHOW")) {
                if ( input.length == 1 ) {
                    String boardRoomContexts = trelloService.showBoards();
                    System.out.println(boardRoomContexts);
                } else if ( input[1].equals("BOARD") ) {
                    String boardRoomContext = trelloService.showBoardData(input[2]);
                    System.out.println(boardRoomContext);
                } else if ( input[1].equals("LIST") ) {
                    String jobContext = trelloService.showJobListData(input[2]);
                    System.out.println(jobContext);
                } else if ( input[1].equals("CARD") ) {
                    String cardContext = trelloService.showCardData(input[2]);
                    System.out.println(cardContext);
                }
            } else if (input[0].equals("BOARD")) {
                if ( input[1].equals("CREATE") ) {
                    String boardId = trelloService.createBoard(input[2]);
                    System.out.println("Board ID is " + boardId);
                } else if ( input[1].equals("DELETE") ) {
                    trelloService.deleteBoardRoom(input[2]);
                } else {
                    if ( input[2].equals("name") ) {
                        trelloService.renameBoardName(input[1], input[3]);
                    } else if ( input[2].equals("privacy") ) {
                        trelloService.setBoardPrivacy(input[1], input[3]);
                    } else if ( input[2].equals("ADD_MEMBER") ) {
                        User user = trelloService.createUser(input[3], input[4]);
                        trelloService.addMemberToTheBoard(input[1], user);
                    } else if ( input[2].equals("REMOVE_MEMBER") ) {
                        trelloService.removeMemberFromBoard(input[1], input[3]);
                    }
                }
            } else if ( input[0].equals("LIST")) {
                if ( input[1].equals("CREATE") ) {
                    String jobListId = trelloService.createJobList(input[2], input[3]);
                    System.out.println("Created List: " + jobListId);
                } else if ( input[1].equals("DELETE") ) {
//                    trelloService.deleteBoardRoom(input[2]);
                }
            } else if ( input[0].equals("CARD")) {
                if ( input[1].equals("CREATE") ) {
                    String cardId = trelloService.createCard(input[2], input[3]);
                    System.out.println("Created Card: " + cardId);
                } else if ( input[1].equals("DELETE") ) {
//                    trelloService.deleteBoardRoom(input[2]);
                } else if ( input[2].equals("name") ) {
                    trelloService.renameCardName(input[1], input[3]);
                } else if ( input[2].equals("description") ) {
                    trelloService.addDescToCard(input[1], input[3]);
                } else if ( input[2].equals("ASSIGN") ) {
                    trelloService.assignUserToCard(input[1], input[3]);
                } else if ( input[2].equals("UNASSIGN") ) {
                    trelloService.unAssignUserFromCard(input[1], input[3]);
                } else if ( input[2].equals("MOVE") ) {
                    trelloService.changeCardLocation(input[1], input[3]);
                }
            }
        }
    }
}
