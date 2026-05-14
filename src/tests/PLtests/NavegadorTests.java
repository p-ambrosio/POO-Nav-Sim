package tests.PLtests;

import org.junit.jupiter.api.Test;
import utils.Navegador;
import utils.Ponto;
import utils.Route;
import utils.Vetor;

import static org.junit.jupiter.api.Assertions.*;


public class NavegadorTests {


    @Test
    void testconstrutor(){
        Ponto[] p = {
                new Ponto(5,1),
                new Ponto(5,5),
                new Ponto(7,5),
        };
        Route r = new Route(p);

        Navegador n = new Navegador(r);
    }

    @Test
    void testtime(){
        Ponto[] p = {
                new Ponto(5,1),
                new Ponto(5,5),
                new Ponto(7,5),
        };
        double vl = 2;
        Route r = new Route(p);

        Navegador n = new Navegador(r);

        assertEquals(3.00,Math.round(n.time(vl) * 100.0) / 100.0);
    }
    @Test
    void testposition(){

        Ponto[] p = {
                new Ponto(5,1),
                new Ponto(5,5),
                new Ponto(7,5),
        };
        double vl = 2;
        double time = 2.25;
        Route r = new Route(p);

        Navegador n = new Navegador(r);
        Ponto pt = new Ponto(5.5,5);
        assertEquals(pt,n.position(time,vl));
    }
    @Test
    void testspeed(){
        Ponto[] p = {
                new Ponto(5,1),
                new Ponto(5,5),
                new Ponto(7,5),
        };
        Vetor windSpeed = new Vetor(1,1);
        double vl = 2;
        Route r = new Route(p);

        Navegador n = new Navegador(r);
        Vetor[] v = {
                new Vetor(-1,1),
                new Vetor(1,-1),
        };

        Vetor[] spdarr = n.speedArray(windSpeed,vl);

        assertEquals(v[0]+" "+v[1],spdarr[0]+" "+spdarr[1]);
    }
}
