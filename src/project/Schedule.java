package project;
/*
    Dunno if we should this as support so port is less bloated
 */
public class Schedule {
    private int departureTime;
    private Port destination;
    private double speed;

    public Schedule(int departureTime, Port destination, double speed) {
        this.departureTime = departureTime;
        this.destination = destination;
        this.speed = speed;
    }
}
