package normalversion;
public class NormalVersion {
     public static void main(String [] args) throws InterruptedException {
            ReadWriteLock RW = new ReadWriteLock();
            Reader read = new Reader(RW);
            Writer write = new Writer(RW);
            Thread t1 = new Thread(read);
            t1.setName("1");
            Thread t2 = new Thread(read);
            t2.setName("2");
            Thread t3 = new Thread(write);
            t3.setName("3");
            Thread t4 = new Thread(read);
            t4.setName("4");
            Thread t5 = new Thread(write);
            t5.setName("5");

            t1.start();
            t3.start();
            t2.start();
            t4.start();
            t5.start();
          }
    
}
