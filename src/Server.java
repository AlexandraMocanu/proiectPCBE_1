import java.util.ArrayList;
import java.util.Iterator;

public class Server {

    //TODO:We also need a user input reader (with menu)
    //TODO:receive different message and forward them to corresponding clients
    //TODO:create threads for clients (receiving messages) and sending of messages (topic and queue)

    private ArrayList<Client> clients;
    private final ArrayList<MessageForQueue> messagesQueue;
    private final ArrayList<MessageForTopic> messagesTopic;
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
        if (message instanceof MessageForTopic) {
            messagesTopic.add((MessageForTopic) message);
            System.out.println("[TOPIC]:" + message);
        }
        else if (message instanceof MessageForQueue) {
            messagesQueue.add((MessageForQueue) message);
        }

    }

    public Message getMessage(String type, String params) {
        if (type.equals("topic"))
            return this.getTopicMessage(params);
        else
            return this.getQueueMessage(params);
    }

    private synchronized Message getTopicMessage(String params) {
        synchronized (messagesTopic) {
            Iterator<MessageForTopic> iter = messagesTopic.iterator(); ;
            while (iter.hasNext()) {
                MessageForTopic msg = iter.next();
                Long expire = msg.getExpire();
                if (expire != null && expire > System.currentTimeMillis()) {
                    iter.remove();
                } else if (msg.getType().equals(params))
                    return msg;
            }
            return null;
        }
    }

    private synchronized Message getQueueMessage(String params) {
        synchronized (messagesQueue) {
            for (MessageForQueue msg : messagesQueue)
                if (msg.getReceiver().equals(params)) {
                    messagesQueue.remove(msg);
                    return msg;
                }
        }
        return null;
    }

}