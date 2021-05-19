package splitWise.modal;

import java.util.HashMap;
import java.util.Map;

public class ExpenseSheet {
    String expenseSheetOwner;
    double remainingBalance;
    Map<String, PendingPayment> expenseChart;

    ExpenseSheet(String userId) {
        this.expenseSheetOwner = userId;
        expenseChart = new HashMap<>();
    }

    public Map<String, PendingPayment> getExpenseChart() {
        return expenseChart;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }

    public void addBalanceAmount(double amount) {
        this.remainingBalance += amount;
    }

    public void removeBalanceAmount(double amount) {
        this.remainingBalance -= amount;
    }
}
