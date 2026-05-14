package identity;

public class User {

    private final int userId;
    private final String userName;
    private final Role role;

    public User(int userId, String userName, Role role) {
        this.userId = userId;
        this.userName = userName;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public Role getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }
}