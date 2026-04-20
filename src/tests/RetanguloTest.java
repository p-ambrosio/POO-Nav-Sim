package tests;

import utils.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RetanguloTest {
    @Test
    public void TestRetanguloConstructor() {
        Retangulo r = new Retangulo(
                new Ponto(0,0), new Ponto(2,0),
                new Ponto(2,1), new Ponto(0,1));
        assertNotNull(r);
    }

    @Test
    public void TestRetanguloArea() {
        // calc manual: 2 * 1 = 2
        Retangulo r = new Retangulo(
                new Ponto(0,0), new Ponto(2,0),
                new Ponto(2,1), new Ponto(0,1));
        assertEquals(2.0, r.Area(), 0.001);
    }

}