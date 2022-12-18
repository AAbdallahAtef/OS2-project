package normalversion;

public class Reader implements Runnable {

    private normalversion.ReadWriteLock lock;

    Reader(normalversion.ReadWriteLock rw) {
        lock = rw;
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.acquireRead();
                System.out.println("Thread " + Thread.currentThread().getName() + " is READING");               //critical section
                Thread.sleep(2000);                                                                     //critical section
                System.out.println("Thread " + Thread.currentThread().getName() + " has FINISHED READING");    //critical section
                lock.releaseRead();
                Thread.sleep(1000); //out of critical section
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
