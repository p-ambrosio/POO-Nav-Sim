package tests;

import org.junit.jupiter.api.Test;
import project.MovingObstacle;
import project.Port;
import project.RouteGraphing;
import utils.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RouteGraphingTest {

    @Test
    void constructorTest(){
        RouteGraphing rg = new RouteGraphing();
    }

    @Test
    void findPath() {
        List<Ponto> pts = new ArrayList<>();
        Ponto p = new Ponto(1,2);
        pts.add(p);
        Route r = new Route(pts);
        Port port1 = new Port("asd",new Ponto(1,1));
        Port port2 = new Port("asd",new Ponto(1,1));
        List<Route> routes = new ArrayList<>();     // insert preset routes
        routes.add(r);
        List<Port> ports = new ArrayList<>();       // insert preset ports
        ports.add(port1);
        ports.add(port2);

        RouteGraphing rg = new RouteGraphing();     // rg already has all preset ports/routes inside

        assertEquals(routes.getFirst(),rg.findPath(ports.get(0),ports.get(1)));
        // suppose the route to get between port 1 and 2 is always going to be route 1
        // ASSUMING no moving obstacle
    }

    //Route that passes through a circle obstacle must be detected as blocked
    @Test
    public void checkCollisionTest0() {
        RouteGraphing rg = new RouteGraphing();         // has all preset routes

        MovingObstacle circleObstacle = new MovingObstacle(new Ponto(10, 0), 2,new Vetor(1,1));
        //Simulation of the actual moving storm

        assertTrue(rg.interceptsMovingObstacle(rg.getRoute(1),circleObstacle,circleObstacle));
    }

    //Route that does NOT pass through a circle must return no intersection.
    @Test
    public void checkCollisionTest1(){
        RouteGraphing rg = new RouteGraphing();         // has all preset routes

        MovingObstacle circleObstacle = new MovingObstacle(new Ponto(10, 0), 2,new Vetor(1,1));
        //Simulation of the actual moving storm

        assertFalse(rg.interceptsMovingObstacle(rg.getRoute(1),circleObstacle,circleObstacle));
    }

    @Test
    public void getPortTest(){
        RouteGraphing rg = new RouteGraphing();         // has all preset routes
        Port p = new Port("asd",new Ponto(1,1));

        assertEquals(rg.getPort(1),p);
    }

    @Test
    public void getRoutesTest(){
        RouteGraphing rg = new RouteGraphing();
        List<Ponto> pts = new ArrayList<>();
        Ponto p = new Ponto(1,2);
        pts.add(p);
        Route r = new Route(pts);

        assertEquals(rg.getRoute(1),r);
    }
}