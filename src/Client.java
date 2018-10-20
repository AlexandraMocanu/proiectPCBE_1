public interface Client extends Runnable{

    String getName();

    void sendMessage(MessageBroker message);
    Message receiveMessage();

    MessageForTopic buildTopic(String body, String tag);
    MessageForQueue buildMessage(String body, String username);

    void registerToServer();

}
