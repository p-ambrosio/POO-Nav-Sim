package tests;
//  UP TO DATE
import org.junit.jupiter.api.Test;
import utils.*;
import project.*;
import static org.junit.jupiter.api.Assertions.*;

class MovingObstacleTest {

    @Test
    public void constructorTest(){

        Ponto centro = new Ponto(10, 10);

        MovingObstacle obs = new MovingObstacle(centro, 5.0);
        assertEquals(10.0, obs.getCentro().getX(), 0.001);
        assertEquals(10.0, obs.getCentro().getY(), 0.001);
        assertEquals(5.0, obs.getRaio(), 0.001);
    }

    @Test
    public void positioningTest1() {

        MovingObstacle obs = new MovingObstacle(new Ponto(0, 0), 5.0);
        obs.positioning(0);

        assertEquals(480.0, obs.getCentro().getX(), 0.001);
        assertEquals(400.0, obs.getCentro().getY(), 0.001);
    }

    @Test
    public void positioningTest2() {
        MovingObstacle obs = new MovingObstacle(new Ponto(0, 0), 5.0);

        obs.positioning(1);

        assertEquals(120.0, obs.getCentro().getX(), 0.001);

        assertEquals(190.0, obs.getCentro().getY(), 0.001);
    }

    @Test
    public void nextSimulationTest0() {

        MovingObstacle obs = new MovingObstacle(new Ponto(0, 0), 5.0);

        obs.positioning(0);

        double firstX = obs.getCentro().getX();

        MovingObstacle.nextSimulation();
        obs.positioning(0);
        double secondX = obs.getCentro().getX();

        assertNotEquals(firstX, secondX, 0.001);
    }

    @Test
    public void nextSimulationTest1() {

        MovingObstacle obs = new MovingObstacle(new Ponto(0, 0), 5.0);
        obs.positioning(0);
        double initialX = obs.getCentro().getX();

        for(int i = 0; i < 4; i++) {
            MovingObstacle.nextSimulation();
        }

        obs.positioning(0);
        assertEquals(initialX,obs.getCentro().getX(), 0.001);
    }

}