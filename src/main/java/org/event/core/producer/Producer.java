package org.event.core.producer;

import org.event.core.type.Topic;

public interface Producer {
    void offer(Topic topic);
}
