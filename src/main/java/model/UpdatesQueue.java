package model;

import java.util.concurrent.LinkedBlockingQueue;

public class UpdatesQueue {

    LinkedBlockingQueue<UpdateMessage> queue;
    private static UpdatesQueue instance = new UpdatesQueue();

    private UpdatesQueue() {
        queue = new LinkedBlockingQueue<>();
    }

    public static UpdatesQueue getInstance() {
        return instance;
    }

    public LinkedBlockingQueue<UpdateMessage> getQueue() {
        return queue;
    }

}
