public class MessageForQueue extends Message {

    private String receiver;

    MessageForQueue(String body, String sender, String dest){
        super(body, sender);
        this.receiver = dest;
    }

    public void setReceiver(String sender) {
        this.receiver = sender;
    }

    String getReceiver() {
        return receiver;
    }

}
