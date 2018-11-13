public interface MessageBroker{
    Message getMessage(int i);
    Message getFirstMsg();
    void removeMsg(int i);
    void addMsg(Message msg);
    int getSize();
}