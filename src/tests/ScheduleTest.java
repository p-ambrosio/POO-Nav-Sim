package tests;
//  UP TO DATE
import org.junit.jupiter.api.Test;
import utils.*;
import project.*;
import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {
    @Test
    public void constructorTest(){
        Port portF = new Port("F", new Ponto(10, 10));
        Schedule s = new Schedule(12, portF, 3.0);
        assertEquals(12,   s.getDepartureTime());
        assertEquals(portF, s.getDestination());
        assertEquals(3.0,  s.getSpeed(), 0.001);

    }
    @Test
    public void getDepartureTimeTest() {
        Port portF = new Port("F", new Ponto(10, 10));
        Schedule s = new Schedule(9, portF, 2.0);
        assertEquals(9, s.getDepartureTime());
    }

    @Test
    public void getDestinationTest() {
        Port portF = new Port("F", new Ponto(10, 10));
        Schedule s = new Schedule(12, portF, 3.0);
        assertEquals(portF, s.getDestination());
    }

    @Test
    public void getSpeedTest() {
        Port portF = new Port("F", new Ponto(10, 10));
        Schedule s = new Schedule(12, portF, 3.0);
        assertEquals(3.0, s.getSpeed(), 0.001);
    }

    @Test
    public void toStringTest(){
        Port portF = new Port("F", new Ponto(10, 10));
        Schedule s = new Schedule(12, portF, 3.0);
        assertEquals("T=12, F, 3", s.toString());
    }
}