package org.event;

import lombok.extern.slf4j.Slf4j;
import org.event.core.producer.Producer;
import org.event.core.producer.impl.ProducerV1;
import org.event.core.type.Topic;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Producer producer = new ProducerV1();
        Topic topic = new Topic("A", "asdf");
        producer.offer(topic);
    }

}