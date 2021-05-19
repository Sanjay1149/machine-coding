package splitWise.service;


import splitWise.modal.SplitWiseRoom;
import splitWise.modal.User;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double amountPerHead = (double) paidAmount / numberOfUsersPaidFor;

        for (int i = 0; i < numberOfUsersPaidFor; i++) {
            String owedUserId = inputQuery[(3 + i)];
            User owedUser = splitWiseRoom.getUserWithId(owedUserId);
            if (paidUser.getUserId().equals(owedUser.getUserId())) {

            } else {
                PaymentService.addMoney(paidUser, owedUser, Double.parseDouble(decimalFormat.format(amountPerHead)));
                PaymentService.owedMoney(paidUser, owedUser, Double.parseDouble(decimalFormat.format(amountPerHead)));
            }
        }

    }

    public void exactExpenseHandler(String input) {

    }

    public void percentageExpenseHandler(String input) {

    }
}
