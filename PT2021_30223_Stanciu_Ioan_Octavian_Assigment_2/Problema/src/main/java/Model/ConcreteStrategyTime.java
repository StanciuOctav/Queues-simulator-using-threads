package Model;

import Model.Client;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public void addTask(List<Queue> queues, Client t) throws InterruptedException {

        // minimul dintre cozi si dupa la coada cu cea mai mica waitingPeriod adaug Clientul

        Queue minim = new Queue();
        minim.setWaitingPeriod(new AtomicInteger(1000000));
        int poz = 0;

        for (int i = 0; i < queues.size(); i++) {
            if (queues.get(i).getWaitingPeriod().get() < minim.getWaitingPeriod().get()) {
                minim.setWaitingPeriod(new AtomicInteger(queues.get(i).getWaitingPeriod().get()));
                poz = i;
            }
        }

        queues.get(poz).addClient(t);
    }
}
