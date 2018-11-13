import java.util.ArrayList;

public interface Client {

    String getName();
    Message receiveMessage(Message message);
    void registerServer(Server server);
    String registerToTopicMessages(String tag);
    void startClient();
    ArrayList<String> getTopicSubs();
}
