public class DummyMessage extends Message{

    DummyMessage(){}

    DummyMessage(Server sender, Server receiver, int availability, String body){
        super(sender, receiver, availability, body);
    }

}