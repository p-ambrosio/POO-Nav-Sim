package tests;

import utils.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe responsavel pelos testes da classe Vetor que implementa um vetor bidimensional
 * o qual é tambem responsavel por diversas operacoes matematicas
 *  @author Pedro Ambrosio, nº88589
 *  @version 1.0 [2/3/26]
 *  @inv Os testes sobre a classe Vetor devem todos passar com os valores esperados
 */
class VetorTest {

    @Test
    public void TestConstructor1()
    {
        Vetor v = new Vetor(3.0, 2.0);
        assertEquals(3.0, v.getX(), 0);
        assertEquals(2.0, v.getY(), 0);
    }

    @Test
    public void TestGetters(){
        Vetor tester= new Vetor (4.0,-3.5);
        assertEquals(4.0,tester.getX(),0);
        assertEquals(-3.5,tester.getY(),0);
    }

    @Test
    public void TestMod(){
        Vetor tester= new Vetor (4.0,-3.5);
        //Calc manual: 4^2 +(-3.5)^2 = 5.31
        assertEquals(5.31,tester.calcMod(),0.01);
        
    }

    @Test
    public void TestPordint(){
        Vetor tester1= new Vetor (4.0,-3.5);
        Vetor tester2= new Vetor (2.5, 1.0);
        //Calc manual se nao nao sei o esperado
        // 4*2.5 + (-3.5)*1.0 = 10 + (-3.5) = 6.5 okay
        assertEquals(6.5,tester1.prodInt(tester2),0);
    }

    @Test
    public void TestCosine(){
        Vetor tester1= new Vetor (1.0,0.0);
        Vetor tester2= new Vetor (0.0,1.0);
        //should be 0.0
        assertEquals(0.0, tester1.cosineSim(tester2),0);
    }

    @Test
    public void Testmult(){
        Vetor tester= new Vetor(2.0,-3.0);
        Vetor d = tester.mult(2.0);
        assertEquals(4.0,d.getX(),0);
        assertEquals(-6.0,d.getY(),0);
    }


    @Test
    public void Testadd(){
        Vetor tester1= new Vetor(2.0,-3.0);
        Vetor tester2= new Vetor(1.0,2.0);
        Vetor v = tester1.add(tester2);
        assertEquals(3.0,v.getX(),0);
        assertEquals(-1.0,v.getY(),0);
    }

    @Test
    public void Testsub(){
        Vetor tester1= new Vetor(2.0,-3.0);
        Vetor tester2= new Vetor(1.0,2.0);
        Vetor v = tester1.sub(tester2);
        assertEquals(1.0,v.getX(),0);
        assertEquals(-5.0,v.getY(),0);
    }

    public void testIntersect() {
        Vetor v = new Vetor(1.0, 1.0);

        Ponto p1 = new Ponto(0.0, 2.0);
        Ponto p2 = new Ponto(2.0, 0.0);
        SegmentoReta sr = new SegmentoReta(p1, p2);

        Ponto result = v.Intersect(sr);

        assertNotNull(result);
        // O ponto de interseção esperado entre y=x e y=-x+2 é (1,1)
        assertEquals(1.0, result.getX(), 0);
        assertEquals(1.0, result.getY(), 0);
    }

    @Test
    public void testNoIntersect() {
        Vetor v = new Vetor(1.0, 0.0);
        Ponto p1 = new Ponto(0.0, 1.0);
        Ponto p2 = new Ponto(5.0, 1.0);
        SegmentoReta sr = new SegmentoReta(p1, p2);

        assertNull(v.Intersect(sr));
    }

    @Test
    public void testToString() {
        assertEquals("[1,00,2,00]", new Vetor(1, 2).toString());
        assertEquals("[3,14,2,72]", new Vetor(3.14159, 2.71828).toString());
        assertEquals("[0,50,-1,20]", new Vetor(0.5, -1.2).toString());
    }



}