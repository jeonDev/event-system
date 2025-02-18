package org.event;

import org.event.core.producer.Producer;
import org.event.core.producer.impl.ProducerV1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Producer producer = new ProducerV1();
        boolean isLoop = true;

        while (isLoop) {
            Scanner scanner = new Scanner(System.in);
            String data = scanner.next();
            if ("q".equals(data)) {
                isLoop = false;
            }
            producer.offer(data);

        }
    }
}