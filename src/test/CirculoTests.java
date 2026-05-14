package utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CirculoTests {

    @Test
    void testconstructor(){
        Ponto centro = new Ponto(14,4);
        double rad = 2;
        Circulo c = new Circulo(centro,rad);
    }

    @Test
    void testintersection(){
        Ponto[] p = {
                new Ponto(6,4),
                new Ponto(20,4)
        };
        Route r = new Route(p);

        Ponto centro = new Ponto(14,4);
        double rad = 2;
        Circulo c = new Circulo(centro,rad);
        Ponto[] ip ={
                new Ponto(12.00,4.00),
                new Ponto(16.00,4.00)
        };

        assertEquals(c.findIntersection(r)[0],ip[0]);
        assertEquals(c.findIntersection(r)[1],ip[1]);
    }
}
