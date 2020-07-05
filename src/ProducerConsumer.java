import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer {
    private final Random random = new Random();
    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void produce() {
        while (true) {
            try {
                queue.put(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer {
    private final Random random = new Random();
    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void consume() {
        while (true) {
            try {
                Thread.sleep(100);

                if (random.nextInt(10) == 0) {
                    int value = queue.take();
                    System.out.println("Took value: " + value + ", queue size: " + queue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        Thread t1 = new Thread(() -> producer.produce());
        Thread t2 = new Thread(() -> consumer.consume());
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
