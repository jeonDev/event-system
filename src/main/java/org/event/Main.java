package org.event;

import org.event.core.consumer.Consumer;
import org.event.core.consumer.ConsumerListener;
import org.event.core.consumer.impl.ConsumerListenerV1;
import org.event.core.consumer.impl.ConsumerV1;
import org.event.core.producer.Producer;
import org.event.core.producer.impl.ProducerV1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Thread thread = consumerListenerStart();
        producerMain(thread);
    }
    private static Thread consumerListenerStart() {
        Consumer consumer = new ConsumerV1();
        ConsumerListener consumerListener = new ConsumerListenerV1(consumer);
        Thread thread = new Thread(consumerListener);
        thread.setDaemon(true);
        thread.start();
        return thread;
    }

    private static void producerMain(Thread thread) {
        Producer producer = new ProducerV1();
        boolean isLoop = true;

        Scanner scanner = new Scanner(System.in);
        while (isLoop) {
            System.out.println("입력 : ");
            String data = scanner.next();
            if ("q".equals(data)) {
                isLoop = false;
            } else if ("s".equals(data)) {
                thread.interrupt();
            }
            producer.offer(data);
        }
    }
}