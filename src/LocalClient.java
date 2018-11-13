import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class LocalClient implements Client {

    private static int userCount = 1;
    private String name;
    private Server server;
    private Thread sendMessagesHandler;
    private Thread recvMessagesHandler;
    private String type;

    public LocalClient(String type) {
        this.name = "User_" + userCount++;
        this.type = type;
        this.sendMessagesHandler = new Thread(() -> {
            Random random = new Random();
            int queueSentCnt = 0, topicSentCnt = 0;
            while (true) {
                try {
                    Message aux;
                    if (type.equals("topic")) {
                        Long expirationTime = System.currentTimeMillis() + random.nextInt(10000);
                        aux = new MessageForTopic(this.name + "- topic nr " + (++topicSentCnt), expirationTime, "topicTags");
                    } else
                        aux = new MessageForQueue(this.name + "- queue nr " + (++queueSentCnt), "queueMessage");
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
                    String params = (type.equals("topic")) ? "topicTags" : "queueMessage";
                    Message msg = server.getMessage(type, params);
                    if (msg != null)
                        System.out.println(this.name + " received message: " + msg.getBody());
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        });
    }

    public LocalClient(String name, String type) {
        this(type);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Message receiveMessage(Message message) {
        System.out.println(message);
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
