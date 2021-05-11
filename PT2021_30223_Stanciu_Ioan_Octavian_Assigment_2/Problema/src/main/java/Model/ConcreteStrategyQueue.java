package Model;

import Model.Client;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addTask(List<Queue> queues, Client t) throws InterruptedException {

        int poz = 0;

        for (int i = 0; i < queues.size() - 1; i++) {
            if (queues.get(i).getClients().size() < queues.get(i + 1).getClients().size())
                poz = i;
            else
                poz = i + 1;
        }

        queues.get(poz).addClient(t);

    }
}
