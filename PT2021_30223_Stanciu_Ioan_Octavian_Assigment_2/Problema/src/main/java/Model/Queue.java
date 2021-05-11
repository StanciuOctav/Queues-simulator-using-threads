package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable {

    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;

    public Queue() {
        this.clients = new LinkedBlockingQueue<>(1000);
        this.waitingPeriod = new AtomicInteger(0);
    }

    public void addClient(Client newClient) {
        this.clients.add(newClient);
        this.waitingPeriod.set(this.waitingPeriod.get() + newClient.getProcessingTime());
    }

    public void run() {
        while (true) {
            try {
                for (Client c : this.clients) {
                    Thread.sleep(c.getProcessingTime() * 1000);
                    this.waitingPeriod.set(this.waitingPeriod.get() - c.getProcessingTime());
                    this.clients.take();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public void setClients(BlockingQueue<Client> clients) {
        this.clients = clients;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }
}
