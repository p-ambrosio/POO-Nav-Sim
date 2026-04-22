package project;

import utils.*;


public class Ship {
    private String id; // Ex: "A12"
    private Ponto position;
    private double speed;
    private Route currentRoute;
    private int departureTime;
    private boolean isWaiting; //To avoid colisions
    private boolean arrived;

    /*
        Ship constructor
     */
    public Ship(String id, Route route, double speed, int departureTime) {
        this.id = id;
        this.currentRoute = route;
        this.speed = speed;
        this.departureTime = departureTime;

        this.isWaiting = false;
        this.arrived = false;

        this.position = route.getPoints().getFirst(); //to start at the first point of the route its going
    }

    /*
        Will handle movement of ship based on speed and etc
     */
    public void movement (double dt, double x, double y){

    }

    /*
        Will handle the checking if a ship is near an obstacle moving or not
     */
    public void checkCollision(Ship obstacle){

    }



    /*
        Getters and setter?
     */
    public void setRoute(Route r) { this.currentRoute = r;}

    public Ponto getPosition()  { return position; }

    public boolean hasArrived() { return arrived; }

}
