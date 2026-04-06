package utils;

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

    /**
     * Construtor que define o start point(a) e o end point(b)
     * @param A ponto de partida
     * @param B ponto do destino
     */
    public AutoPilot(Ponto A, Ponto B) {
        this.A = A;
        this.B = B;
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

}
