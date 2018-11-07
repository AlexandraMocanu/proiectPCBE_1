public class MessageForQueue extends Message {

    private String receiver;
    MessageForQueue(String body, String sender){
        super(body);
        this.receiver = sender;
    }

    public void setReceiver(String sender) {
        this.receiver = sender;
    }

    public String getReceiver() {
        return receiver;
    }

}
