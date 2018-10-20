import java.util.ArrayList;
import java.util.concurrent.*;

//https://codereview.stackexchange.com/questions/38557/concurrent-multi-server-pinging-in-java
//TODO:See above for example of pinging (check out user messages)


public class Server{

    //TODO:We also need a user input reader (with menu)

    private ArrayList<Client> clientList = new ArrayList<Client>();
    private String name = "Server";

    Server(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void registerClient(Client client){
        this.clientList.add(client);
    }

}