import java.util.ArrayList;
import java.util.List;

//https://codereview.stackexchange.com/questions/38557/concurrent-multi-server-pinging-in-java
//TODO:See above for example of pinging (check out user messages)


public class Server {

    //TODO:We also need a user input reader (with menu)

    private String name = "Server";
    private List<LocalClient> localClients;
    private List<Message> messages;


    public void addUser(LocalClient localClient) {
        localClients.add(localClient);
    }

    public List<LocalClient> getLocalClients() {
        return localClients;
    }

    public synchronized void addMessage(Message message) {
        messages.add(message);
        //find destination

    }

    Server() {
        localClients = new ArrayList<>();
        messages = new ArrayList<>();
    }
    Server(String name){
        this();
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sendMessage(Message msg){

    }

    public void receiveMessage(Message msg){

    }
}