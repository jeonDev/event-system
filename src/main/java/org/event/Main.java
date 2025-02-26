package org.event;

import lombok.extern.slf4j.Slf4j;
import org.event.core.topic.data.TopicAData;
import org.event.core.topic.Topic;
import org.event.core.topic.data.TopicA;
import org.event.core.producer.Producer;
import org.event.core.producer.impl.ProducerV1;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Producer producer = new ProducerV1();
        Topic<TopicAData> topic = new TopicA<>(new TopicAData("A", "AA"));
        producer.offer(topic);
    }

}