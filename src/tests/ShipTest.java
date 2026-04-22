package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.*;
import project.*;

import java.util.List;

class ShipTest {


    //After t=0 the ship must be exactly at the route's first point (or should) *PASSED*
    @Test
    public void movementT0() {
        Route straight = new Route(List.of(new Ponto(0, 0))); //placeholder until we have the route calculations done
        Ship testerShip = new Ship("Idk", straight,5.0,0);
        Ponto position = testerShip.getPosition();
        assertEquals(0.0, position.getX(), 0.001);
        assertEquals(0.0, position.getY(), 0.001);
    }

    @Test
    public void movementHalfway(){
        //TODO

    }

    @Test
    public void movementCrossingSegments(){
        //TODO

    }

    @Test
    public void movementArrived(){
        //TODO
    }

    @Test
    public void movementWaiting(){
        //TODO
    }

    //Route that passes through a circle obstacle must be detected as blocked *PASSED*
    @Test
    public void checkCollisionBlocked() {
        Route straight;
        straight = new Route(List.of(new Ponto(0, 0), new Ponto(10, 0), new Ponto(10, 10), new Ponto(20, 10)));
        Circulo circleObstacle = new Circulo(new Ponto(10, 0), 2); //Simulation of the actual moving storm
        assertTrue(straight.intersectsFigura(circleObstacle).size() > 0);
    }

    //Route that does NOT pass through a circle must return no intersection. *PASSED*
    @Test
    public void checkCollisionPassed(){
        Route farRoute = new Route(List.of(new Ponto(0, 50), new Ponto(20, 50)));
        Circulo circleObstacle = new Circulo(new Ponto(10, 0), 2); //Simulation of the actual moving storm
        assertTrue(farRoute.intersectsFigura(circleObstacle).isEmpty());
    }

    // Same as Blocked but for land aka Triangle this figure is enough imo *PASSED*
    @Test
    public void checkCollisionLand(){
        Route straight;
        straight = new Route(List.of(new Ponto(0, 0), new Ponto(10, 0), new Ponto(10, 10), new Ponto(20, 10)));
        Triangulo triangleObstacle = new Triangulo(new Ponto(8, 4), new Ponto(12, 4), new Ponto(10, 8));
        assertTrue(straight.intersectsFigura(triangleObstacle).size() > 0);
    }

    @Test
    public void checkCollision2Ships(){
        //TODO

    }

    @Test
    void setRoute() {
        //TODO
    }
}