import java.util.ArrayList;
import java.util.List;

public class User implements Runnable {


    private static int userCount = 1;
    private String userName;

    private List<Message> messages;

    public User() {
        this.userName = "User_" + userCount++;
        this.messages = new ArrayList<>();
    }

    public User(String userName) {
        this.userName = userName;
        this.messages = new ArrayList<>();
    }

    @Override
    public void run() {

        return;
    }
}
