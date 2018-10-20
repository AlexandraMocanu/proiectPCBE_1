public class Client implements Runnable{

    private String Name;

    Client(String name){
        this.Name = name;
    }

    public void registerToServer(Server server){
        server.registerClient(this);
    }

    @Override
    public void run() {

    }
}
