import java.util.ArrayList;
import java.util.List;

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
            while(true) {
                try {
                    Message aux;
                    if (type.equals("topic"))
                        aux = new MessageForTopic("topic are mere " + this.name, "whatever");
                    else
                        aux = new MessageForQueue("queue are mere", "albastru");
                    server.addMessage(aux);
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        });
        this.recvMessagesHandler = new Thread(() -> {
           while (true) {
                   try {
                       String params = (type.equals("topic")) ? "whatever" : "albastru";
                       Message msg = server.getMessage(type, params);
                       if (msg != null)
                           System.out.println(this.name + " received message: " + msg.getBody());
                       Thread.sleep(2000);
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
    public void registerServer(Server server){
        this.server = server;
    }

    @Override
    public void startClient() {
        sendMessagesHandler.start();
        recvMessagesHandler.start();
    }

}
