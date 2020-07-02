import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class MyClass extends Thread {
    private final int id = new Random().nextInt(10000);

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Running iteration " + i + " in class with id " + id);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BasicThreads {
    public static void main(String[] args) {
        MyClass c1 = new MyClass();
        MyClass c2 = new MyClass();
        // should run the two loops in parallel
        c1.start();
        c2.start();

        System.out.println();
        System.out.println("Running lots of threads");
        System.out.println();
        // what if I create a bunch of them?
        List<MyClass> lst = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            lst.add(new MyClass());
        }
        for (MyClass c : lst) {
            c.start();
        }
    }
}
