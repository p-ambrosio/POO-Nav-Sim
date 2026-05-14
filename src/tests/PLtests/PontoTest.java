package tests.PLtests;

import org.junit.jupiter.api.Test;
import utils.Ponto;

import static org.junit.jupiter.api.Assertions.*;

class PontoTest {

    @Test
    void testConstructor0(){
        Ponto p =new Ponto(1,2);
        assertEquals("(1.00,2.00)",p.toString());
    }

    @Test
    void testgetX() {
        Ponto p = new Ponto(3.0, 4.0);
        assertEquals(3,p.getX());
    }

    @Test
    void testgetY() {
        Ponto p = new Ponto(3.0, 4.0);
        assertEquals(4,p.getY());
    }

    @Test
    void testEquals() {
        Ponto p1 = new Ponto (2,2);
        Ponto p2 = new Ponto (2,2);
        Ponto p3 = new Ponto (1,3);

        assertTrue(p1.equals(p2));
        assertFalse(p1.equals(p3));
    }

    @Test
    void testToString() {
        Ponto p = new Ponto (1,2);
        assertEquals("(1.00,2.00)",p.toString());
    }

    @Test
    void testmoduloPosicao() {
        Ponto v1 = new Ponto(3.0, 4.0);
        assertEquals(5,v1.moduloPosicao());
    }
}