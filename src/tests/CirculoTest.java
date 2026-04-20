package tests;

import utils.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CirculoTest {
    @Test
    public void TestCirculoConstructor() {
        Circulo c = new Circulo(new Ponto(0,0), 5);
        assertNotNull(c);
    }

    @Test
    public void TestCirculoGetters() {
        Circulo c = new Circulo(new Ponto(1,2), 3);
        assertEquals(1.0, c.getCentro().getX(), 0.001);
        assertEquals(2.0, c.getCentro().getY(), 0.001);
        assertEquals(3.0, c.getRaio(), 0.001);
    }

    @Test
    public void TestCirculoArea() {
        // raio 1 — área = π * 1^2 = π
        // calc manual: π ≈ 3.14159
        Circulo c = new Circulo(new Ponto(0,0), 1);
        assertEquals(Math.PI, c.Area(), 0.001);
    }

    @Test
    public void TestCirculo_Intersects_Verdadeiro() {
        // segmento horizontal atravessa círculo de raio 2 centrado na origem
        Circulo c = new Circulo(new Ponto(0,0), 2);
        SegmentoReta sr = new SegmentoReta(new Ponto(-3,0), new Ponto(3,0));
        assertTrue(c.intersects(sr));
    }

}