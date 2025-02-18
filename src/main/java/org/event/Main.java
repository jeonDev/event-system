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

        consumerListenerStart();
        producerMain();
    }
    private static void consumerListenerStart() {
        Consumer consumer = new ConsumerV1();
        ConsumerListener consumerListener = new ConsumerListenerV1(consumer);
        Thread thread = new Thread(consumerListener);
        thread.setDaemon(true);
        thread.start();
    }

    private static void producerMain() {
        Producer producer = new ProducerV1();
        boolean isLoop = true;

        Scanner scanner = new Scanner(System.in);
        while (isLoop) {
            System.out.println("입력 : ");
            String data = scanner.next();
            if ("q".equals(data)) {
                isLoop = false;
            }
            producer.offer(data);
        }
    }
}