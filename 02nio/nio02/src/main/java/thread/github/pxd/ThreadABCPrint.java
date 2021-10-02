package thread.github.pxd;

public class ThreadABCPrint {

    volatile static int completeThread = 0;

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            while (true){

                if (completeThread == 3 || completeThread == 0) {
                    System.out.print("A");
                    completeThread = 1;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true){

                if (completeThread == 1) {
                    System.out.print("B");
                    completeThread = 2;
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            while (true){

                if (completeThread == 2) {
                    System.out.print("C");
                    completeThread = 3;
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
