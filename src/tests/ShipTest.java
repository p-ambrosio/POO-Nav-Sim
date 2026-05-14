package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.*;
import project.*;

import java.util.List;

class ShipTest {

    @Test
    void constructorTest() {
        Port start = new Port("A", new Ponto(0, 0));
        Port dest  = new Port("D", new Ponto(800,800));

        Ship ship = new Ship(start, dest, 2, 0);

        assertEquals(start, ship.getStartingPort());
        assertEquals(dest, ship.getDestinationPort());
        assertEquals(0, ship.getDepartureTime());

        assertFalse(ship.hasArrived());
        assertFalse(ship.isWaiting());
        assertEquals(start.getPosition(), ship.getPosition());
        assertEquals("A0",ship.getTripCode());
    }

    @Test
    void movementTest0() {
        Port start = new Port("A", new Ponto(0, 0));
        Port dest  = new Port("D", new Ponto(800,800));

        Ship ship = new Ship(start, dest, 2, 0);

        // Antes de se mover
        Ponto pos = ship.getPosition();
        assertEquals(0.0, pos.getX(), 0.001);
        assertEquals(0.0, pos.getY(), 0.001);
        while(!ship.hasArrived()){
            ship.movement(2,new Vetor(1,2));
        }
        assertTrue(ship.hasArrived());
        assertEquals(dest.getPosition(), ship.getPosition());
    }

    @Test
    void isNearTest() {
        Port start = new Port("A", new Ponto(0, 0));
        Port dest  = new Port("D", new Ponto(800,800));

        Ship s1 = new Ship(start, dest, 5.0, 0);
        Ship s2 = new Ship(start, dest, 5.0, 1);

        assertTrue(s1.isNear(s2));
    }

    @Test
    void shouldWaitForTest() {
        Port start = new Port("A", new Ponto(0, 0));
        Port dest  = new Port("D", new Ponto(800,800));


        Ship s1 = new Ship(start, dest, 5.0, 0);
        Ship s2 = new Ship(start, dest, 5.0, 10);

        assertFalse(s1.shouldWaitFor(s2));  // s1 = A0: s2 = A10; s1 não deve esperar
        assertTrue(s2.shouldWaitFor(s1));   // s2 deve esperar pelo s1
    }

    @Test
    void startStopWaitingTest() {
        Port start = new Port("A", new Ponto(0, 0));
        Port dest  = new Port("D", new Ponto(800,800));

        Ship ship = new Ship(start, dest, 5.0, 0);
        assertFalse(ship.isWaiting());

        ship.startWaiting();
        assertTrue(ship.isWaiting());

        ship.stopWaiting();
        assertFalse(ship.isWaiting());
    }


}