package utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class VetorTest {
    void testConstructor0(){                 // unable to test system.exit(0) here so i will be comparing the output of toString...
        Vetor v = new Vetor(1,2);
        assertEquals("[1.00,2.00]",v.toString());
    }

    @Test
    void testConstructor1(){                 // unable to test system.exit(0) here so i will be comparing the output of toString...
        Ponto p =new Ponto(1,2);
        Vetor v = new Vetor(p);
        assertEquals("[1.00,2.00]",v.toString());
    }

    @Test
    void testgetX() {
        Vetor v1 = new Vetor(3.0, 4.0);
        assertEquals(3,v1.getX());
    }

    @Test
    void testgetY() {
        Vetor v1 = new Vetor(3.0, 4.0);
        assertEquals(4,v1.getY());
    }

    @Test
    void testcossineSimilarity() {
        Vetor v1 = new Vetor(3.0, 4.0);
        Vetor v2 = new Vetor(3.0, 4.0);
        assertEquals(1,v1.cossineSimilarity(v2));
    }

    @Test
    void testprodutoInterno() {
        Vetor v1 = new Vetor(3.0, 4.0);
        Vetor v2 = new Vetor(3.0, 4.0);
        assertEquals(25,v1.produtoInterno(v2));
    }

    @Test
    void testEquals() {
        Vetor v1 = new Vetor(3.0, 4.0);
        Vetor v2 = new Vetor(3.0, 4.0);
        Vetor v3 = new Vetor(5.0, 4.0);
        assertTrue(v1.equals(v2));
        assertFalse(v1.equals(v3));
    }

    @Test
    void testmoduloPosicao() {
        Vetor v1 = new Vetor(3.0, 4.0);
        assertEquals(5,v1.moduloPosicao());
    }

    @Test
    void testmult() {
        Vetor v = new Vetor (2,2);
        Vetor vRes = v.mult(2);
        assertEquals("[4.00,4.00]",vRes.toString());
    }

    @Test
    void testadd() {
        Vetor v1 = new Vetor (2,2);
        Vetor v2 = new Vetor (3,4);
        Vetor v = v1.add(v2);
        assertEquals("[5.00,6.00]",v.toString());
    }

    @Test
    void testsub() {
        Vetor v1 = new Vetor (2,2);
        Vetor v2 = new Vetor (3,4);
        Vetor v = v1.sub(v2);
        assertEquals("[-1.00,-2.00]",v.toString());
    }

    @Test
    void testToString() {
        Vetor v1 = new Vetor(3.0, 4.0);
        assertEquals("[3.00,4.00]",v1.toString());
    }

    @Test
    void testintersect() {
        Ponto p1 = new Ponto(0,1);
        Vetor v1 = new Vetor(4,1);
        SegmentoReta r1 = new SegmentoReta(p1,v1);
        Vetor v2 = new Vetor(2,2);
        Ponto result = new Ponto(1,1);
        assertTrue(v2.intersect(r1).equals(result));
    }
}