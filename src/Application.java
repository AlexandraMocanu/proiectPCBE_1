import java.util.ArrayList;

public class Application {
    public static void main (String args[]){

        Server server = new Server("Server1");

        for (int i = 0; i < 5; i++) {
            LocalClient client = new LocalClient(server);
            client.registerToServer();
        }

        ArrayList<Client> localClients = server.getClients();

        for (int i = 0; i < localClients.size(); i++) {
            Thread clientThread = new Thread(localClients.get(i));
            clientThread.start();
            //TODO:make client create its 2 threads for receiving and sending of messages
        }

    }
}