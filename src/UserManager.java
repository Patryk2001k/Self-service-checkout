import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users = new ArrayList<>();
    private User loggedInUser = null;

    public boolean register(String username, String password) {
        if (getUserByUsername(username) == null) {
            users.add(new User(username, password));
            return true;
        }
        return false;
    }

    public boolean login(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.checkPassword(password)) {
            loggedInUser = user;
            return true;
        }
        return false;
    }

    public void logout() {
        loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    private User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
