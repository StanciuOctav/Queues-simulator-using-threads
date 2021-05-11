package Model;

import java.util.Comparator;

public class SortClients implements Comparator<Client> {
    @Override
    public int compare(Client o1, Client o2) {
        if (o1.getArrivalTime() < o2.getArrivalTime())
            return -1;
        else if (o1.getArrivalTime() > o2.getArrivalTime())
            return 1;
        return 0;
    }
}
