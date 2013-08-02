package monitor.process;

/**
 * Created by Basem
 */
public abstract class ManagedProcess implements Runnable {

    public abstract void start();
    public abstract void shutdown();

    @Override
    public void run() {
        shutdown();
    }
}

