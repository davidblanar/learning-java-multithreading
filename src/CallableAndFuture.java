import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CallableAndFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(5);
        List<Future<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Future future = ex.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Random random = new Random();
                    int result = random.nextInt(1000);
                    // simulate work
                    Thread.sleep(2000);
                    return result;
                }
            });
            list.add(future);
        }
        ex.shutdown();
        // ex.awaitTermination(1, TimeUnit.HOURS);

        for (Future future : list) {
            System.out.println(future.get());
        }
        System.out.println("Done");
    }
}
