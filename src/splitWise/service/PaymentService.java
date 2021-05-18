package splitWise.service;

import splitWise.modal.ExpenseSheet;
import splitWise.modal.PaymentStatus;
import splitWise.modal.PendingPayment;
import splitWise.modal.User;

import java.util.Map;

public class PaymentService {

    static void addMoney(User paidUser, User owedUser, int paidAmount) {
        ExpenseSheet expenseSheet = paidUser.getExpenseSheet();
        Map<String, PendingPayment> expenseChart = expenseSheet.getExpenseChart();
        String owedUserId = owedUser.getUserId();
        if (expenseChart.containsKey(owedUserId)) {
            PendingPayment pendingPayment = expenseChart.get(owedUserId);
            pendingPayment.revisePayment(paidAmount);
        } else {
            PendingPayment pendingPayment = new PendingPayment(paidUser.getUserId(), owedUserId, paidAmount, PaymentStatus.NEED);
            expenseChart.put(owedUserId, pendingPayment);
        }

        expenseSheet.addBalanceAmount(paidAmount);
    }

    static void owedMoney(User paidUser, User owedUser, int paidAmount) {
        ExpenseSheet expenseSheet = owedUser.getExpenseSheet();
        Map<String, PendingPayment> expenseChart = expenseSheet.getExpenseChart();
        String paidUserId = paidUser.getUserId();
        if (expenseChart.containsKey(paidUserId)) {
            PendingPayment pendingPayment = expenseChart.get(paidUserId);
            pendingPayment.revisePayment(-paidAmount);
        } else {
            PendingPayment pendingPayment = new PendingPayment(paidUserId, owedUser.getUserId(), -paidAmount, PaymentStatus.OWE);
            expenseChart.put(paidUserId, pendingPayment);
        }

        expenseSheet.removeBalanceAmount(paidAmount);
    }

}
