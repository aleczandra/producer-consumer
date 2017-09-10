package consumer;

import java.util.concurrent.TimeUnit;

import model.UpdatesQueue;

/**
 * This is a consumer. It has to wait and read from the updatesQueue.
 */
public class Teletype  implements Runnable {


    UpdatesQueue updatesQueue;

    public Teletype(UpdatesQueue queue) {
        this.updatesQueue = queue;
    }

    @Override
    public void run() {
        System.out.println("Teletype started recording");
        while (true) {
            try {
                System.out.println(updatesQueue.getQueue().take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
