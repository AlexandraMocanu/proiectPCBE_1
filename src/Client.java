public interface Client {

    String getName();

    void sendMessage(MessageBroker message);

    MessageBroker buildTopic(String body, String tag);

    MessageBroker buildMessage(String body, String username);
}
