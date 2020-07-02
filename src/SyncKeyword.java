class MyClass2 {
    private int count = 0;
    private int count2 = 0;
    private int count3 = 0;

    public void doStuff() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        });
        t1.start();
        t2.start();
        System.out.println("Count: " + count); // will sometimes report inaccurate number
    }

    public void useSyncKeyword() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                incCount2();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                incCount2();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Count2: " + count2);
    }

    public void useSyncBlock() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (this) {
                    count3++;
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (this) {
                    count3++;
                }
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Count3: " + count3);
    }

    private synchronized void incCount2() {
        count2++;
    }
}

public class SyncKeyword {
    public static void main(String[] args) {
        MyClass2 c = new MyClass2();
        c.doStuff();
        c.useSyncKeyword();
        c.useSyncBlock();
    }
}
