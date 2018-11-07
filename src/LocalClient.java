import java.util.ArrayList;
import java.util.List;

public class LocalClient implements Client {

    //TODO: create threads for listening and sending messages

    private static int userCount = 1;
    private String name;
    private Server server;
    Thread sendMessagesHandler;
    Thread recvMessagesHandler;
    private String type;

    public LocalClient(String type) {
        this.name = "User_" + userCount++;
        this.type = type;
        this.sendMessagesHandler = new Thread(() -> {
            while(true) {
                try {
                    Message aux;
                    if (type == "topic")
                        aux = new MessageForTopic("topic are mere " + this.name, "whatever");
                    else
                        aux = new MessageForQueue("queue are mere", "albastru");
                    server.addMessage(aux);
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    System.out.println(ie);
                }
            }
        });
        this.recvMessagesHandler = new Thread(() -> {
           while (true) {
                   try {
                       String params = (type == "topic") ? "whatever" : "albastru";
                       Message msg = server.getMessage(type, params);
                       if (msg != null)
                           System.out.println(this.name + " received message: " + msg.getBody());
                       Thread.sleep(2000);
                   } catch (InterruptedException ie) {
                       System.out.println(ie);
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
    public void sendMessage() {
//        this.server.addMessage(message);
        return;
    }

    @Override
    public Message receiveMessage(Message message) {
        System.out.println(message);
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

    @Override
    public void registerServer(Server server){
        this.server = server;
    }

    @Override
    public void startClient() {
        sendMessagesHandler.start();
        recvMessagesHandler.start();
    }

    @Override
    public void run() {
//         this.sendMessagesHandler = new Thread(() -> {
//                 Message aux = new MessageForTopic("ana are mere", "whatever");
//                 server.addMessage(aux);
//        });
    }

}
