package org.event.core.producer.impl;

import lombok.extern.slf4j.Slf4j;
import org.event.core.EventQueue;
import org.event.core.producer.Producer;

@Slf4j
public class ProducerV1 implements Producer {
    private final EventQueue eventQueue;

    public ProducerV1() {
        this.eventQueue = EventQueue.getInstance();
    }

    @Override
    public void offer(Object data) {
        log.info("Queue Data Offer : {}", data.toString());
        eventQueue.offer(data);
    }
}
