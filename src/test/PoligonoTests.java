package utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PoligonoTests {

    @Test
    void testconstructor(){
        Ponto[] p = {
                new Ponto(0,1),
                new Ponto(1,1),
                new Ponto(4,4),
                new Ponto(4,3)
        };
        Poligono pol = new Poligono(p);
    }

    @Test
    void testintersection(){
        Ponto[] p1 = {
                new Ponto(1,1),
                new Ponto(3,3),
                new Ponto(4,3),
                new Ponto(4,4),
                new Ponto(6,4),
                new Ponto(6,1)
        };
        Poligono pol = new Poligono(p1);

        Ponto[] p2 = {
                new Ponto(1,4),
                new Ponto(3,2),
                new Ponto(4,2),
                new Ponto(7,5),
                new Ponto(7,2),
                new Ponto(5,2),
                new Ponto(3,0),
        };
        Route r = new Route(p2);

        Ponto[] ip = {
                new Ponto(2.50,2.50),
                new Ponto(6.00,4.00) ,
                new Ponto(6.00,2.00) ,
                new Ponto(4.00,1.00),
        };
        Ponto[] intp = pol.findIntersection(r);
        int i=0;
        for(Ponto p : ip){
            assertEquals(intp[i],p);
            i++;
        }
    }
}
