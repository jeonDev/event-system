package org.event.core.consumer.impl;

import lombok.extern.slf4j.Slf4j;
import org.event.core.consumer.Consumer;
import org.event.core.consumer.ConsumerListener;

@Slf4j
public class ConsumerListenerV1 implements ConsumerListener {
    private boolean isLoop = true;
    private final Consumer consumer;

    public ConsumerListenerV1(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void listener() {
        while (isLoop) {
            log.info("Loop!");

            try {
                Object data = consumer.poll();
                log.info("Consume : {}", data.toString());
            } catch (InterruptedException e) {
                log.error("Interrupt");
                isLoop = false;
            }

        }
    }

    @Override
    public void run() {
        listener();
    }
}
