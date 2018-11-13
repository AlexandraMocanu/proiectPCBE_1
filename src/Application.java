import java.lang.reflect.Array;
import java.util.ArrayList;

public class Application {
    public static void main (String args[]) {

        // initialization
        Server server = new Server("Server");
        ArrayList<Client> clients = new ArrayList<>();

        for (int i = 0; i < 6; i++)
            clients.add(new LocalClient());
        //queue + topic

        // registration
        for (int i = 0; i < 6; i++) {
            server.registerClient(clients.get(i));
            clients.get(i).registerServer(server);
            String suc = clients.get(i).registerToTopicMessages("topic" + i % 2);
            System.out.println(clients.get(i).getName() + "---" + suc);
            suc = clients.get(i).registerToTopicMessages("topic" + i % 3);
            System.out.println(clients.get(i).getName() + "---" + suc);
            suc = clients.get(i).registerToTopicMessages("topic" + i % 4);
            System.out.println(clients.get(i).getName() + "---" + suc);
        }

        // start
        server.start();
        for (Client client : clients) {
            client.startClient();
        }
    }
}