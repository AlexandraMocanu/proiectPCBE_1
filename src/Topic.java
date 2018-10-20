import java.util.LinkedList;

public class Topic implements MessageBroker{

    private LinkedList<MessageForTopic> messageQueue = new LinkedList<MessageForTopic>();

    Topic(){ }

    public void addMsg(Message msg){
        messageQueue.add((MessageForTopic) msg);
    }
    public Message getMessage(){
        return messageQueue.getFirst();
    }

    public void removeMessage(){
        messageQueue.removeFirst();
    }

}