import java.util.*;

public class Server{

    private ArrayList<Client> clients;
    private final Queue messagesQueue;
    private final Topic messagesTopic;
    private final HashMap<Message, ArrayList<Client>> sent;
    private String name = "Server";
    private Thread admin;

    Server(String name) {
        clients = new ArrayList<>();
        messagesQueue = new Queue();
        messagesTopic = new Topic();
        sent = new HashMap<>();
        this.name = name;

        this.admin = new Thread(() -> {
            while(true){
                for(int i = 0; i < clients.size(); i++){
                    synchronized (messagesTopic) {
                        for (int j = 0; j < messagesTopic.getSize(); j++) {
                            if (clients.get(i).getTopicSubs().
                                    contains(((MessageForTopic) messagesTopic.getMessage(j)).getType())) {

                                ArrayList<Client> aux = sent.get(messagesTopic.getMessage(j));
                                if(!aux.contains(clients.get(i))){
                                    clients.get(i).receiveMessage(messagesTopic.getMessage(j));

                                    ArrayList<Client> newC = sent.get(messagesTopic.getMessage(j));
                                    newC.add(clients.get(i));
                                    sent.put(messagesTopic.getMessage(j), newC);
                                    System.out.println("Server sent message " + messagesTopic.getMessage(j) +
                                            " to client " + clients.get(j).getName());
                                }
                            }
                        }
                    }

                    synchronized (messagesQueue) {
                        for (int j = 0; j < messagesQueue.getSize(); j++) {
                            if (clients.get(i).getName().equals(
                                    ((MessageForQueue) messagesQueue.getMessage(j)).getReceiver())) {
                                clients.get(i).receiveMessage(messagesQueue.getMessage(j));
                                System.out.println("Server sent message " + messagesQueue.getMessage(j) +
                                        " to client " + clients.get(j).getName());
                                System.out.println("Server removed message " + messagesQueue.getMessage(j));
                                messagesQueue.removeMsg(j);
                            }
                        }
                    }
                }

                processTopic();
            }
        });
    }

    public void start(){
        admin.start();
    }

    public void registerClient(Client localClient) {
        clients.add(localClient);
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public synchronized void addMessage(Message message) {
        if (message instanceof MessageForTopic) {
            synchronized (messagesTopic) {
                messagesTopic.addMsg(message);
                ArrayList<Client> cl = new ArrayList<>();
                sent.put(message, cl);
            }
            System.out.println("Server received [TOPIC]:" + message);
        }
        else if (message instanceof MessageForQueue) {
            synchronized (messagesQueue) {
                messagesQueue.addMsg(message);
            }
            System.out.println("Server received [QUEUE]:" + message);
        }

    }

    public synchronized void processTopic(){
        synchronized (messagesTopic) {
            for (int i = 0; i < messagesTopic.getSize(); i++) {
                Long expire = ((MessageForTopic)messagesTopic.getMessage(i)).getExpire();
                Long end = System.currentTimeMillis()/10000000;
                if (expire != null && expire < end) {
                    System.out.println("Server removed [TOPIC]:" + messagesTopic.getMessage(i) +
                            " after " + expire/1000 + " seconds have passed since publication.");
                    messagesTopic.removeMsg(i);
                }
            }
        }
    }

//    private synchronized Message getTopicMessage(String params) {
//        synchronized (messagesTopic) {
//            for (int i = 0; i < messagesTopic.getSize(); i++) {
//                Long expire = ((MessageForTopic)messagesTopic.getMessage(i)).getExpire();
//                if (expire != null && expire > System.currentTimeMillis()) {
//                    messagesTopic.removeMsg(i);
//                } else if (msg.getType().equals(params))
//                    return msg;
//            }
//            return null;
//        }
//    }
//
//    private synchronized Message getQueueMessage(String params) {
//        synchronized (messagesQueue) {
//            for (MessageForQueue msg : messagesQueue)
//                if (msg.getReceiver().equals(params)) {
//                    messagesQueue.remove(msg);
//                    return msg;
//                }
//        }
//        return null;
//    }

}