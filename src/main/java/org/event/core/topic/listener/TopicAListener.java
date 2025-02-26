package org.event.core.topic.listener;

import lombok.extern.slf4j.Slf4j;
import org.event.core.topic.TopicListener;
import org.event.core.topic.data.TopicAData;

@Slf4j
public class TopicAListener implements TopicListener {

    private static final TopicAListener TOPIC_LISTENER = new TopicAListener();

    private TopicAListener() {
    }

    public static TopicListener getInstance() {
        return TOPIC_LISTENER;
    }

    @Override
    public void listener(Object data) {
        TopicAData topicData = (TopicAData) data;
        log.info("TOPIC DATA : {} {}", topicData.a(), topicData.b());
    }
}
