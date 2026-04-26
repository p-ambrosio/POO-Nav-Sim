package tests;
//  UP TO DATE
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import utils.*;
import project.*;

import java.lang.reflect.Executable;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class MovingObstacleTest {

    @Test
    public void constructorTest(){
        Ponto centro = new Ponto(10, 10);
        Vetor speed = new Vetor(2, 1);
        MovingObstacle obs = new MovingObstacle(centro, 5.0, speed);

        assertEquals(10.0, obs.getCentro().getX(), 0.001);
        assertEquals(10.0, obs.getCentro().getY(), 0.001);
        assertEquals(5.0,  obs.getRaio(), 0.001);
    }

    @Test
    public void positioningTest1() {
        MovingObstacle obs = new MovingObstacle(new Ponto(0, 0), 5.0, new Vetor(2, 1));
        obs.positioning(3.0);            // time = 3

        // obs.getCenter gets the circle position
        assertNotEquals(0, obs.getCentro().getX(), 0.001);// it moved
        assertNotEquals(0, obs.getCentro().getY(), 0.001);
    }

    // function obs.move is a loop running positioning with a wait time of wait(500)
    @Test
    public void moveTest(){
        MovingObstacle obs = new MovingObstacle(new Ponto(0, 0), 5.0, new Vetor(2, 1));

        assertTimeout(Duration.ofMillis(600), obs::move);

        assertNotEquals(0, obs.getCentro().getX(), 0.001);      // it moved
        assertNotEquals(0, obs.getCentro().getY(), 0.001);
    }

    @Test
    public void getSpeedTest() {
        Vetor speed = new Vetor(2, 1);
        MovingObstacle obs = new MovingObstacle(new Ponto(10, 10), 5.0, speed);

        assertEquals(2.0, obs.getSpeed().getX(), 0.001);
        assertEquals(1.0, obs.getSpeed().getY(), 0.001);
    }

}