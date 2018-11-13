import java.util.LinkedList;

public class Queue implements MessageBroker{

    private static int maxMsg = 1000;
    private LinkedList<MessageForQueue> messageQueue = new LinkedList<MessageForQueue>();

    Queue(int maxMsg){
        this.maxMsg = maxMsg;
    }

    Queue(){  }

    public static void setMaxMsg(int maxMsg) {
        Queue.maxMsg = maxMsg;
    }

    public static int getMaxMsg() {
        return maxMsg;
    }

    public void addMsg(Message msg){
        if(messageQueue.size() < maxMsg){
            messageQueue.add((MessageForQueue)msg);
        }
        else {
            //ignore msg
            System.out.println("Couldn't add message to queue. Please try to remove messages first or increase the message limit.");
        }
    }

    public Message getMessage(int i){
        return messageQueue.get(i);
    }

    public Message getFirstMsg(){
        Message msg = messageQueue.getFirst();
        messageQueue.removeFirst();
        return msg;
    }

    @Override
    public int getSize() {
         return messageQueue.size();
    }

    @Override
    public void removeMsg(int i) {
        messageQueue.remove(i);
    }
}