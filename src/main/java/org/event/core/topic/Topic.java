package org.event.core.topic;

public interface Topic<T> {
    T getTopic();
    TopicListener getTopicListener();
}
