package tests;

import utils.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FiguraTest {

    @Test
    public void TestGetArestas() {
        // triângulo com 3 vértices deve ter 3 arestas, a última de v[2] para v[0]
        Triangulo t = new Triangulo(new Ponto(0,0), new Ponto(2,0), new Ponto(1,2));
        SegmentoReta[] arestas = t.getArestas();
        assertEquals(3, arestas.length);
        assertEquals(1.0, arestas[2].getA().getX(), 0.001);
        assertEquals(0.0, arestas[2].getB().getX(), 0.001);
    }

    @Test
    public void TestFigura_Intersects_Verdadeiro() {
        // segmento horizontal atravessa o quadrado unitário
        Quadrado q = new Quadrado(
                new Ponto(0,0), new Ponto(1,0),
                new Ponto(1,1), new Ponto(0,1));
        SegmentoReta sr = new SegmentoReta(new Ponto(-1, 0.5), new Ponto(2, 0.5));
        assertTrue(q.intersects(sr));
    }

}