package org.event.core.consumer.impl;

import lombok.extern.slf4j.Slf4j;
import org.event.core.EventQueue;
import org.event.core.consumer.Consumer;

@Slf4j
public class ConsumerV1 implements Consumer {
    private boolean isLoop = true;
    private final EventQueue eventQueue;

    public ConsumerV1(EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void listener() {
        while (isLoop) {
            log.info("Loop!");

            try {
                Object data = eventQueue.poll();
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
