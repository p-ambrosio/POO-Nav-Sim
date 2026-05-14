package tests;

import org.junit.jupiter.api.Test;

import project.*;
import utils.*;
import static org.junit.jupiter.api.Assertions.*;

class RouteGraphingTest {

    @Test
    void routeGraphingConstructorTest0() {
        RouteGraphing rg = new RouteGraphing();

        assertEquals("A", rg.getPort("A").getName());
        assertEquals("B", rg.getPort("B").getName());
        assertEquals("C", rg.getPort("C").getName());
        assertEquals("D", rg.getPort("D").getName());
        assertNull(rg.getPort("Z"));
    }


    @Test
    void routeGraphingConstructorTest1() {
        RouteGraphing rg = new RouteGraphing();
        MovingObstacle[] obs = rg.getMovingObstacle();

        assertNotNull(obs[0].getPosition());
        assertNotNull(obs[1].getPosition());
        // os dois obstáculos devem estar em posições distintas
        assertNotEquals(obs[0].getPosition(), obs[1].getPosition());
    }

    //for static
    @Test
    void routeGraphingConstructorTest2() {
        RouteGraphing rg = new RouteGraphing();

        assertEquals(4, rg.getStaticObstacle().length);
        for (Poligono p : rg.getStaticObstacle()) {
            assertNotNull(p);
        }
    }

    @Test
    void routeGraphingConstructorTest3() {
        RouteGraphing rg = new RouteGraphing();
        Port a = rg.getPort("A");
        Port d = rg.getPort("D");

        Route route = rg.findPath(a, d);
        assertNotNull(route);
        assertTrue(route.getPoints().length >= 2);
    }

    
}