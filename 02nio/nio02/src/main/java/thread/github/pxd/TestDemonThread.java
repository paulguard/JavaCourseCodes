package thread.github.pxd;

public class TestDemonThread {

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

        Thread thread = new Thread(task);
        thread.setName("test-thread-1");

        //这个地方如果是守护线程，那么进程里所有的线程都是守护线程，则会在进程结束的时候，会清理掉所有线程
        //thread.setDaemon(true);
        thread.start();

    }

}
