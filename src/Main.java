import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        CountDownLatch sdl = new CountDownLatch(1);
        Semaphore semaphore = new Semaphore(1);
        new Uploader( "Файл ",semaphore ,sdl).start();
        try {
            sdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CountDownLatch cdl = new CountDownLatch(10);
        Semaphore semaphore1 = new Semaphore(3);
        for (int i = 1; i <11 ; i++) {
            new Downloaders("Челоовек " , semaphore1,cdl).start();
            System.out.println(i+  " человек скачал файл ");
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(" Файл будет удален с сервера ");
    }
}
