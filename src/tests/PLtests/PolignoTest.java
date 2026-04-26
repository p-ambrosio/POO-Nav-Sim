package tests.PLtests;

import utils.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PolignoTest {
    @Test
    public void TestPoligonoConstructor() {
        Poligno p = new Poligno(new Ponto[]{
                new Ponto(0,0), new Ponto(1,0), new Ponto(0,1)
        });
        assertNotNull(p);
    }

    @Test
    public void TestPoligonoArea() {
        // quadrado 1×1 como polígono genérico, área deve ser 1
        Poligno p = new Poligno(new Ponto[]{
                new Ponto(0,0), new Ponto(1,0),
                new Ponto(1,1), new Ponto(0,1)
        });
        assertEquals(1.0, p.Area(), 0.001);
    }


}