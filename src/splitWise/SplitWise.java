package splitWise;

import splitWise.modal.SplitWiseRoom;
import splitWise.modal.User;
import splitWise.service.ExpenseCalculator;

import java.util.Scanner;

public class SplitWise {
    static SplitWiseRoom splitWiseRoom;
    static User[] users;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int noOfUsers = Integer.parseInt(in.nextLine());
        users = new User[noOfUsers];
        splitWiseRoom = new SplitWiseRoom();
        for (int i = 0; i < noOfUsers; i++) {
            String[] userInput = in.nextLine().split(" ");
            users[i] = new User(userInput[0], userInput[1], Long.parseLong(userInput[2]));
            splitWiseRoom.addUserToRoom(users[i]);
        }
        splitWiseRoom.setAdminUser(users[0].getUserId());

        System.out.println();
        startStimulation(in);
    }

    private static void startStimulation(Scanner in) {
        ExpenseCalculator expenseCalculator = new ExpenseCalculator(splitWiseRoom);
        while (true) {
            boolean handlerStatus = false;
            String paymentInput = in.nextLine();
            if (paymentInput.contains("EQUAL")) {
                expenseCalculator.equalExpenseHandler(paymentInput);
                handlerStatus = true;
            } else if (paymentInput.contains("EXACT")) {
                handlerStatus = expenseCalculator.exactExpenseHandler(paymentInput);
            } else if (paymentInput.contains("PERCENT")) {
                handlerStatus = expenseCalculator.percentageExpenseHandler(paymentInput);
            }
            if (handlerStatus) {
                System.out.println("Next Iteration -> ");
                splitWiseRoom.printStatus(users);
            }
        }
    }
}
