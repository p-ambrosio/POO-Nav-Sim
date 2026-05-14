package project;

import utils.*;

import java.util.List;


public class Ship extends Circulo {
    private final String tripCode;
    private Ponto position;
    private final double speed; //constant?
    private final Route currentRoute;
    private int departureTime;
    private boolean isWaiting; //To avoid colisions??
    private boolean arrived;
    private final double radius = 100;
    private final Port startingPort;
    private final Port destinationPort;
    private double elapsedTime =0;
    private boolean isNear=false;
    private final Navegador nav;
    private double travelledDistance = 0;

    RouteGraphing rg = new RouteGraphing();
    /*
        Ship constructor
     */
    public Ship(Port startingPort, Port destinationPort, double speed,
                int departureTime) {

        super(startingPort.getPosition(), 1.0);

        this.speed = speed;
        this.departureTime = departureTime;
        this.startingPort = startingPort;
        this.destinationPort = destinationPort;
        this.isWaiting = false;
        this.arrived = false;
        this.currentRoute = rg.findPath(startingPort,destinationPort);
        this.position = currentRoute.getPoints()[0];
        this.tripCode = startingPort.getName() + departureTime;
        this.nav = new Navegador(this.currentRoute);
    }
    /*
        Will handle movement of ship based on speed and etc
     */
    public void movement(double dt, Vetor current) {
        if (arrived || isWaiting) return;

        double currentEffect = current.moduloPosicao();

        travelledDistance += speed * currentEffect * dt;

        double totalDistance = currentRoute.findDistance();

        // Check if reached destination
        if (travelledDistance >= totalDistance) {
            position =currentRoute.getPoints()[currentRoute.getPoints().length - 1];
            arrived = true;
            return;
        }

        // Update position
        position = nav.positionByDistance(travelledDistance);

        this.center = position;
    }

    public boolean isNear(Ship other) {
        double dx = this.center.getX() - other.center.getX();
        double dy = this.center.getY() - other.center.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);
        return dist <= this.radius+other.radius; // circles touch when dist <= 1+1
    }

    //decides which ship has to wait based of the trip code like A12
    public boolean shouldWaitFor(Ship other) {
        return this.tripCode.compareTo(other.tripCode) > 0;
    }

    public void downDepartureTime(){
        departureTime--;
    }

    //for the sim
    public void startWaiting() { isWaiting = true; }
    public void stopWaiting()  { isWaiting = false; }

    /*
        Getters and setter?
     */
    public Ponto   getPosition()      { return position; }
    public boolean hasArrived()       { return arrived; }
    public boolean isWaiting()        { return isWaiting; }
    public String  getTripCode()      { return tripCode; }
    public int     getDepartureTime() { return departureTime; }
    public double  getSpeed()         { return speed; }
    public Route   getCurrentRoute()  { return currentRoute; }
    public Port    getStartingPort()  { return startingPort; }
    public Port    getDestinationPort(){ return destinationPort; }


    //no need for tostring??

}
