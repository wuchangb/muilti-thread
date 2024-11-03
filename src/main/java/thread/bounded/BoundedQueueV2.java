package thread.bounded;

import static util.MyLogger.*;
import static util.ThreadUtils.*;

import java.util.ArrayDeque;
import java.util.Queue;

public class BoundedQueueV2 implements BoundedQueue{

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV2(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("[put] 큐가 가득 참, 대기");
            sleep(1000);
            return;
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] 대기");
            sleep(1000);
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
