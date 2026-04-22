package tests;

import org.junit.jupiter.api.Test;
import project.*;
import utils.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PortTest {

    //basic constructor test since we have to *PASSED*
    @Test
    public void constructorTest(){
        Port portA = new Port("A", new Ponto(20,10));
        assertEquals("A", portA.getName());
        assertEquals(20, portA.getPosition().getX(), 0.001);
        assertEquals(10, portA.getPosition().getY(), 0.001);
    }

    //Test to see if we can add to the queque the info *PASSED*
    @Test
    public void addScheduleTest(){
        Port portA = new Port("A", new Ponto(20,10));
        Schedule test = new Schedule(12,portA,3.0);

        portA.addSchedule(test);

        assertFalse(portA.getQueque().isEmpty());
        assertEquals(1,portA.getQueque().size());
    }

    //Test to for the sorting *PASSED*
    @Test
    public void addScheduleOrderTest() {
        Port portA = new Port("A", new Ponto(20, 10));
        Port portF = new Port("F", new Ponto(10, 10));

        Schedule s1 = new Schedule(15, portF, 1.0);
        Schedule s2 = new Schedule(9,  portF, 2.0);
        Schedule s3 = new Schedule(12, portF, 3.0);

        portA.addSchedule(s1);
        portA.addSchedule(s2);
        portA.addSchedule(s3);

        List<Schedule> fila = portA.getQueque();
        assertEquals(9,  fila.get(0).getDepartureTime());
        assertEquals(12, fila.get(1).getDepartureTime());
        assertEquals(15, fila.get(2).getDepartureTime());
    }
}