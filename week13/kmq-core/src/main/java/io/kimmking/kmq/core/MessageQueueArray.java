package io.kimmking.kmq.core;

import lombok.Data;

/**
 * 自定义内存Message数组模拟Queue
 *
 * @param <T>
 */
@Data
public class MessageQueueArray<T> {

    private T[] queueArray;
    private int consume_offset;
    private int current_write_position;

    public MessageQueueArray(int size) {
        queueArray = (T[]) new Object[size];
    }

    public boolean enqueue(int index, T item) {
        queueArray[index] = item;
        return true;
    }

    public T dequeue(int index) {
        return queueArray[index];
    }


}
