import java.util.ArrayList;

public class Server {

    //TODO:We also need a user input reader (with menu)
    //TODO:receive different message and forward them to corresponding clients
    //TODO:create threads for clients (receiving messages) and sending of messages (topic and queue)

    private ArrayList<Client> clients;
    private ArrayList<MessageForQueue> messagesQueue;
    private ArrayList<MessageForTopic> messagesTopic;
    private String name = "Server";

    Server(String name) {
        clients = new ArrayList<>();
        messagesQueue = new ArrayList<>();
        messagesTopic = new ArrayList<>();
        this.name = name;
    }

    public void registerClient(Client localClient) {
        clients.add(localClient);
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public synchronized void addMessage(Message message) {
        if (message instanceof MessageForTopic)
            messagesTopic.add((MessageForTopic) message);
        else if (message instanceof MessageForQueue)
            messagesQueue.add((MessageForQueue) message);

        System.out.println(message);
    }

    public synchronized Message getMessage(String type, String params) {
        if (type == "topic")
            return this.getTopicMessage(params);
        else
            return this.getQueueMessage(params);
    }

    public Message getTopicMessage(String params) {
        for (MessageForTopic msg : messagesTopic) {
            if (msg.getType() == params)
                return msg;
        }
        return null;
    }

    public synchronized Message getQueueMessage(String params) {
        synchronized (messagesQueue) {
            System.out.println("Got here with queue: " + messagesQueue.size());
            for (MessageForQueue msg : messagesQueue)
                if (msg.getReceiver() == params) {
                    Message retVal = msg;
                    messagesQueue.remove(msg);
                    return retVal;
                }
        }
        return null;
    }

    public void startServer() {

    }

}