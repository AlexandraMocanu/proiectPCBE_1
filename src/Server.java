import java.util.ArrayList;

public class Server {

    //TODO:We also need a user input reader (with menu)
    //TODO:receive different message and forward them to corresponding clients
    //TODO:create threads for clients (receiving messages) and sending of messages (topic and queue)

    private ArrayList<Client> clients;
    private ArrayList<MessageQueue> messagesQueue;
    private ArrayList<Topic> messagesTopic;
    private String name = "Server";

    Server(String name) {
        clients = new ArrayList<>();
        messagesQueue = new ArrayList<>();
        messagesTopic = new ArrayList<>();
        this.name = name;
    }

    public void registerClient(LocalClient localClient) {
        clients.add(localClient);
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public synchronized void addMessage(MessageBroker message) {
        //messages.add(message);
    }

}