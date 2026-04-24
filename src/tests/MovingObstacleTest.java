package tests;
//  UP TO DATE
import org.junit.jupiter.api.Test;
import utils.*;
import project.*;
import static org.junit.jupiter.api.Assertions.*;

class MovingObstacleTest {

    @Test //*PASSED*
    public void constructorTest(){
        Ponto centro = new Ponto(10, 10);
        Vetor speed = new Vetor(2, 1);
        MovingObstacle obs = new MovingObstacle(centro, 5.0, speed);

        assertEquals(10.0, obs.getCentro().getX(), 0.001);
        assertEquals(10.0, obs.getCentro().getY(), 0.001);
        assertEquals(5.0,  obs.getRaio(), 0.001);

    }

    @Test //* DOES NOT PASS * Pq o circulo nao esta a atualizar o seu centro o mesmo vai acontecer aos próximos tests
    public void positioningNewPositionTest() {
        MovingObstacle obs = new MovingObstacle(new Ponto(0, 0), 5.0, new Vetor(2, 1));
        MovingObstacle repositioned = obs.positioning(3.0);  // time = 3

        // new center = (0 + 2*3, 0 + 1*3) = (6, 3) in theory
        assertEquals(6.0, repositioned.getCentro().getX(), 0.001);
        assertEquals(3.0, repositioned.getCentro().getY(), 0.001);
    }

    @Test
    public void positioningChangesTest(){

    }

    @Test
    public void positioningKeepRaioTest(){

    }

    // *PASSED*
    @Test
    public void getSpeedTest() {
        Vetor speed = new Vetor(2, 1);
        MovingObstacle obs = new MovingObstacle(new Ponto(10, 10), 5.0, speed);

        assertEquals(2.0, obs.getSpeed().getX(), 0.001);
        assertEquals(1.0, obs.getSpeed().getY(), 0.001);
    }
}