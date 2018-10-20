import java.util.LinkedList;

public class MessageQueue implements MessageBroker{

    private static int maxMsg = 1000;
    private LinkedList<MessageForQueue> messageQueue = new LinkedList<MessageForQueue>();

    MessageQueue(int maxMsg){
        this.maxMsg = maxMsg;
    }

    MessageQueue(){  }

    public static void setMaxMsg(int maxMsg) {
        MessageQueue.maxMsg = maxMsg;
    }

    public static int getMaxMsg() {
        return maxMsg;
    }

    public void addMsg(MessageForQueue msg){
        if(messageQueue.size() < maxMsg){
            messageQueue.add(msg);
        }
        else {
            //ignore msg
            System.out.println("Couldn't add message to queue. Please try to remove messages first or increase the message limit.");
        }
    }

    public Message getMessage(){
        Message msg = messageQueue.getFirst();
        messageQueue.removeFirst();
        return msg;
    }

}