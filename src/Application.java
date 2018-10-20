import java.util.List;

public class Application {
    public static void main (String args[]){

        Server server = new Server();

        for (int i = 0; i < 5; i++) {
            server.addUser(new LocalClient(server));
        }

        List<LocalClient> localClients = server.getLocalClients();

        for (user:
             localClients) {
            user.start();
        }


    }
}