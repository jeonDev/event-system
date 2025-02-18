package org.event.core;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

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
            System.out.println("Queue Size Max");
            return;
        }
        queue.offer(o);
        System.out.println(queue.toString());
    }

    public Object poll() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.poll();
    }

    public void log() {
        System.out.println(queue.toString());
    }
}
