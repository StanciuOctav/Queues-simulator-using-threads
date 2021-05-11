package Model;

import java.util.List;

public interface Strategy {

    void addTask(List<Queue> queues, Client t) throws InterruptedException;

}
