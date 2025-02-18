package org.event.core.consumer.impl;

import org.event.core.consumer.Consumer;
import org.event.core.consumer.ConsumerListener;

public class ConsumerListenerV1 implements ConsumerListener {
    private boolean isLoop = true;
    private final Consumer consumer;

    public ConsumerListenerV1(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void listener() {
        while (isLoop) {
            System.out.println("Loop!");
            Object data = consumer.poll();

            if (data != null) {
                System.out.println(data);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                isLoop = false;
            }
        }
    }

    @Override
    public void run() {
        listener();
    }
}
