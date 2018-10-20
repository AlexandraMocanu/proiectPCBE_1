public class MessageForQueue extends Message{

    public String sender;
    MessageForQueue(String body, String sender){
        super(body);
        this.sender = sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }
}
