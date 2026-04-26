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
        List<Route> routes = new ArrayList<>();
        List<Port> ports = new ArrayList<>();
        RouteGraphing rg = new RouteGraphing(routes, ports);
    }

    @Test
    void findPath() {
        List<Route> routes = new ArrayList<>();
        List<Port> ports = new ArrayList<>();
        RouteGraphing rg = new RouteGraphing(routes, ports);

        assertEquals(routes.get(1),rg.findPath(ports.get(1),ports.get(2)).get(1));
        // suppose the route to get between port 1 and 2 is always going to be route 1
        // ASSUMING no moving obstacle
    }

    //Route that passes through a circle obstacle must be detected as blocked
    @Test
    public void checkCollisionTest0() {
        Route farRoute = new Route(List.of(new Ponto(0, 50), new Ponto(20, 50)));
        List<Route> routes = new ArrayList<>();
        routes.add(farRoute);
        List<Port> ports = new ArrayList<>();
        RouteGraphing rg = new RouteGraphing(routes, ports);

        MovingObstacle circleObstacle = new MovingObstacle(new Ponto(10, 0), 2,new Vetor(1,1)); //Simulation of the actual moving storm

        assertTrue(rg.interceptsMovingObstacle(farRoute,circleObstacle,circleObstacle));
    }

    //Route that does NOT pass through a circle must return no intersection.
    @Test
    public void checkCollisionTest1(){
        Route farRoute = new Route(List.of(new Ponto(0, 50), new Ponto(20, 50)));
        List<Route> routes = new ArrayList<>();
        routes.add(farRoute);
        List<Port> ports = new ArrayList<>();
        RouteGraphing rg = new RouteGraphing(routes, ports);

        MovingObstacle circleObstacle = new MovingObstacle(new Ponto(10, 0), 2,new Vetor(1,1)); //Simulation of the actual moving storm

        assertFalse(rg.interceptsMovingObstacle(farRoute,circleObstacle,circleObstacle));
    }

}