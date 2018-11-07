public interface Client {

    String getName();

    Message receiveMessage(Message message);

    void registerServer(Server server);
    void startClient();
}
