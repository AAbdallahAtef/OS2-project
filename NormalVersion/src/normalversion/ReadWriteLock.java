package normalversion;

public class ReadWriteLock {

    protected int readers = 0;
    protected boolean writing = false;
    private int waitingWriters = 0;
    private boolean readersTurn = false;

    synchronized public void acquireRead() {
        while (writing || waitingWriters > 0 && !readersTurn) {
            try {

                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        ++readers;
    }

    synchronized public void releaseRead() {
        --readers;
        readersTurn = false;
        if (readers == 0) {
            notifyAll();
        }
    }

    synchronized public void acquireWrite() {
        while (readers > 0 || writing) {
            ++waitingWriters;
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        --waitingWriters;
        writing = true;
    }

    synchronized public void releaseWrite() {
        writing = false;
        readersTurn = true;
        notifyAll();
    }
}
