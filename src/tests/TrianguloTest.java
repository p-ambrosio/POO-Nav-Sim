package tests;

import utils.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrianguloTest {

    @Test
    public void TestTrianguloArea() {
        // triângulo retângulo base 2, altura 2 — área = 2
        Triangulo t = new Triangulo(new Ponto(0,0), new Ponto(2,0), new Ponto(0,2));
        // calc manual: (2*2)/2 = 2
        assertEquals(2.0, t.Area(), 0.001);
    }

    @Test
    public void TestTriangulo_Colineares_Verdadeiro() {
        // pontos (0,0), (1,1), (2,2) são colineares
        Triangulo t = new Triangulo(new Ponto(0,0), new Ponto(4,0), new Ponto(2,4));
        assertTrue(t.Colineares(new Ponto(0,0), new Ponto(1,1), new Ponto(2,2)));
    }

}