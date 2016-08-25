package com.example.jagdeepsingh.samplepro.executor;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Jagdeep.Singh on 25-07-2016.
 */
public class ProducerThread extends Thread {

    BlockingQueue<String> stringBlockingQueue;

    @Override
    public void run() {
        try {
            stringBlockingQueue.put("1");
            stringBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
