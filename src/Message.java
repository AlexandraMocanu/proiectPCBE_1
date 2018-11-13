public class Message {
    private String body;
    private String sender;

    Message(String body, String sender){
        this.body = body;
        this.sender = sender;
    }

    public void setBody(String body) {
        this.body = body;
    }

    String getBody() {
        return body;
    }

    public String getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return body;
    }
}
