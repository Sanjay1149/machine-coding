package splitWise.modal;

public class User {
    static int uniqueID;
    String userId;
    String name;
    String email;
    long mobileNumber;
    ExpenseSheet expenseSheet;

    public User(String name, String email, long mobileNumber) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        generateId();
        expenseSheet = new ExpenseSheet(userId);
    }

    private void generateId() {
        this.userId = "u" + ++uniqueID;
    }

    public static int getUniqueID() {
        return uniqueID;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public ExpenseSheet getExpenseSheet() {
        return expenseSheet;
    }

    public String getEmail() {
        return email;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }
}
