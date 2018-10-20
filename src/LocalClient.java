import java.util.ArrayList;
import java.util.List;

public class LocalClient implements Client {

    //TODO: create threads for listening and sending messages

    private static int userCount = 1;
    private String name;
    private Server server;
    private List<Message> messages;

    public LocalClient(Server server) {
        this.name = "User_" + userCount++;
        this.server = server;
        this.messages = new ArrayList<>();
    }

    public LocalClient(Server server, String name) {
        this(server);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void sendMessage(MessageBroker message) {

    }

    @Override
    public Message receiveMessage() {
        return null;
    }

    @Override
    public MessageForTopic buildTopic(String body, String tag) {
        return null;
    }

    @Override
    public MessageForQueue buildMessage(String body, String username) {
        return null;
    }

    public void registerToServer(){
        server.registerClient(this);
    }

    @Override
    public void run() {
        //take input, do what user asks
    }

}
