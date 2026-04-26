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
        //TODO
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
        //TODO
    }

    // movementCrossingSegments
    @Test
    public void movementTest2(){
        //TODO
    }

    // movementArrived
    @Test
    public void movementTest3(){
        //TODO
    }

    // movementWaiting
    @Test
    public void movementTest4(){
        //TODO
    }

    //Route that passes through a circle obstacle must be detected as blocked
    @Test
    public void checkCollisionTest0() {
        Route straight;
        straight = new Route(List.of(new Ponto(0, 0), new Ponto(10, 0), new Ponto(10, 10), new Ponto(20, 10)));
        Circulo circleObstacle = new Circulo(new Ponto(10, 0), 2); //Simulation of the actual moving storm
        assertTrue(straight.intersectsFigura(circleObstacle).size() > 0);
    }

    //Route that does NOT pass through a circle must return no intersection.
    @Test
    public void checkCollisionTest1(){
        Route farRoute = new Route(List.of(new Ponto(0, 50), new Ponto(20, 50)));
        Circulo circleObstacle = new Circulo(new Ponto(10, 0), 2); //Simulation of the actual moving storm
        assertTrue(farRoute.intersectsFigura(circleObstacle).isEmpty());
    }

    // Same as Blocked but for land aka Triangle this figure is enough
    @Test
    public void checkCollisionTest2(){
        Route straight;
        straight = new Route(List.of(new Ponto(0, 0), new Ponto(10, 0), new Ponto(10, 10), new Ponto(20, 10)));
        Triangulo triangleObstacle = new Triangulo(new Ponto(8, 4), new Ponto(12, 4), new Ponto(10, 8));
        assertTrue(straight.intersectsFigura(triangleObstacle).size() > 0);
    }

    // Check collision w/ ships
    @Test
    public void checkCollisionTest3(){
        //TODO

    }

    @Test
    void setRoute() {
        Route straight = new Route(List.of(new Ponto(0, 0)));
        Ship testerShip = new Ship("Idk", straight,5.0,0);
        testerShip.setRoute(straight);

        assertEquals(testerShip.getRoute(),straight);
    }

}