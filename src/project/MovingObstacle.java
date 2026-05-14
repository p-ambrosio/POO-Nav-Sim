package project;

import utils.Circulo;
import utils.Ponto;
import utils.Vetor;

import java.util.Random;
/**
 * Representa um obstáculo móvel na simulação, modelado como um círculo.
 * Em cada simulação, o obstáculo é posicionado numa posição diferente,
 * escolhida ciclicamente de um conjunto fixo de posições predefinidas que
 * intersectam rotas relevantes. Durante a simulação, o obstáculo permanece fixo.
 *
 * @author Aashma Pandey-88430, Bruno Simão-80143, Pedro Ambrósio-88589.
 * @version 1.0 - 2025-05-14
 * @inv O raio do obstáculo é positivo (raio > 0) e o centro é sempre uma das
 *      posições predefinidas em {@code positions} após a chamada a {@code positioning}.
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


    /**
     * Constrói um obstáculo móvel com um centro e raio especificados.
     *
     * <p>Pré-condição: {@code raio > 0} e {@code centro != null}.</p>
     * <p>Pós-condição: O obstáculo é criado com o centro e raio fornecidos.</p>
     *
     * @param centro o ponto central inicial do obstáculo; não deve ser {@code null}
     * @param raio   o raio do obstáculo; deve ser positivo
     */
    public MovingObstacle(Ponto centro, double raio) {
        super(centro, raio);
    }

    /**
     * Define o centro do obstáculo para um novo ponto.
     *
     * <p>Pré-condição: {@code newCentro != null}.</p>
     * <p>Pós-condição: O campo {@code center} é atualizado para {@code newCentro}.</p>
     *
     * @param newCentro o novo ponto central a atribuir ao obstáculo; não deve ser {@code null}
     */
    private void setPosition(Ponto newCentro) {
        this.center = newCentro; // works as-is if Circulo.centro is protected
    }

    /**
     * Posiciona o obstáculo numa posição predefinida com base no índice de simulação
     * atual e no número identificador do obstáculo.
     * Garante que obstáculos diferentes ficam em posições distintas na mesma simulação.
     *
     * @param obstacleNumber o índice deste obstáculo dentro da simulação
     * usado para diferenciar a posição entre múltiplos obstáculos
     */
    public void positioning(int obstacleNumber) {

        int index = (simIndex + obstacleNumber) % positions.length;

        setPosition(positions[index]);
    }

    /**
     * Devolve o ponto atual da tempestade.
     * @return ponto que representa o centro atual.
     */
    public Ponto getPosition(){
        return this.center;
    }
    /**
     * Avança o índice global de simulação para a próxima simulação.
     */
    public static void nextSimulation() {
        simIndex++;

    }
    
    /**
     * Devolve o índice atual da simulação.
     * @return o valor inteiro atual de simIndex
     */
    public static int getSimIndex(){return simIndex;}

}
