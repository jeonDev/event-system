package org.event.core;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
public class EventQueue {
    private static EventQueue instance;
    private final Queue<Object> queue;
    private final Integer MAX_SIZE;

    public static EventQueue getInstance() {
        if (instance == null) {
            instance = new EventQueue(10);
        }
        return instance;
    }

    private EventQueue(Integer MAX_SIZE) {
        this.queue = new ConcurrentLinkedQueue<>();
        this.MAX_SIZE = MAX_SIZE;
    }

    public void offer(Object o) {
        if (queue.size() >= MAX_SIZE) {
            log.warn("Queue Size Max");
            return;
        }
        queue.offer(o);
        log.info("Event Queue : {}", queue.toString());
    }

    public Object poll() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.poll();
    }

}
