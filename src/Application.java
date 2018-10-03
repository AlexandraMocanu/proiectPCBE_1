public class Application {
    public static void main (String args[]){
        Server senderDummyServer = new DummyServer("SenderDummyServer");
        Server receiverDummyServer = new DummyServer("ReceiverDummyServer");

        Message dummyMessage1 = new DummyMessage(senderDummyServer,
                receiverDummyServer,
                60,
                "60 minutes availability");

        Message dummyMessage2 = new DummyMessage();
        dummyMessage2.setAvailability(40);
        dummyMessage2.setBody("50 minutes availability");
        dummyMessage2.setSender(senderDummyServer);
        dummyMessage2.setReceiver(receiverDummyServer);


        System.out.println(dummyMessage1.getBody() + ":\n");
        System.out.println("Sender: " + dummyMessage1.getSender().getName());
        System.out.println("Receiver: " + dummyMessage1.getReceiver().getName());
        System.out.println();
        System.out.println(dummyMessage2.getBody() + ":\n");
        System.out.println("Sender: " + dummyMessage2.getSender().getName());
        System.out.println("Receiver: " + dummyMessage2.getReceiver().getName());

    }
}