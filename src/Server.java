import java.util.concurrent.*;

//https://codereview.stackexchange.com/questions/38557/concurrent-multi-server-pinging-in-java
//TODO:See above for example of pinging (check out user messages)


public abstract class Server{

    //TODO:We also need a user input reader (with menu)

    private String name = "Server";

    Server(String name){
        this.name = name;
        final ExecutorService executorService = Executors.newFixedThreadPool(4);
        //return value is null if run was succesful
        Future<?> runResult = executorService.submit(new User());


        BlockingQueue<Runnable> work =
                new ArrayBlockingQueue<Runnable>(5);

        //or we could use Callable instead of Runnable
//        executorService.submit()

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sendMessage(Message msg){

    }

    public void receiveMessage(Message msg){

    }
}