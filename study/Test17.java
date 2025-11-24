import java.util.LinkedList;
import java.util.Queue;

class Buffer {
    private Queue<Integer> queue = new LinkedList<>();
    private int capacity;
    public Buffer(int capacity) {
        this.capacity = capacity;
    }
    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("缓冲区已满，生产者等待...");
            wait();
        }
        queue.add(item);
        System.out.println("生产者生产了产品: " + item + "，当前库存: " + queue.size());
        notifyAll();
    }
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("缓冲区为空，消费者等待...");
            wait();
        }

        int item = queue.poll();
        System.out.println("消费者消费了产品: " + item + "，当前库存: " + queue.size());
        notifyAll();
        return item;
    }
}

class Producer implements Runnable {
    private Buffer buffer;
    private String name;
    public Producer(Buffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                int item = Integer.parseInt(name.substring(name.length()-1)) * 10 + i;
                buffer.produce(item);
                Thread.sleep(1000); // 模拟生产时间
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(name + "被中断");
        }
    }
}

class Consumer implements Runnable {
    private Buffer buffer;
    private String name;

    public Consumer(Buffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                buffer.consume();
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(name + "被中断");
        }
    }
}

public class Test {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(3);
        Thread producer1 = new Thread(new Producer(buffer, "Producer-1"));
        Thread producer2 = new Thread(new Producer(buffer, "Producer-2"));
        Thread consumer1 = new Thread(new Consumer(buffer, "Consumer-1"));
        Thread consumer2 = new Thread(new Consumer(buffer, "Consumer-2"));

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        try {
            producer1.join();
            producer2.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有生产者和消费者已完成工作");
    }
}class Buffer {
    private Queue<Integer> queue = new LinkedList<>();
    private int capacity;
    public Buffer(int capacity) {
        this.capacity = capacity;
    }
    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("缓冲区已满，生产者等待...");
            wait();
        }
        queue.add(item);
        System.out.println("生产者生产了产品: " + item + "，当前库存: " + queue.size());
        notifyAll();
    }
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("缓冲区为空，消费者等待...");
            wait();
        }

        int item = queue.poll();
        System.out.println("消费者消费了产品: " + item + "，当前库存: " + queue.size());
        notifyAll();
        return item;
    }
}

class Producer implements Runnable {
    private Buffer buffer;
    private String name;
    public Producer(Buffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                int item = Integer.parseInt(name.substring(name.length()-1)) * 10 + i;
                buffer.produce(item);
                Thread.sleep(1000); // 模拟生产时间
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(name + "被中断");
        }
    }
}

class Consumer implements Runnable {
    private Buffer buffer;
    private String name;

    public Consumer(Buffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                buffer.consume();
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(name + "被中断");
        }
    }
}