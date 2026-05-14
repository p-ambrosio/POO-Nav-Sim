package tests.PLtests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import utils.AutoPilot;
import utils.Ponto;
import utils.Vetor;

class AutoPilotTests {

    @Test
    void testConstructor0(){                // cant test system.exit(0)....
        Ponto a = new Ponto(1,4);
        Ponto b= new Ponto(2,1);
        AutoPilot ap = new AutoPilot(a,b);
    }

    @Test
    void testtime() {
        Ponto a = new Ponto(3,4);
        Ponto b= new Ponto(3,2);
        Vetor w = new Vetor(0.2,0.2);
        double s = 0.4;

        AutoPilot ap = new AutoPilot(a, b);
        assertEquals(5,Math.round(ap.time(s) * 100.0) / 100.0);       // round to 2 decimals
    }

    @Test
     void testspeed() {
        Ponto a = new Ponto(3,2);
        Ponto b= new Ponto(3,4);
        Vetor w = new Vetor(0.2,0.2);
        double s = 0.4;

        AutoPilot ap = new AutoPilot(a, b);
        double t = ap.time(s);
        Vetor v = new Vetor(-0.2,0.2);
        assertTrue((ap.speed(w,t)).equals(v));
    }

}