public class Message {
    private String body;

    Message(String body){
        this.body = body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return body;
    }
}
