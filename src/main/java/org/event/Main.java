package org.event;

import lombok.extern.slf4j.Slf4j;
import org.event.core.producer.Producer;
import org.event.core.producer.impl.ProducerV1;
import org.event.core.type.Topic;

import java.util.Scanner;

@Slf4j
public class Main {
    public static void main(String[] args) {

        producerMain();
    }

    private static void producerMain() {
        Producer producer = new ProducerV1();
        boolean isLoop = true;

        Scanner scanner = new Scanner(System.in);
        while (isLoop) {
            try {
                log.info("Producer Loop!");
                System.out.print("Topic 입력 : ");
                String topicData = scanner.next();

                if ("q".equals(topicData)) {
                    throw new RuntimeException("q");
                }

                System.out.print("Data 입력 : ");
                String data = scanner.next();

                Topic topic = new Topic(topicData, data);
                producer.offer(topic);

            } catch (Exception e) {
                log.error("탈출 ~~~~~~~~");
                String message = e.getMessage();
                if ("q".equals(message)) isLoop = false;
            }
        }
    }

}