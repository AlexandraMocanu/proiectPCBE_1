import java.util.ArrayList;

public class Application {
    public static void main (String args[]){

        Server server = new Server("Server1");

        for (int i = 0; i < 5; i++) {
            LocalClient client = new LocalClient(server);
            client.registerToServer();
        }

    }
}