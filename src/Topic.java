import java.util.LinkedList;

public class Topic implements MessageBroker{

    private LinkedList<MessageForTopic> messageTopic = new LinkedList<MessageForTopic>();

    Topic(){ }

    public void addMsg(Message msg){
        messageTopic.add((MessageForTopic) msg);
    }
    public Message getMessage(int i){
        return messageTopic.get(i);
    }
    public Message getFirstMsg(){
        return messageTopic.getFirst();
    }

    public void removeMessage(){
        messageTopic.removeFirst();
    }

    public int getSize(){
        return messageTopic.size();
    }

    @Override
    public void removeMsg(int i) {
        messageTopic.remove(i);
    }
}