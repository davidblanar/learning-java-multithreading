import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MyClass3 implements Runnable {
    private final int id = new Random().nextInt(1000);

    @Override
    public void run() {
        System.out.println("Starting task with id " + id);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ending task with id " + id);
    }
}

public class ThreadPools {
    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            ex.submit(new MyClass3());
        }
        ex.shutdown();
        System.out.println("All tasks submitted");
        try {
            ex.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks complete");
    }
}
