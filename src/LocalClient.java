import java.util.ArrayList;
import java.util.Random;

public class LocalClient implements Client {

    private static int userCount = 0;
    private String name;
    private Server server;
    private ArrayList<String> topicSubs;
    private Thread sendMessagesHandler;
    private Thread recvMessagesHandler;
    private static volatile ArrayList<Message> receivedMsgs;

    public LocalClient() {
        this.name = "User_" + userCount++;
        receivedMsgs = new ArrayList<>();
        topicSubs = new ArrayList<>();

        this.sendMessagesHandler = new Thread(() -> {
            Random random = new Random();
            int queueSentCnt = 0, topicSentCnt = 0;
            while (true) {
                try {
                    Message aux = null;
                    if (topicSentCnt % 2 == 0) {
                        Long expirationTime = System.currentTimeMillis() + random.nextInt(10000);
                        aux = new MessageForTopic(this.name + "-Topic_Nr_" + (++topicSentCnt), this.name, expirationTime, "topic" + random.nextInt(6));
                    } else if (queueSentCnt % 2 == 0) {
                        aux = new MessageForQueue(this.name + "-Queue_Nr_" + (++queueSentCnt), this.name, "User_" + random.nextInt(6));
                    }
                    server.addMessage(aux);
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        });

        this.recvMessagesHandler = new Thread(() -> {
            Random random = new Random();
            while (true) {
                try {
                    //String params = (type.equals("topic")) ? "topicTags" : "queueMessage";
                    Message msg = null;
                    if (!receivedMsgs.isEmpty()){
                        msg = receivedMsgs.get(0);
                        receivedMsgs.remove(0);
                    }
                    if (msg != null){
                        if(msg instanceof MessageForTopic){
                            System.out.println(this.name + " received [TOPIC] message: " + msg.getBody() +
                                    " of type " + ((MessageForTopic) msg).getType());
                        }
                        else{
                            System.out.println(this.name + " received [QUEUE] message: " + msg.getBody() +
                                    " from " + msg.getSender());
                        }
                    }

                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        });
    }

    public String registerToTopicMessages(String tag){
        if(!topicSubs.contains(tag)){
            topicSubs.add(tag);
            return "Successfully subscribed to " + tag + "!";
        }
        else return "Already subscribed to " + tag;
    }

    public ArrayList<String> getTopicSubs(){
        return topicSubs;
    }

    @Override
    public String getName() {
        return name;
    }

    public synchronized Message receiveMessage(Message message) {
        synchronized (receivedMsgs){
            receivedMsgs.add(message);
        }
        return null;
    }

    @Override
    public void registerServer(Server server) {
        this.server = server;
    }

    @Override
    public void startClient() {
        sendMessagesHandler.start();
        recvMessagesHandler.start();
    }

}
