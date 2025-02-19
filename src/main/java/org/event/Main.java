package org.event;

import lombok.extern.slf4j.Slf4j;
import org.event.core.consumer.Consumer;
import org.event.core.consumer.ConsumerListener;
import org.event.core.consumer.impl.ConsumerListenerV1;
import org.event.core.consumer.impl.ConsumerV1;
import org.event.core.producer.Producer;
import org.event.core.producer.impl.ProducerV1;
import org.event.core.type.Topic;

import java.util.Scanner;

@Slf4j
public class Main {
    public static void main(String[] args) {

        Thread thread = consumerListenerStart();
        producerMain(thread);
    }
    private static Thread consumerListenerStart() {
        Consumer consumer = new ConsumerV1();
        ConsumerListener consumerListener = new ConsumerListenerV1(consumer);
        Thread thread = new Thread(consumerListener);
        thread.start();
        return thread;
    }

    private static void producerMain(Thread thread) {
        Producer producer = new ProducerV1();
        boolean isLoop = true;

        Scanner scanner = new Scanner(System.in);
        while (isLoop) {
            try {
                log.info("Producer Loop!");
                System.out.print("Topic 입력 : ");
                String topicData = scanner.next();
                exit(topicData);

                System.out.print("Data 입력 : ");
                String data = scanner.next();

                Topic topic = new Topic(topicData, data);
                producer.offer(topic);

            } catch (Exception e) {
                log.error("탈출 ~~~~~~~~");
                String message = e.getMessage();
                if ("q".equals(message)) isLoop = false;
                if ("s".equals(message)) thread.interrupt();
            }
        }
    }

    private static void exit(String data) {
        if ("q".equals(data)) {
            throw new RuntimeException("q");
        } else if ("s".equals(data)) {
            throw new RuntimeException("s");
        }
    }
}