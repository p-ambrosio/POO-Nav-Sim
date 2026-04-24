package project;

import utils.*;

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
    private final Vetor speed; //used only in the starting set-up

    public MovingObstacle(Ponto centro, double raio, Vetor speed) {
        super(centro, raio);
        this.speed = speed;
    }

    //So we have it be random... since "Em cada simulação deverão estar em posições diferentes" makes it a pain to deal with
    public MovingObstacle positioning(double time){
        Random rngesus = new Random();

        double x = getCentro().getX() + speed.getX() * rngesus.nextDouble()*time;
        double y = getCentro().getY() + speed.getY() * rngesus.nextDouble()*time;

        return new MovingObstacle(new Ponto(x,y),getRaio(),speed);
    }

    public Vetor getSpeed(){ return speed;}

}
