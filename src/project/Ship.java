package project;

import utils.*;


public class Ship {
    private static int id = 0; //
    private Ponto position;
    private double speed;
    private Route currentRoute;
    private int departureTime;
    private boolean isWaiting; //To avoid colisions??
    private boolean arrived;
    private final double radius = 2;

    /*
        Ship constructor
     */
    public Ship(Route route, double speed, int departureTime) {
        id++;
        this.currentRoute = route;
        this.speed = speed;
        this.departureTime = departureTime;

        this.isWaiting = false;
        this.arrived = false;


        this.position = route.getPoints().getFirst(); //to start at the first point of the route its going
    }

    public Route getCurrentRoute() {
        return currentRoute;
    }

    /*
            Will handle movement of ship based on speed and etc
         */
    public void movement (double time, Port p, Vetor v){
        RouteGraphing rg = new RouteGraphing();
        while(!arrived){

            if (getPosition().equals(p.getPosition())) {
                arrived = true;
                break;
            }
        }
    }


    public boolean isNear(Ship other){
        // intercepts code lol
        return this.radius.intercepts(other.radius);
    }

    /*
        Getters and setter?
     */
    public void setRoute(Route r) { this.currentRoute = r;}

    public Ponto getPosition()  { return position; }

    public boolean hasArrived() { return arrived; }

    public int getDepartureTime()  { return departureTime; }

}
