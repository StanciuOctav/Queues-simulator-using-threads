package Model;

import Model.Client;
import Model.ConcreteStrategyQueue;
import Model.ConcreteStrategyTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scheduler {

    private List<Queue> queues = Collections.synchronizedList(new ArrayList<>());
    private int maxNoQueues;
    private int maxClientsPerQueue;
    private Strategy strategy = new ConcreteStrategyTime();

    public Scheduler(int maxNoQueues, int maxClientsPerQueue) {
        for (int i = 0; i < maxNoQueues; i++) {
            Queue q = new Queue();
            this.queues.add(q);
            new Thread(q).start();
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }

    }

    public void dispatchClient(Client t) throws InterruptedException {
        this.strategy.addTask(this.queues, t);
    }

    public List<Queue> getQueues() {
        return queues;
    }

}
