package utils;

import java.util.ArrayList;
import java.util.List;


/**
 * A classe AutoPilot pretende receber dados e devolver resultados,
 * calculando parâmetros de navegação aérea como velocidade e tempo.
 *
 *
 * @author Pedro Ambrosio, nº88589
 * @version 2.0 [30/3/26]
 *
 **/
public class AutoPilot {
    private final Ponto A;
    private final Ponto B;
    private final Route route;

    /**
     * Construtor que define o start point(a) e o end point(b)
     * @param A ponto de partida
     * @param B ponto do destino
     */
    public AutoPilot(Ponto A, Ponto B) {
        this.A = A;
        this.B = B;
        List<Ponto> pts = new ArrayList<>();
        pts.add(A);
        pts.add(B);
        this.route = new Route(pts);
    }
    /*

     */
    public AutoPilot(Route route){
        this.route = route;
        this.A= route.getPoints().get(0);
        this.B = route.getPoints().get(route.getPoints().size()-1);
    }
    /**
     * Calcula a velocidade vetorial necessaria para um avião realizar o seu trajeto de A para B num segmento de reta
     * com vento lateral com velocidade vetorial windSpeed, no tempo time.
     * @param w vento lateral com velocidade vetorial
     * @param time tempo
     * @return Velocidade vetorial necessaria para chegar ao seu destino.
     * @see "tutoria.ualg.pt/2025/pluginfile.php/253184/mod_resource/content/26/POO25-26_Lab-nav.pdf"
     */
    public Vetor speed(Vetor w, double time){
        Vetor sr = new SegmentoReta(A,B).GetVetor();
        return sr.mult(1.0/time).sub(w);
    }

    /**
     * Calcula o tempo necessario para o aviao se deslocar dentro do segmento de reta
     * do seu ponto de partida (a) até ao seu destino(b) com uma velocidade linear, utilizando
     * a fórmula fisica t= mod s/mod v.
     *
     * @param vl velocidade linear de deslocamento
     * @return Tempo de deslocamento de A para B
     * @see "https://mundoeducacao.uol.com.br/fisica/velocidade-media.htm"
     */
    public double time(double vl){
        Vetor sr = new SegmentoReta(A,B).GetVetor();
        double s= sr.calcMod();
        // Basically altered to my first version
        return s/vl;
    }

    /**
     *
     * @return
     */
    public double routeLenght(){
        return route.Length();
    }

    /**
     *
     * @param vl
     * @return
     */
    public double routeTime(double vl){
        return route.Length()/vl;
    }

    /**
     *
     * @param w
     * @param vl
     * @return
     */
    public List<Vetor> vectorSpeed(Vetor w, double vl){
        List<Ponto> points = route.getPoints();
        List<Vetor> speeds = new ArrayList<>();
        for (int i = 0; i < points.size() - 1; i++) {
            Ponto p1 = points.get(i);
            Ponto p2 = points.get(i + 1);
            Vetor r = new SegmentoReta(p1, p2).GetVetor();
            double segTime = r.calcMod() / vl;
            // v = r/t - w
            speeds.add(r.mult(1.0 / segTime).sub(w));
        }
        return speeds;
    }

    /**
     *
     * @param t
     * @param vl
     * @return
     */
    public Ponto positionAtTime(double t, double vl) {
        List<Ponto> points = route.getPoints();
        double remaining = t;
        for (int i = 0; i < points.size() - 1; i++) {
            Ponto p1 = points.get(i);
            Ponto p2 = points.get(i + 1);
            Vetor r = new SegmentoReta(p1, p2).GetVetor();
            double segTime = r.calcMod() / vl;
            if (remaining <= segTime) {
                // Plane is within this segment: interpolate position
                double fraction = remaining / segTime;
                double px = p1.getX() + fraction * r.getX();
                double py = p1.getY() + fraction * r.getY();
                return new Ponto(px, py);
            }
            remaining -= segTime;
        }
        // t exceeds total route time — return final destination
        return points.get(points.size() - 1);
    }

}
