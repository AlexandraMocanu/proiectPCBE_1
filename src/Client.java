public interface Client extends Runnable {

    String getName();

    void sendMessage();
    Message receiveMessage(Message message);

    MessageForTopic buildTopic(String body, String tag);
    MessageForQueue buildMessage(String body, String username);

    void registerServer(Server server);
    void startClient();
}
