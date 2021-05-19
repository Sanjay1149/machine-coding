package splitWise.modal;

import java.util.HashMap;
import java.util.Map;

public class SplitWiseRoom {
    private final Map<String, User> usersMap;
    String adminUserId;

    public SplitWiseRoom() {
        usersMap = new HashMap<>();
    }

    public void addUserToRoom(User user) {
        usersMap.put(user.getUserId(), user);
    }

    public User getUserWithId(String userId) {
        return usersMap.get(userId);
    }

    public void setAdminUser(String adminUserId) {
        this.adminUserId = adminUserId;
    }

    public void showUsersBalance(User[] users) {
        for (User userData : users) {
            ExpenseSheet expenseSheet = userData.getExpenseSheet();
            Map<String, PendingPayment> expenseChart = expenseSheet.getExpenseChart();
            expenseChart.forEach((userId, pendingPayment) -> {
                if (pendingPayment.status == PaymentStatus.OWE) {
                    System.out.println(userData.getUserId() + " owes " + userId + " " + Math.abs(pendingPayment.getRemainingBalance()));
                }
            });
        }
    }

    public void showBalanceForUser(String userId) {
        User user = usersMap.get(userId);
        ExpenseSheet expenseSheet = user.getExpenseSheet();
        Map<String, PendingPayment> expenseChart = expenseSheet.getExpenseChart();
        expenseChart.forEach((otherUserId, pendingPayment) -> {
            if (pendingPayment.status == PaymentStatus.OWE) {
                System.out.println(userId + " owes " + otherUserId + " " + Math.abs(pendingPayment.getRemainingBalance()));
            } else if (pendingPayment.status == PaymentStatus.NEED) {
                System.out.println(otherUserId + " owes " + userId + " " + Math.abs(pendingPayment.getRemainingBalance()));
            }
        });
    }
}
