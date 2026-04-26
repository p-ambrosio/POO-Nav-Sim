package tests.PLtests;

import utils.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class QuadradoTest {

    @Test
    public void TestQuadradoConstructor() {
        Quadrado q = new Quadrado(
                new Ponto(0,0), new Ponto(1,0),
                new Ponto(1,1), new Ponto(0,1));
        assertNotNull(q);
    }

    @Test
    public void TestQuadradoArea() {
        // quadrado 1×1 — área = 1
        // calc manual: 1 * 1 = 1
        Quadrado q = new Quadrado(
                new Ponto(0,0), new Ponto(1,0),
                new Ponto(1,1), new Ponto(0,1));
        assertEquals(1.0, q.Area(), 0.001);
    }

}