public interface MessageBroker{
    Message getMessage();
    void addMsg(Message msg);
}