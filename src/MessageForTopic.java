public class MessageForTopic extends Message {

    private String type;

    MessageForTopic(String body, String type){
        super(body);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
