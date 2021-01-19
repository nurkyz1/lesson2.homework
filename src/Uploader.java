import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
public class Uploader  extends  Thread{

    private Semaphore semaphore;
    private CountDownLatch cdl ;
    private  int mb = 500;
    private int mbs= 20;


    public Uploader(String name, Semaphore semaphore, CountDownLatch cdl  ) {
        super(name);
        this.semaphore = semaphore;
        this.cdl=cdl;

    }
    public  void  run(){

        System.out.println(this.getName()+" загружается на сервер ");
        try {
            semaphore.acquire();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <mb ; i++) {
            System.out.println("...");
            mb -=mbs ;
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }}
            System.out.println(this.getName()+" загружен на сервер ");
             semaphore.release();
             cdl.countDown();


    }
}
