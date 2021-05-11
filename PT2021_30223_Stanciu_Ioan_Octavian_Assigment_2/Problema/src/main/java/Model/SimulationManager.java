package Model;

import View.View;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SimulationManager implements Runnable {

    public int timeLimit;

    public int maxProcessingTime;
    public int minProcessingTime;

    public int maxServiceTime;
    public int minServiceTime;

    public int numberOfServers;
    public int numberOfClients;

    public View view;

    public double averageServiceTime;
    public double averageWaitingTime;
    public static int peakHour = 0;
    public static int maxiAllTime = 0;

    private Scheduler scheduler;
    private List<Client> generatedClients = Collections.synchronizedList(new ArrayList<>());

    public SimulationManager(View view,  int minProcessingTime, int maxProcessingTime, int minServiceTime, int maxServiceTime, int numberOfServers, int numberOfClients, int timeLimit) {

        this.view = view;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.timeLimit = timeLimit;
        this.averageWaitingTime = 1.0 / this.numberOfClients;

        scheduler = new Scheduler(numberOfServers, numberOfClients);
        generateNRandomClients();
    }

    private void generateNRandomClients() {
        Random r = new Random();
        for (int i = 0; i < this.numberOfClients; i++) {
            int processTime = r.nextInt(this.maxServiceTime);
            int arrivalTime = r.nextInt(this.maxProcessingTime);

            if (processTime < this.minServiceTime)
                processTime += this.minServiceTime;

            if (arrivalTime < this.minProcessingTime)
                arrivalTime += this.minProcessingTime;

            this.generatedClients.add(new Client(i + 1, arrivalTime, processTime));
        }
        this.generatedClients.sort(new SortClients());
        for(int i=0;i<this.generatedClients.size();i++)
            averageServiceTime += this.generatedClients.get(i).getProcessingTime();
        averageServiceTime = averageServiceTime / this.generatedClients.size();
    }

    @Override
    public void run() {
        int currentTime = 0;
        FileWriter f = null;
        try {
            f = new FileWriter("E:\\1. Facultate\\AN 2\\SEM 2\\Tehnici de programare fundamentale - Tudor Cioara\\PT2021_30223_Stanciu_Ioan_Octavian_Assigment_2\\Problema\\src\\main\\java\\Results.txt", true);

            while (currentTime < this.timeLimit) {

                view.setTime(currentTime);
                for (int i = 0; i < this.generatedClients.size(); i++) {
                    if (this.generatedClients.get(i).getArrivalTime() == currentTime) {
                        try {
                            this.scheduler.dispatchClient(this.generatedClients.get(i));
                            this.generatedClients.remove(this.generatedClients.get(i));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        i--;
                    }
                }

                try {


                    f.write("TIME: " + currentTime + "\n");

                    //// ------------- SCRIERE IN FISIER SI GUI PENTRU CLIENTI ------------- ////
                    String x = "";
                    if (this.generatedClients.size() == 0)
                        view.setRemainingClients("");
                    f.write("Waiting clients: ");
                    for (Client c : this.generatedClients) {
                        f.write(" ( " + c.getID() + " , " + c.getArrivalTime() + " , " + c.getProcessingTime() + " ) ");
                        x = x + " ( " + c.getID() + " , " + c.getArrivalTime() + " , " + c.getProcessingTime() + " ) ";
                        view.setRemainingClients(x);
                    }
                    f.write("\n");

                    //// ------------- SCRIERE IN FISIER PENTRU COZI ---------------- ////
                    for (int i = 0; i < this.scheduler.getQueues().size(); i++) {
                        f.write("Queue " + (i + 1) + ": ");
                        averageWaitingTime += this.scheduler.getQueues().get(i).getWaitingPeriod().get();

                        if(this.scheduler.getQueues().get(i).getClients().size() > 0) {
                            int firstClient = 0;
                            for (Client c : this.scheduler.getQueues().get(i).getClients()) {
                                if (c.getProcessingTime() != 0)
                                    f.write("(" + c.getID() + " , " + c.getArrivalTime() + " , " + c.getProcessingTime() + ")");

                                if (firstClient == 0)
                                    c.setProcessingTime(c.getProcessingTime() - 1);
                                firstClient++;
                            }
                        }
                        else
                            f.write("CLOSED");
                        f.write("\n");
                    }

                    //// ------------- SCRIERE IN GUI PENTRU COZI ---------------- ////
                    int nrQ = this.numberOfServers;
                    if(nrQ == 1){
                        view.setQueue1(getClientsInQueue(this.scheduler.getQueues().get(0)));
                    }
                    else
                    if(nrQ == 2){
                        view.setQueue1(getClientsInQueue(this.scheduler.getQueues().get(0)));
                        view.setQueue2(getClientsInQueue(this.scheduler.getQueues().get(1)));
                    }
                    else
                    if(nrQ == 3){
                        view.setQueue1(getClientsInQueue(this.scheduler.getQueues().get(0)));
                        view.setQueue2(getClientsInQueue(this.scheduler.getQueues().get(1)));
                        view.setQueue3(getClientsInQueue(this.scheduler.getQueues().get(2)));
                    }
                    else
                    if(nrQ == 4){
                        view.setQueue1(getClientsInQueue(this.scheduler.getQueues().get(0)));
                        view.setQueue2(getClientsInQueue(this.scheduler.getQueues().get(1)));
                        view.setQueue3(getClientsInQueue(this.scheduler.getQueues().get(2)));
                        view.setQueue4(getClientsInQueue(this.scheduler.getQueues().get(3)));
                    }

                    int maxi = 0;
                    for(int i=0;i<this.scheduler.getQueues().size();i++)
                        maxi += this.scheduler.getQueues().get(i).getClients().size();
                    if(maxi > this.maxiAllTime) {
                        this.peakHour = currentTime;
                        this.maxiAllTime = maxi;
                    }

                    f.write("\n\n");

                    currentTime++;
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            f.write("Average Waiting Time: " + averageWaitingTime/(this.numberOfClients * this.timeLimit) + "\n" +"Average Service time: " + averageServiceTime + "\n" + "Peak hour: " + peakHour  + "\n");
            f.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClientsInQueue(Queue q) {
        if (q.getClients().size() == 0)
            return "CLOSED";
        else {
            String x = "";
            for (Client c : q.getClients())
                if (c.getProcessingTime() != 0)
                    x = x + " ( " + c.getID() + " , " + c.getArrivalTime() + " , " + c.getProcessingTime() + " ) ";
            return x;
        }
    }

    public static void main(String[] args) {
        //View view = new View();
        //new Thread(new SimulationManager( 10, 100, 3,9,20,1000,200)).start();
    }
}
