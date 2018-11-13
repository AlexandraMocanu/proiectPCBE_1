public class MessageForTopic extends Message {

    private Long expire;
    private String type;

    MessageForTopic(String body, Long expire, String type){
        super(body);
        this.expire = expire;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }
}
