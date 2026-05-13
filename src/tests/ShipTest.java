package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.*;
import project.*;

import java.util.List;

class ShipTest {

    @Test
    void constructorTest() {
        Port start = new Port("PortA", new Ponto(0, 0));
        Port dest  = new Port("PortB", new Ponto(10, 10));
        Route route = new Route(List.of(start.getPosition(), dest.getPosition()).toArray(new Ponto[0]));

        Ship ship = new Ship(start, dest, 5.0, 0, route);

        assertEquals(start, ship.getStartingPort());
        assertEquals(dest, ship.getDestinationPort());
        assertEquals(0, ship.getDepartureTime());
        assertEquals(route, ship.getCurrentRoute());
        assertFalse(ship.hasArrived());
        assertFalse(ship.isWaiting());
        assertEquals(start.getPosition(), ship.getPosition());
    }

    @Test
    void movementTest0() {
        Port start = new Port("PortA", new Ponto(0, 0));
        Port dest  = new Port("PortB", new Ponto(10, 10));
        Route route = new Route(List.of(start.getPosition(), dest.getPosition()).toArray(new Ponto[0]));

        Ship ship = new Ship(start, dest, 5.0, 0, route);

        // Antes de se mover
        Ponto pos = ship.getPosition();
        assertEquals(0.0, pos.getX(), 0.001);
        assertEquals(0.0, pos.getY(), 0.001);
    }

    @Test
    void movementTest1() {
        Port start = new Port("PortA", new Ponto(0, 0));
        Port dest  = new Port("PortB", new Ponto(10, 0));
        Route route = new Route(List.of(start.getPosition(), dest.getPosition()).toArray(new Ponto[0]));

        Ship ship = new Ship(start, dest, 5.0, 0, route);
        ship.setRoute(route); // inicializa Navegacao

        // Simula tempo suficiente para chegar ao destino
        ship.movement(10, null);

        assertTrue(ship.hasArrived());
        Ponto pos = ship.getPosition();
        assertEquals(10.0, pos.getX(), 0.001);
        assertEquals(0.0, pos.getY(), 0.001);
    }

    @Test
    void isNearTest() {
        Port start = new Port("PortA", new Ponto(0, 0));
        Port dest  = new Port("PortB", new Ponto(10, 10));
        Route route = new Route(List.of(start.getPosition(), dest.getPosition()).toArray(new Ponto[0]));

        Ship s1 = new Ship(start, dest, 5.0, 0, route);
        Ship s2 = new Ship(start, dest, 5.0, 1, route);

        assertTrue(s1.isNear(s2));
    }

    @Test
    void shouldWaitForTest() {
        Port start = new Port("PortA", new Ponto(0, 0));
        Port dest  = new Port("PortB", new Ponto(10, 10));
        Route route = new Route(List.of(start.getPosition(), dest.getPosition()).toArray(new Ponto[0]));

        Ship s1 = new Ship(start, dest, 5.0, 0, route); // tripCode = PortA0
        Ship s2 = new Ship(start, dest, 5.0, 1, route); // tripCode = PortA1

        assertTrue(s1.shouldWaitFor(s2)); // PortA0 < PortA1
        assertFalse(s2.shouldWaitFor(s1));
    }

    @Test
    void startStopWaitingTest() {
        Port start = new Port("PortA", new Ponto(0, 0));
        Port dest  = new Port("PortB", new Ponto(10, 10));
        Route route = new Route(List.of(start.getPosition(), dest.getPosition()).toArray(new Ponto[0]));

        Ship ship = new Ship(start, dest, 5.0, 0, route);
        assertFalse(ship.isWaiting());

        ship.startWaiting();
        assertTrue(ship.isWaiting());

        ship.stopWaiting();
        assertFalse(ship.isWaiting());
    }

    @Test
    void setRouteTest() {
        Port start = new Port("PortA", new Ponto(0, 0));
        Port dest  = new Port("PortB", new Ponto(10, 10));
        Route route1 = new Route(List.of(start.getPosition(), dest.getPosition()).toArray(new Ponto[0]));
        Route route2 = new Route(List.of(new Ponto(1, 1), new Ponto(2, 2)).toArray(new Ponto[0]));

        Ship ship = new Ship(start, dest, 5.0, 0, route1);
        ship.setRoute(route2);

        assertEquals(route2, ship.getCurrentRoute());
        assertEquals(new Ponto(1, 1), ship.getPosition());
    }
}