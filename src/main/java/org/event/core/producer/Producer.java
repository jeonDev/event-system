package org.event.core.producer;

import org.event.core.topic.Topic;

public interface Producer {
    void offer(Topic<?> topic);
}
