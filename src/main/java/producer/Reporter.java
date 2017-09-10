package producer;

import java.util.List;

import model.Match;
import model.UpdateMessage;
import model.UpdatesQueue;

/**
 * This is a producer. It will write down into the queue the messages.
 */
public class Reporter implements Runnable {

    String name;
    Match match;
    UpdatesQueue updatesQueue;

    public Reporter(String name, Match match, UpdatesQueue queue) {
        this.name = name;
        this.match = match;
        this.updatesQueue = queue;
    }

    @Override
    public void run() {
        System.out.println("Reporter " + name + " started reporting. ");

        long now = System.currentTimeMillis();
        List<UpdateMessage> updates = match.getUpdates();

        for (UpdateMessage um: updates) {
            delayUntilNextUpdate(now, um);
            System.out.println("Write into the queue: " + um.toString());
            updatesQueue.getQueue().add(um);
        }

    }

    public void delayUntilNextUpdate(long now, UpdateMessage um) {

        while (System.currentTimeMillis() < now + um.getTime()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
