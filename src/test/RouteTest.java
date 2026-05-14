package utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RouteTest {

    @Test
    void testConstructor0(){
        Ponto[] p = {
                new Ponto(0,1),
                new Ponto(1,1),
                new Ponto(4,4),
                new Ponto(4,3)
        };
        Route r = new Route(p);
        assertEquals("(0.00,1.00)", r.getPoints()[0].toString());
        assertEquals("(1.00,1.00)", r.getPoints()[1].toString());
        assertEquals("(4.00,4.00)", r.getPoints()[2].toString());
        assertEquals("(4.00,3.00)", r.getPoints()[3].toString());
    }

    @Test
    void testfindDistance(){
        Ponto[] p = {
                new Ponto(0,1),
                new Ponto(1,1),
                new Ponto(4,4),
                new Ponto(4,3)
        };
        Route r = new Route(p);
        assertEquals(6.24, Math.floor(r.findDistance() * 100) / 100);
    }

    @Test
    void testfindIntersect(){
        Ponto[] p = {
                new Ponto(0,1),
                new Ponto(1,1),
                new Ponto(4,4),
                new Ponto(4,3)
        };
        SegmentoReta sr = new SegmentoReta(new Ponto(2,1),new Vetor(2,4));
        Route r = new Route(p);
        assertEquals("(2.00,2.00)",r.findIntersect(sr)[0].toString());
    }

}