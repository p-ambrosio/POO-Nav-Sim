package project;

import project.*;
import utils.*;

import java.util.List;


public class Ship extends Circulo {
    private final String tripCode;
    private Ponto position;
    private double speed; //constant?
    private Route currentRoute;
    private final int departureTime;
    private boolean isWaiting; //To avoid colisions??
    private boolean arrived;
    private final double radius = 1;
    private final Port startingPort;
    private final Port destinationPort;
    private double elapsedTime =0;
    private Navegacao nav;

    /*
        Ship constructor
     */
    public Ship(Port startingPort, Port destinationPort, double speed,
                int departureTime, Route initialRoute) {

        super(startingPort.getPosition(), 1.0);

        this.speed = speed;
        this.departureTime = departureTime;
        this.startingPort = startingPort;
        this.destinationPort = destinationPort;
        this.isWaiting = false;
        this.arrived = false;
        this.currentRoute = initialRoute;
        this.position = initialRoute.getPoints()[0];
        this.tripCode = startingPort.getName() + departureTime;
    }

    /*
            Will handle movement of ship based on speed and etc
         */
    public void movement(double dt, Vetor corrente) {
        if (arrived || isWaiting) return;

        elapsedTime += dt;

        double totalTime = nav.totalTime(speed);

        // Check if reached destination
        if (elapsedTime >= totalTime) {
            position = currentRoute.getPoints().getLast();
            arrived  = true;
            return;
        }

        // Update position
        position = nav.positionAtTime(elapsedTime, speed);

        this.center = position;
    }


    public boolean isNear(Ship other){
        // intercepts code lol
        return this.intercepts(other); //Circulo intercepts circulo
    }

    //decides which ship has to wait based of the trip code like A12
    public boolean shouldWaitFor(Ship other) {
        return this.tripCode.compareTo(other.tripCode) < 0;
    }

    //for the sim
    public void startWaiting() { isWaiting = true; }
    public void stopWaiting()  { isWaiting = false; }


    /*
        Getters and setter?
     */
    public void setRoute(Route r) {
        this.currentRoute = r;
        this.nav          = new Navegacao(r);
        this.elapsedTime  = 0;
    }
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
