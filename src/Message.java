public abstract class Message{

    private int availability;
    private String body;
    private Server sender;
    private Server receiver;

    Message(){}

    Message(Server sender, Server receiver, int availability, String body){
        this.availability = availability;
        this.body = body;
        this.receiver = receiver;
        this.sender = sender;
    }

    public int getAvailability() {
        return availability;
    }

    public Server getReceiver() {
        return receiver;
    }

    public Server getSender() {
        return sender;
    }

    public String getBody() {
        return body;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setReceiver(Server receiver) {
        this.receiver = receiver;
    }

    public void setSender(Server sender) {
        this.sender = sender;
    }

    public int getTimeRemaining(){
        //???
        return 0;
    }
}