package Model;

import View.View;

public class Model {

    private SimulationManager sm;

    public void startSimulation(View view, int minProcessingTime, int maxProcessingTime, int minServiceTime, int maxServiceTime, int numberOfServers, int numberOfClients, int timeLimit){
        this.sm = new SimulationManager( view,  minProcessingTime,  maxProcessingTime,  minServiceTime,  maxServiceTime,  numberOfServers,  numberOfClients,  timeLimit);
        new Thread(this.sm).start();
    }


    public SimulationManager getSm() {
        return sm;
    }

    public void setSm(SimulationManager sm) {
        this.sm = sm;
    }
}
