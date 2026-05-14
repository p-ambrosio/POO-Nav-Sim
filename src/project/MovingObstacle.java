package project;

import utils.Circulo;
import utils.Ponto;
import utils.Vetor;

import java.util.Random;
/*
    Storms as moving objects circulos also
    TEACHER SAID:
    Os obstáculos móveis mudam de posição apenas no início da simulação.
    Em cada simulação deverão estar em posições diferentes, que devem intersectar rotas,
    mas durante a simulação permanecem fixos

    Oq simplifica o que fazer


    //TODO O CENTRO NAO ATUALIZA RN
 */

public class MovingObstacle extends Circulo{
    //We will cycle within these nodes in each sim, this way it will allways be in a route
    private static final Random r= new Random();

    private static int simIndex=r.nextInt(1234);
    private static final Ponto[] positions = {
            new Ponto(480,400),
            new Ponto(120,190),
            new Ponto(0,600),
            new Ponto(800,200),
    };


    //speed made no sense
    public MovingObstacle(Ponto centro, double raio) {
        super(centro, raio);
    }

    private void setPosition(Ponto newCentro) {
        this.center = newCentro; // works as-is if Circulo.centro is protected
    }

    public void positioning(int obstacleNumber) {

        int index = (simIndex + obstacleNumber) % positions.length;

        setPosition(positions[index]);
    }

    public Ponto getPosition(){
        return this.center;
    }

    public static void nextSimulation() {
        simIndex++;

    }
    public static int getSimIndex(){return simIndex;}

}
