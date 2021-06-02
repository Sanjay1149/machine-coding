package trello.modal;

public class User {
    static int userIndex;
    private final String userId;
    private String userName;
    private String userEmail;

    public User(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
        userId = generateUserId();
    }

    String generateUserId() {
        return "user_" + ++userIndex;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
