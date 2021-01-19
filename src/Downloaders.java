import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Downloaders  extends  Thread{

  private   Semaphore semaphore ;
    private CountDownLatch cdl;
    private int mb = 500;
    private int mbs = 100;

    public Downloaders(String name, Semaphore semaphore, CountDownLatch cdl) {
        super(name);
        this.semaphore = semaphore;
        this.cdl = cdl;
    }
    public  void run(){
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( this.getName()+" ожидает загрузку ");
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName()+" начал загрузку с сервера ");
        for (int i = 0; i <mb ; i++) {
            mb -= mbs ;
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName()+" завершил загрузку ");
            cdl.countDown();
            semaphore.release();
        }
    }
}
