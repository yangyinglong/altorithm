package queue;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {

    private Item[] items;
    private int maxSize;
    private int front, rear;

    public Queue() {
        this(10);
    }

    public Queue(int initialSize) {
        if (initialSize >= 0) {
            this.maxSize = initialSize;
            items = (Item[]) new Object[initialSize];
            front = rear = 0;
        } else {
            throw new RuntimeException("初始化队列长度不能小于0");
        }
    }

    private void resize(int capacity) {
        assert capacity >= maxSize;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < maxSize; i++) {
            temp[i] = items[i];
        }
        items = temp;
        maxSize = maxSize * 2;
    }

    private void reindex(int index) {
        for (int i = front; i < rear; i++) {
            items[i-front] = items[i];
        }
        rear = rear - front;
        front = 0;
    }

    public void enqueue(Item item) {
        if (rear == maxSize) {
            // 队列已满，重新分配空间
            resize(2*maxSize);
        }
        items[rear++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            return null;
        }
        Item item = items[front];
        items[front++] = null;
        if (front > 10) {
            reindex(front);
        }
        return item;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public int size() {
        return rear-front;
    }


    @Override
    public Iterator<Item> iterator() {
        class QueueIter implements Iterator<Item> {

            private int cur = front;

            @Override
            public boolean hasNext() {
                return cur < rear;
            }

            @Override
            public Item next() {
                Item item = items[cur];
                cur++;
                return item;
            }
        }
        return new QueueIter();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>();
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(40);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(80);
        queue.enqueue(9);
        queue.enqueue(10);
        queue.enqueue(11);
        queue.enqueue(12);
        queue.enqueue(13);
        queue.enqueue(14);
        queue.enqueue(15);
        queue.enqueue(16);
        queue.enqueue(17);

        Iterator iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        queue.enqueue(18);
        queue.enqueue(19);
    }

}
