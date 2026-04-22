package project;

public class Schedule {
    private int departureTime;
    private Port destination;
    private double speed;

    public Schedule (int departureTime, Port destination, double speed){
        this.departureTime=departureTime;
        this.destination=destination;
        this.speed=speed;
    }

    //getters
    public int getDepartureTime() { return departureTime; }
    public Port getDestination() { return destination; }
    public double getSpeed() { return speed; }

    //tostring to format??
}
