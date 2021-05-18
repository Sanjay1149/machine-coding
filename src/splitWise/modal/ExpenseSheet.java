package splitWise.modal;

import java.util.HashMap;
import java.util.Map;

public class ExpenseSheet {
    String expenseSheetOwner;
    int remainingBalance;
    Map<String, PendingPayment> expenseChart;

    ExpenseSheet(String userId) {
        this.expenseSheetOwner = userId;
        expenseChart = new HashMap<>();
    }

    public Map<String, PendingPayment> getExpenseChart() {
        return expenseChart;
    }

    public int getRemainingBalance() {
        return remainingBalance;
    }

    public void addBalanceAmount(int amount) {
        this.remainingBalance += amount;
    }

    public void removeBalanceAmount(int amount) {
        this.remainingBalance -= amount;
    }
}
