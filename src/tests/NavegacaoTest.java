package tests;

import org.junit.jupiter.api.Test;
import utils.Navegacao;
import utils.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class NavegacaoTest {

    @Test
    public void TestNavegadorRota() {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(3, 0));
        pontos.add(new Ponto(3, 4));
        Navegacao nav = new Navegacao(new Route(pontos));
        assertNotNull(nav);
    }


    @Test
    public void TestRouteLength() {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(3, 0));
        pontos.add(new Ponto(3, 4));
        Navegacao nav = new Navegacao(new Route(pontos));
        // segmento 1: 3, segmento 2: 4 → total 7
        assertEquals(7.0, nav.routeLength(), 0.001);
    }

    @Test
    public void TestTotalTime() {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(3, 0));
        pontos.add(new Ponto(3, 4));
        Navegacao nav = new Navegacao(new Route(pontos));
        assertEquals(3.5, nav.totalTime(2.0), 0.001);
    }

    @Test
    public void TestSpeedVectors() {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(4, 0));
        pontos.add(new Ponto(4, 3));
        Navegacao nav = new Navegacao(new Route(pontos));
        // r=[4,0], vl=4  t=1 → v=[3,-1]
        Vetor v = nav.speedVectors(new Vetor(1, 1), 4.0).getFirst();
        assertEquals(3.0,  v.getX(), 0.001);
        assertEquals(-1.0, v.getY(), 0.001);
    }

    @Test
    public void TestPosition() {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(4, 0));
        pontos.add(new Ponto(4, 4));
        Navegacao nav = new Navegacao(new Route(pontos));
        // vl=2: seg1=2s, seg2=2s; t=2 → junção (4,0)
        Ponto p = nav.positionAtTime(2, 2.0);
        assertEquals(4.0, p.getX(), 0.001);
        assertEquals(0.0, p.getY(), 0.001);
    }
}