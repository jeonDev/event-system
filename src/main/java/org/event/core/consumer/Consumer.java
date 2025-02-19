package org.event.core.consumer;

public interface Consumer {
    Object poll() throws InterruptedException;
}
