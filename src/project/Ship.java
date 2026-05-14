package project;

import utils.*;

import java.util.List;


public class Ship extends Circulo {
    private final String tripCode;
    private Ponto position;
    private final double speed; //constant?
    private final Route currentRoute;
    private final int departureTime;
    private boolean isWaiting; //To avoid colisions??
    private boolean arrived;
    private final double radius = 50;
    private final Port startingPort;
    private final Port destinationPort;
    private double elapsedTime =0;
    private boolean isNear=false;
    private final Navegador nav;

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
    public void movement(double dt) {
        if (arrived || isWaiting) return;

        elapsedTime += dt;

        double totalTime = nav.time(speed);

        // Check if reached destination
        if (elapsedTime >= totalTime) {
            position = currentRoute.getPoints()[currentRoute.getPoints().length - 1];
            arrived  = true;
            return;
        }

        // Update position
        position = nav.position(elapsedTime, speed);

        this.center = position;
    }

    public void update(double dt, List<Ship> ships) {

        for (Ship s : ships) {
            s.stopWaiting();
        }

        for (int i = 0; i < ships.size(); i++) {
            for (int j = i + 1; j < ships.size(); j++) {
                Ship a = ships.get(i);
                Ship b = ships.get(j);

                if (a.hasArrived() || b.hasArrived()) {
                    continue;
                }
                if (a.isNear(b)) {
                    if (a.shouldWaitFor(b)) {
                        a.startWaiting();
                    } else {
                        b.startWaiting();
                    }
                }
            }
        }

        // movement phase
        for (Ship s : ships) {
            s.movement(dt);
        }
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
