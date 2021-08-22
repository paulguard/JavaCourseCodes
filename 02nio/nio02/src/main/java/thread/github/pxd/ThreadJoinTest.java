package thread.github.pxd;

public class ThreadJoinTest {

    public static void main(String[] args) {

        Runnable task = () -> {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread t = Thread.currentThread();
            System.out.println("当前线程:" + t.getName());

        };

        Thread thread1 = new Thread(task);
        thread1.setName("test-thread-1");

        Thread thread2 = new Thread(() -> {
            try {
                Thread t = Thread.currentThread();
                System.out.println("当前线程:" + t.getName());

                thread1.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        thread2.setName("test-thread-2");

        thread1.start();
        thread2.start();
    }

}
