package splitWise.service;


import splitWise.modal.SplitWiseRoom;
import splitWise.modal.User;

public class ExpenseCalculator {

    SplitWiseRoom splitWiseRoom;

    public ExpenseCalculator(SplitWiseRoom splitWiseRoom) {
        this.splitWiseRoom = splitWiseRoom;
    }

    boolean isValidExactCondition() {
        return true;
    }

    boolean isValidPercentageCondition() {
        return true;
    }

    public void equalExpenseHandler(String input) {
        String[] inputQuery = input.split(" ");
        User paidUser = splitWiseRoom.getUserWithId(inputQuery[0]);
        int paidAmount = Integer.parseInt(inputQuery[1]);
        int numberOfUsersPaidFor = Integer.parseInt(inputQuery[2]);
        int amountPerHead = paidAmount / numberOfUsersPaidFor;
        for (int i = 0; i < numberOfUsersPaidFor; i++) {
            String owedUserId = inputQuery[(3 + i)];
            User owedUser = splitWiseRoom.getUserWithId(owedUserId);
            if (paidUser.getUserId().equals(owedUser.getUserId())) {

            } else {
                PaymentService.addMoney(paidUser, owedUser, amountPerHead);
                PaymentService.owedMoney(paidUser, owedUser, amountPerHead);
            }
        }

    }

    public void exactExpenseHandler(String input) {

    }

    public void percentageExpenseHandler(String input) {

    }
}
