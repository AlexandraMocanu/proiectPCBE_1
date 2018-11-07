import java.lang.reflect.Array;
import java.util.ArrayList;

public class Application {
    public static void main (String args[]){

        // initialization
        Server server = new Server("Server");
        ArrayList<Client> clients = new ArrayList<>();

        for (int i = 0; i < 5; i++)
            clients.add(new LocalClient("Client" + i, (i%2==0) ? "topic" : "queue"));

        // registration

        for (Client client : clients) {
            server.registerClient(client);
            client.registerServer(server);
        }

        // start

//        server.startServer();
        for (Client client: clients) {
            client.startClient();
        }

    }
}