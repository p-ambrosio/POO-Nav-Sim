package tests.PLtests;

import utils.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A classe AutoPilotTest pretende testar os resultados das funcoes implementadas na classe
 * AutoPilot, como os cálculos da velocidade e tempo atraves de testes unitarios com valores simples,
 * mas que cobram os casos possiveis
 *
 *
 * @author Pedro Ambrosio, nº88589
 * @version 2.0 [30/3/26]
 *
 **/
class AutoPilotTest {

    @Test
    public void TestSpeed(){
        Ponto tester = new Ponto(0,0);
        Ponto tester2 = new Ponto(10.0,0);
        AutoPilot test = new AutoPilot(tester,tester2);
        Vetor wind = new Vetor(0,2.0);
        double time = 2.0;

        Vetor Vr= test.speed(wind,time);
        assertEquals(5.0,Vr.getX(),0.01);
        assertEquals(-2.0,Vr.getY(),0.01);
    }
    @Test
    public void TestSpeed2(){
        Ponto tester = new Ponto(0,0);
        Ponto tester2 = new Ponto(10.0,10.0);
        AutoPilot test = new AutoPilot(tester,tester2);
        Vetor wind = new Vetor(2.0,0.0);
        double time = 2.0;

        Vetor Vr= test.speed(wind,time);
        assertEquals(3.0,Vr.getX(),0.01);
    }


    @Test
    void testTime0() {
        // Arrange
        Ponto A = new Ponto(0, 0);
        Ponto B = new Ponto(3, 4); // distance = 5
        AutoPilot tester = new AutoPilot(A, B);

        double linearSpeed = 5.0;
        double result = tester.time(linearSpeed);

        // Expected 1
        assertEquals(1.0, result, 0.1);

    }

    @Test
    void testTime1(){
        Ponto A = new Ponto(1,1);
        Ponto B = new Ponto(1,1);
        AutoPilot tester = new AutoPilot(A,B);

        double linearSpeed = 5.0;
        double result = tester.time(linearSpeed);

        // Expected 0 == SegmentoReta:iv
        assertEquals(0.0, result, 0.1);
    }
}