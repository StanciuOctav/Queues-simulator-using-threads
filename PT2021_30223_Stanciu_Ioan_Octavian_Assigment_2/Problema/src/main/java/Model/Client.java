package Model;

public class Client {

    private int ID;
    private int arrivalTime;
    private int processingTime;

    public Client(int ID, int arrivalTime, int processingTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }
}
