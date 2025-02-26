package org.event.core;

import lombok.extern.slf4j.Slf4j;
import org.event.core.topic.Topic;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class EventQueue implements Runnable {
    private static EventQueue instance;
    private final Queue<Object> queue;
    private final Integer MAX_SIZE;
    private final Lock lock;
    private final Condition condition;

    public static EventQueue getInstance() {
        if (instance == null) {
            instance = new EventQueue(10);
        }
        return instance;
    }

    private EventQueue(Integer MAX_SIZE) {
        this.MAX_SIZE = MAX_SIZE;
        this.queue = new ConcurrentLinkedQueue<>();
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();

        Thread thread = new Thread(this, "Consumer-Listener");
//        thread.setDaemon(true);
        thread.start();

    }

    public void offer(Object o) {
        lock.lock();
        if (queue.size() >= MAX_SIZE) {
            log.warn("Queue Size Max");
            return;
        }
        queue.offer(o);
        log.info("Event Queue : {}", queue.toString());
        condition.signal();
        lock.unlock();
    }

    public Object poll() throws InterruptedException {
        lock.lock();
        if (queue.isEmpty()) {
            condition.await();
        }

        Object result = queue.poll();
        this.consumerThreadStart(result);

        lock.unlock();
        return result;
    }

    @Override
    public void run() {
        boolean isLoop = true;
        while (isLoop) {
            try {
                log.info("Consumer Loop");
                this.poll();
            } catch (InterruptedException e) {
                isLoop = false;
            }
        }
    }

    private void consumerThreadStart(Object queuePollData) {
        log.info("consumer Thread Start");
        if (queuePollData instanceof Topic<?> data) {
            log.info("consume : {} {}", data.getTopic(), data.getTopicListener());
            Thread thread = new Thread(() -> {
                data.getTopicListener()
                        .listener(data.getTopic());
            }, "Consumer");

            thread.start();
        }

    }
}
