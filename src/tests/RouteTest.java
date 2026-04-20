package tests;

import utils.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * A class TestRoute representa um conjunto de testes logicos para a class Route, que consiste
 * numa rota composta por varios pontos, rota a qual é formada por segmentos de reta entre pontos consecutivos.
 *
 * @author Pedro Ambrósio, nº88589
 * @version 1.0 [10/03/26]
 */
class RouteTest {

    @Test
    public void TestRoute() {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(1, 0));
        pontos.add(new Ponto(1, 1));

        Route route = new Route(pontos);
        assertNotNull(route);
    }

    @Test
    public void TestLength() {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(3, 0));
        pontos.add(new Ponto(3, 4));

        Route route = new Route(pontos);
        // 3 + 4 = 7
        assertEquals(7.0, route.Length(), 0.001);
    }

    @Test
    public void TestIntercet_Route() {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(4, 0));

        Route route = new Route(pontos);

        // Segmento que intersecta a rota em (2, 0)
        SegmentoReta sr = new SegmentoReta(new Ponto(2, -1), new Ponto(2, 1));
        List<Ponto> intersecoes = route.Intersect_Route(sr);
        assertNotNull(intersecoes);
        assertEquals(1, intersecoes.size());
        assertEquals(2.0, intersecoes.getFirst().getX(), 0.001);
        assertEquals(0.0, intersecoes.getFirst().getY(), 0.001);

        // Segmento que não intersecta a rota
        SegmentoReta srFora = new SegmentoReta(new Ponto(5, -1), new Ponto(5, 1));
        assertNull(route.Intersect_Route(srFora));
    }
    @Test
    void testIsDuplicateTrue() {
        List<Ponto> lista = new ArrayList<>();
        lista.add(new Ponto(1.0, 1.0));

        Route r = new Route(lista);

        Ponto p = new Ponto(1.0 + 1e-7, 1.0 - 1e-7);

        assertTrue(r.isDuplicate(lista, p));
    }

    @Test
    void testIsDuplicateFalse() {
        List<Ponto> lista = new ArrayList<>();
        lista.add(new Ponto(1.0, 1.0));

        Route r = new Route(lista);

        Ponto p = new Ponto(2.0, 2.0);

        assertFalse(r.isDuplicate(lista, p));
    }


    @Test
    void testIntersectsCirculo() {
        List<Ponto> rotaPts = Arrays.asList(
                new Ponto(0, 0),
                new Ponto(10, 0)
        );

        Route rota = new Route(rotaPts);

        Circulo c = new Circulo(new Ponto(5, 0), 2);

        List<Ponto> res = rota.intersectsFigura(c);

        assertNotNull(res);
        assertEquals(2, res.size());
    }


    @Test
    void test_getPoints() {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(3, 0));
        pontos.add(new Ponto(3, 4));
        Route route = new Route(pontos);

        List<Ponto> result = route.getPoints();
        assertEquals(3, result.size());
        assertEquals(0.0, result.get(0).getX(), 0.001);
        assertEquals(3.0, result.get(1).getX(), 0.001);
        assertEquals(3.0, result.get(2).getX(), 0.001);
        assertEquals(4.0, result.get(2).getY(), 0.001);

        result.add(new Ponto(9, 9));
        assertEquals(3, route.getPoints().size());
    }

}

