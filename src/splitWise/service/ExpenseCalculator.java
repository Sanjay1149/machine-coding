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

    boolean isValidExactCondition(double totalPaidAmount, int numberOfUsersPaidFor, String[] inputQuery, int querySize) {
        int inputAmount = 0;
        for (int i = querySize - numberOfUsersPaidFor; i < querySize; i++) {
            inputAmount += Integer.parseInt(inputQuery[i]);
        }
        return inputAmount == totalPaidAmount;
    }

    boolean isValidPercentageCondition(int numberOfUsersPaidFor, String[] inputQuery, int querySize) {
        int percentageProvided = 0;
        for (int i = querySize - numberOfUsersPaidFor; i < querySize; i++) {
            percentageProvided += Integer.parseInt(inputQuery[i]);
        }
        return percentageProvided == 100;
    }

    double getPercentageAmount(double totalAmount, int percentage) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double userShareAmount = (totalAmount * percentage) / 100;
        return Double.parseDouble(decimalFormat.format(userShareAmount));
    }

    public void equalExpenseHandler(String input) {
        String[] inputQuery = input.split(" ");
        User paidUser = splitWiseRoom.getUserWithId(inputQuery[0]);
        double paidAmount = Double.parseDouble(inputQuery[1]);
        int numberOfUsersPaidFor = Integer.parseInt(inputQuery[2]);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double amountPerHead = paidAmount / numberOfUsersPaidFor;

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

    public boolean exactExpenseHandler(String input) {
        String[] inputQuery = input.split(" ");
        User paidUser = splitWiseRoom.getUserWithId(inputQuery[0]);
        double totalPaidAmount = Double.parseDouble(inputQuery[1]);
        int numberOfUsersPaidFor = Integer.parseInt(inputQuery[2]);
        int querySize = inputQuery.length;

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        if (isValidExactCondition(totalPaidAmount, numberOfUsersPaidFor, inputQuery, querySize)) {

            for (int i = 0; i < numberOfUsersPaidFor; i++) {
                String owedUserId = inputQuery[(3 + i)];
                User owedUser = splitWiseRoom.getUserWithId(owedUserId);
                double amountPerHead = Double.parseDouble(inputQuery[querySize + i - numberOfUsersPaidFor]);
                if (paidUser.getUserId().equals(owedUser.getUserId())) {

                } else {
                    PaymentService.addMoney(paidUser, owedUser, Double.parseDouble(decimalFormat.format(amountPerHead)));
                    PaymentService.owedMoney(paidUser, owedUser, Double.parseDouble(decimalFormat.format(amountPerHead)));
                }
            }

        } else {
            System.out.println("Please Input a valid entry");
            return false;
        }
        return true;
    }

    public boolean percentageExpenseHandler(String input) {
        String[] inputQuery = input.split(" ");
        User paidUser = splitWiseRoom.getUserWithId(inputQuery[0]);
        double paidAmount = Double.parseDouble(inputQuery[1]);
        int numberOfUsersPaidFor = Integer.parseInt(inputQuery[2]);
        int querySize = inputQuery.length;

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        if (isValidPercentageCondition(numberOfUsersPaidFor, inputQuery, querySize)) {

            for (int i = 0; i < numberOfUsersPaidFor; i++) {
                String owedUserId = inputQuery[(3 + i)];
                User owedUser = splitWiseRoom.getUserWithId(owedUserId);
                double amountPerHead = getPercentageAmount(paidAmount, Integer.parseInt(inputQuery[querySize + i - numberOfUsersPaidFor]));
                if (paidUser.getUserId().equals(owedUser.getUserId())) {

                } else {
                    PaymentService.addMoney(paidUser, owedUser, Double.parseDouble(decimalFormat.format(amountPerHead)));
                    PaymentService.owedMoney(paidUser, owedUser, Double.parseDouble(decimalFormat.format(amountPerHead)));
                }
            }

        } else {
            System.out.println("Please Input a valid entry");
            return false;
        }
        return true;
    }
}
