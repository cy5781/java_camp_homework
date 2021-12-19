package io.kimmking.kmq.core;

import lombok.SneakyThrows;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class Kmq {

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        //this.queue = new LinkedBlockingQueue(capacity);
        this.queue = new MessageQueueArray(capacity);
    }

    private String topic;

    private int capacity;

    //private LinkedBlockingQueue<KmqMessage> queue;
    private MessageQueueArray<KmqMessage> queue;

    public boolean send(int index, KmqMessage message) {
        return queue.enqueue(index, message);
    }

    public KmqMessage poll(int index) {
        return queue.dequeue(index);
    }

    @SneakyThrows
    public KmqMessage poll(long timeout,int index) {
        return queue.dequeue(index);
    }

}
