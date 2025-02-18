package org.event.core.consumer.impl;

import org.event.core.EventQueue;
import org.event.core.consumer.Consumer;

public class ConsumerV1 implements Consumer {
    private final EventQueue eventQueue;

    public ConsumerV1() {
        this.eventQueue = EventQueue.getInstance();
    }

    @Override
    public Object poll() {
        return eventQueue.poll();
    }
}
