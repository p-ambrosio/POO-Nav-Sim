package tests;
import utils.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class SegmentoRetaTest permite a criacao de funcoes de teste para a classe SegmentoReta
 * esta que possui a habilidade da criação de uma reta num plano bidimensional
 * atraves de dois pontos, o inicial e um vetor de posicao que define o seu final.
 * Testando assim a criacao da reta e os cálculos envolvidos
 *
 * @author Pedro Ambrósio, nº88589
 * @version 1.0 [2/03/26]
 * @inv Todos os testes têm de passar
 */
class SegmentoRetaTest {

    @Test
    public void SegmentoRetaTest_P(){
        Ponto tester1 = new Ponto(1,2);
        Ponto tester2 = new Ponto(3,4);
        SegmentoReta sr = new SegmentoReta(tester1, tester2);

        assertNotNull(sr); // se sr foi formado devidamente

    }

    @Test
    public void SegmentoRetaTest_V(){
        Ponto tester1 = new Ponto(1,2);
        Vetor tester2 = new Vetor(3,4);
        SegmentoReta sr = new SegmentoReta(tester1, tester2);
        //Calc: sr=(4,6)
        assertEquals(4,sr.getB().getX());
        assertEquals(6,sr.getB().getY());
    }

    @Test
    public void TestGetters0(){
        Ponto tester1 = new Ponto(1,2);
        Ponto tester2 = new Ponto(3,4);
        SegmentoReta sr = new SegmentoReta(tester1, tester2);

        assertEquals(tester1, sr.getA());
        assertEquals(tester2, sr.getB());

    }

    @Test
    public void TestGetters1(){
        Ponto tester1 = new Ponto(1.0,1.0);
        Ponto tester2 = new Ponto(4.0,5.0);
        SegmentoReta sr = new SegmentoReta(tester1, tester2);

        Vetor v = sr.GetVetor();
        assertEquals(3.0, v.getX(),0.01);
        assertEquals(4.0, v.getY(), 0.01);
    }

    @Test
    public void TestIntersect0(){
        Ponto tester1 = new Ponto(2.0,2.0);
        Ponto tester2 = new Ponto(6.0, 2.0);
        SegmentoReta sr = new SegmentoReta(tester1, tester2);

        Vetor v = new Vetor(4.0, 4.0);
        Ponto intersc = sr.Intersect(v);

        assertNotNull(intersc); // Tem de intersetar!
        assertEquals(2.0, intersc.getX(),0.01);
        assertEquals(2.0, intersc.getY(), 0.01);
    }

    @Test
    public void TestIntersect1(){
        Ponto tester1 = new Ponto(5.0, 5.0);
        Ponto tester2 = new Ponto(10.0, 10.0);
        SegmentoReta sr = new SegmentoReta(tester1, tester2);

        Vetor erro = new Vetor(-1.0, -1.0);
        Ponto intersc = sr.Intersect(erro);

        assertNull(intersc);
    }

    @Test
    public void TesttoString0(){
        Ponto tester1 = new Ponto(1,2);
        Ponto tester2 = new Ponto(3,4);
        SegmentoReta sr = new SegmentoReta(tester1, tester2);

        String test = sr.toString();
        String expected = "sr(" + tester1.toString() + "; " + tester2.toString() + ")";
        assertEquals(expected, test);
    }

    @Test
    public void TestIntersect2() {
        // Segmento horizontal de (0,0) a (4,0)
        SegmentoReta sr1 = new SegmentoReta(new Ponto(0, 0), new Ponto(4, 0));

        // Segmento vertical que intersecta em (2,0)
        SegmentoReta sr2 = new SegmentoReta(new Ponto(2, -1), new Ponto(2, 1));

        Ponto p = sr1.Intersect(sr2);
        assertNotNull(p);
        assertEquals(2.0, p.getX(), 0.001);
        assertEquals(0.0, p.getY(), 0.001);

        // Segmento que não intersecta
        SegmentoReta sr3 = new SegmentoReta(new Ponto(5, -1), new Ponto(5, 1));
        assertNull(sr1.Intersect(sr3));
    }

}