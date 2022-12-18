package normalversion;

public class Writer implements Runnable {

    private normalversion.ReadWriteLock lock;

    public Writer(normalversion.ReadWriteLock rw) {
        lock = rw;
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.acquireWrite();
                System.out.println("Thread " + Thread.currentThread().getName() + " is WRITING");               //critical section
                Thread.sleep(2000);                                                                     //critical section
                System.out.println("Thread " + Thread.currentThread().getName() + " has finished WRITING");    //critical section
                lock.releaseWrite();
                Thread.sleep(1000); //out of critical section
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
