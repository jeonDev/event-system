package org.event.core.producer.impl;

import org.event.core.EventQueue;
import org.event.core.producer.Producer;

public class ProducerV1 implements Producer {
    private final EventQueue eventQueue;

    public ProducerV1() {
        this.eventQueue = EventQueue.getInstance();
    }

    @Override
    public void offer(Object data) {
        eventQueue.offer(data);
    }
}
