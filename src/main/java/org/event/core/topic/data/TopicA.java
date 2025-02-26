package org.event.core.topic.data;

import org.event.core.topic.TopicListener;
import org.event.core.topic.Topic;
import org.event.core.topic.listener.TopicAListener;

public record TopicA<T> (
        T data
) implements Topic<T> {

    @Override
    public T getTopic() {
        return this.data;
    }

    @Override
    public TopicListener getTopicListener() {
        return TopicAListener.getInstance();
    }
}
