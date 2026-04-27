package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.*;
import project.*;

import java.util.List;

class ShipTest {

    //Forgot this silly one before
    @Test
    public void constructorTest(){
        Port startingPort = new Port("asd",new Ponto(1,1));

        Ship testerShip = new Ship(startingPort,5.0,0);

        assertEquals(0.0, testerShip.getDepartureTime());
        assertEquals(startingPort,testerShip.getStartingPort());
    }

    //After t=0 the ship must be exactly at the route's first point
    @Test
    // movementT0
    public void movementTest0() {
        Port startingPort = new Port("asd",new Ponto(0,0));

        Ship testerShip = new Ship(startingPort,5.0,0);
        Ponto position = testerShip.getPosition();

        assertEquals(0.0, position.getX(), 0.001);
        assertEquals(0.0, position.getY(), 0.001);
    }

    // movementCrossingSegments
    @Test
    public void movementTest2(){
        Port startingPort = new Port("asd",new Ponto(1,1));

        RouteGraphing rg = new RouteGraphing();             // has all preset routes
        Ship testerShip = new Ship(startingPort,5.0,0);

        List<Route> routes = rg.findPath(testerShip.getStartingPort(),rg.getPort(1));
        testerShip.movement(2,routes);

        assertEquals(testerShip.getCurrentRoute(),rg.getRoute(2));
    }

    // movementArrived
    @Test
    public void movementTest3(){
        Port startingPort = new Port("asd",new Ponto(1,1));
        RouteGraphing rg = new RouteGraphing();             // has all preset routes
        Ship testerShip = new Ship(startingPort,5.0,0);

        List<Route> routes = rg.findPath(testerShip.getStartingPort(),rg.getPort(1));
        testerShip.movement(2,routes);

        assertTrue(testerShip.hasArrived());
    }

    // Check collision w/ ships
    @Test
    public void checkCollisionTest1(){
        Port startingPort = new Port("asd",new Ponto(1,1));
        Ship testerShip = new Ship(startingPort,5.0,0);
        Ship testerShip2 = new Ship(startingPort,5.0,0);

        assertTrue(testerShip.isNear(testerShip2));
    }

    @Test
    void setRoute() {
        Port startingPort = new Port("asd",new Ponto(1,1));
        Route straight = new Route(List.of(new Ponto(0, 0)));
        Ship testerShip = new Ship(startingPort,5.0,0);
        testerShip.setRoute(straight);

        assertEquals(testerShip.getCurrentRoute(),straight);
    }

    @Test
    void getStartingPortTest(){
        Port p = new Port("asd",new Ponto(1,1));
        Ship testerShip = new Ship(p,5.0,0);

        assertEquals(p,testerShip.getStartingPort());
    }
}