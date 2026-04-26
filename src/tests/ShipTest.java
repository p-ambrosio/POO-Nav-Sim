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
        Route straight = new Route(List.of(new Ponto(0, 0))); //placeholder until we have the route calculations done
        Ship testerShip = new Ship(straight,5.0,0);

        assertEquals(testerShip.getDepartureTime(),0.0);
    }

    //After t=0 the ship must be exactly at the route's first point (or should) *PASSED*
    @Test
    // movementT0
    public void movementTest0() {
        Route straight = new Route(List.of(new Ponto(0, 0))); //placeholder until we have the route calculations done
        Ship testerShip = new Ship("Idk", straight,5.0,0);
        Ponto position = testerShip.getPosition();
        assertEquals(0.0, position.getX(), 0.001);
        assertEquals(0.0, position.getY(), 0.001);
    }

    // movementHalfway
    @Test
    public void movementTest1(){
        Route straight = new Route(List.of(new Ponto(0, 0))); //placeholder until we have the route calculations done
        Ship testerShip = new Ship("Idk", straight,5.0,0);

        Ponto position = testerShip.getPosition();
        assertEquals(0.0, position.getX(), 0.001);
        assertEquals(0.0, position.getY(), 0.001);
    }

    // movementCrossingSegments
    @Test
    public void movementTest2(){
        Route straight = new Route(List.of(new Ponto(0, 0))); //placeholder until we have the route calculations done
        Ship testerShip = new Ship("Idk", straight,5.0,0);
        Ponto position = testerShip.getPosition();
        assertEquals(0.0, position.getX(), 0.001);
        assertEquals(0.0, position.getY(), 0.001);
    }

    // movementArrived
    @Test
    public void movementTest3(){
        Route straight = new Route(List.of(new Ponto(0, 0))); //placeholder until we have the route calculations done
        Ship testerShip = new Ship("Idk", straight,5.0,0);

        assertTrue(testerShip.hasArrived());
    }

    // movementWaiting
    @Test
    public void movementTest4(){
        Route straight = new Route(List.of(new Ponto(0, 0))); //placeholder until we have the route calculations done
        Ship testerShip = new Ship("Idk", straight,5.0,0);
        Ponto position = testerShip.getPosition();
        assertEquals(0.0, position.getX(), 0.001);
        assertEquals(0.0, position.getY(), 0.001);
    }

    // Check collision w/ ships
    @Test
    public void checkCollisionTest3(){
        Route straight = new Route(List.of(new Ponto(0, 0))); //placeholder until we have the route calculations done
        Ship testerShip = new Ship(straight,5.0,0);
        Ship testerShip2 = new Ship(straight,5.0,0);

        assertTrue(testerShip.isNear(testerShip2));
    }

    @Test
    void setRoute() {
        Route straight = new Route(List.of(new Ponto(0, 0)));
        Ship testerShip = new Ship("Idk", straight,5.0,0);
        testerShip.setRoute(straight);

        assertEquals(testerShip.getCurrentRoute(),straight);
    }

}