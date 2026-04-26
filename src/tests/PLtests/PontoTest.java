package tests.PLtests;
import utils.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe que representa um conjunto de testes sobre a classe Ponto
 * que cria um ponto no plano bidimensional com coordenadas x e y
 * Testando assim calculos e funcoes associadas
 *
 * @author Pedro Ambrósio, nº88589
 * @version 1.0 [02/03/26]
 * @inv Todos os testes têm de ser aceites
 *
 */
class PontoTest {

    @Test
    public void TestPontoConstructor() {
        Ponto tester1 = new Ponto(1.0,2.0);
        assertNotNull(tester1);
    }

    @Test
    public void TestGetters(){
        Ponto tester1 = new Ponto(1.0,2.0);
        assertEquals(1.0,tester1.getX(),0.001);
        assertEquals(2.0,tester1.getY(),0.001);
    }

    @Test
    public void TestDistanceOrigin(){
        Ponto tester1 = new Ponto(3.0,4.0);
        //Calc manual 3^2+4^2=25 || sqrt25 =25
        assertEquals(5.0,tester1.distanceOrigin(),0.001);
    }

    @Test
    public void TestTostring(){
        Ponto p = new Ponto(1.234, 5.678);

        String resultado = p.toString();

        assertTrue(resultado.contains("1.23") || resultado.contains("1,23"));
        assertTrue(resultado.contains("5.68") || resultado.contains("5,68"));
        assertTrue(resultado.startsWith("(") && resultado.endsWith(")"));
    }

    @Test
    public void TestDistance(){
        Ponto a = new Ponto(1, 2);
        Ponto b = new Ponto(4, 6);
        //calc man: sqrt(25) = 5
        assertEquals(5.0,a.CalcDistance(b),0.001);

    }

}