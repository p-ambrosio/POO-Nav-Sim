package tests.PLtests;

import org.junit.jupiter.api.Test;
import utils.Ponto;
import utils.SegmentoReta;
import utils.Vetor;

import static org.junit.jupiter.api.Assertions.*;

class SegmentoRetaTest {

    @Test
    void testConstructor0(){        // unable to test system.exit(0) here so i will be comparing the output of toString...
        Ponto p =new Ponto(1,2);
        Vetor v = new Vetor(3,2);
        SegmentoReta sr = new SegmentoReta(p,v);
        assertEquals("sr((1.00,2.00); (3.00,2.00))",sr.toString());
    }
    @Test
    void testdistOrdem() {
        Ponto p =new Ponto(2,2);
        Vetor v = new Vetor(-1,0);
        SegmentoReta sr = new SegmentoReta(p,v);
        assertEquals("sr((1.00,2.00); (2.00,2.00))",sr.distOrdem().toString());
    }

    @Test
    void testToString() {
        Ponto p =new Ponto(1,2);
        Vetor v = new Vetor(3,2);
        SegmentoReta sr = new SegmentoReta(p,v);
        assertEquals("sr((1.00,2.00); (3.00,2.00))",sr.toString());
    }

    @Test
    void testintersect() {
        Ponto p1 = new Ponto(0,1);
        Vetor v1 = new Vetor(4,1);
        SegmentoReta r1 = new SegmentoReta(p1,v1);
        Vetor v2 = new Vetor(2,2);
        Ponto result = new Ponto(1,1);
        assertTrue(r1.intersect(v2).equals(result));
    }
}