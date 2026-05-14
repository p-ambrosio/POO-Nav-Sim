package utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import utils.Lab4.Quadrado;

public class SquareTests {
    @Test
    void testconstructor(){
        Ponto[] p = {
                new Ponto(1,0),
                new Ponto(1,3),
                new Ponto(4,3),
                new Ponto(4,0)
        };
        Quadrado q = new Quadrado(p);
    }

    @Test
    void testintersection(){
        Ponto[] p1 = {
                new Ponto(1,0),
                new Ponto(1,3),
                new Ponto(4,3),
                new Ponto(4,0)
        };
        Quadrado pol = new Quadrado(p1);

        Ponto[] p2 = {
                new Ponto(0,0),
                new Ponto(2,2),
                new Ponto(2,6),
        };
        Route r = new Route(p2);


        Ponto[] ip = {
                new Ponto(1.00,1.00),
                new Ponto (2.00,3.00) ,
        };
        Ponto[] intp = pol.findIntersection(r);
        int i=0;
        for(Ponto p : ip){
            assertEquals(intp[i],p);
            i++;
        }
    }
}
