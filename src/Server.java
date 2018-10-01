public abstract class Server{

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

    public void sendMessage(Message msg){

    }

    public void receiveMessage(Message msg){

    }
}