package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe NavegadorRota estende AutoPilot para suportar rotas compostas por
 * múltiplos segmentos de reta, aplicando os cálculos de navegação de AutoPilot
 * a cada segmento individualmente.
 *
 * @author Pedro Ambrósio, nº88589
 * @version 1.0 [06/04/26]
 * @inv A rota deve ter pelo menos 2 pontos distintos consecutivos.
 */
public class Navegacao extends AutoPilot{
    private final Route route;

    /**
     * Construtor que recebe uma rota de varios segmentos
     * @param route rota composta
     */
    public Navegacao(Route route) {
        super(route.getPoints().getFirst(), route.getPoints().getLast());
        this.route = route;
    }

    /**
     * Calcula o comprimento total da rota somando pontos
     * @return o comprimento atual total da rota composta
     */
    public double routeLength(){return route.Length();}

    /**
     * Calcula o tempo total para percorrer toda a rota com velocidade linear constante vl.
     * @param vl velocidade linear
     * @return tempo total para percorrer a rota na sua totalidade
     */
    public double totalTime(double vl){return route.Length()/vl;}

    /**
     * Calcula a lista de vetores velocidade, um por segmento, necessários para compensar
     * o vento w mantendo o avião na rota com velocidade linear vl.
     * @param w velocidade vetorial do vento
     * @param vl velocidade linear constante
     * @return lista de vetores velocidade, um por segmento da rota
     */
    public List<Vetor> speedVectors(Vetor w, double vl) {
        List<Ponto> points = route.getPoints();
        List<Vetor> speeds = new ArrayList<>();
        for (int i = 0; i < points.size() - 1; i++) {
            Ponto p1 = points.get(i);
            Ponto p2 = points.get(i + 1);
            AutoPilot segAP = new AutoPilot(p1, p2);
            double segTime = segAP.time(vl);
            speeds.add(segAP.speed(w, segTime));
        }
        return speeds;
    }

    /**
     *  Calcula a posição do avião no instante t desde o início da viagem,
     *  percorrendo a rota segmento a segmento com velocidade linear constante vl.
     * @param t tempo decorrido
     * @param vl velocidade linear
     * @see "https://en.wikipedia.org/wiki/Linear_interpolation"
     * @return o ponto p onde se encontra o avião no t
     */
    public Ponto positionAtTime(double t, double vl) {
        List<Ponto> points = route.getPoints();
        double remaining = t;
        for (int i = 0; i < points.size() - 1; i++) {
            Ponto p1 = points.get(i);
            Ponto p2 = points.get(i + 1);
            AutoPilot segAP = new AutoPilot(p1, p2);
            double segTime = segAP.time(vl);
            if (remaining <= segTime) {
                double fraction = remaining / segTime;
                Vetor r = new SegmentoReta(p1, p2).GetVetor();
                double px = p1.getX() + fraction * r.getX();
                double py = p1.getY() + fraction * r.getY();
                return new Ponto(px, py);
            }
            remaining -= segTime;
        }
        return points.getLast();
    }


}
