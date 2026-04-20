package utils;

/**
 * Representa um círculo definido por um centro e um raio.
 * Não é definido por vértices.
 *
 * @author Pedro Ambrósio, nº88589
 * @version 1.0 [23/03/26]
 * @inv O raio deve ser maior que zero.
 */
public class Circulo extends Figura {
    private final Ponto centro;
    private final double raio;

    /**
     * Contrutor criador de uma figura, circulo que necessita de ter area>=1
     * @param centro centro do circulo
     * @param raio raio do circulo
     */
    public Circulo(Ponto centro, double raio) {
        super(new Ponto[]{centro});
        this.centro = centro;
        this.raio = raio;
        if (raio <= 0) {
            System.out.println("Circulo:iv");
            System.exit(0);
        }
    }

    /**
     * Getter do centro do circulo
     * @return Centro
     */
    public Ponto getCentro() {
        return centro;
    }

    /**
     * Getter do raio do circulo
     * @return raio
     */
    public double getRaio() {
        return raio;
    }

    /**
     * Calculo de verificão se a figura interseta um segmento de reta
     * @param sr segemento de reta
     * @return v or f
     */
    @Override
    public boolean intersects (SegmentoReta sr){
        Ponto p1 = sr.getA();
        Ponto p2 = sr.getB();

        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();

        // Vetor de p1 ao centro
        double fx = p1.getX() - centro.getX();
        double fy = p1.getY() - centro.getY();

        double a = dx * dx + dy * dy;
        double b = 2 * (fx * dx + fy * dy);
        double c = fx * fx + fy * fy - raio * raio;

        double discriminante = b * b - 4 * a * c;

        if (discriminante < 0) {
            return false; // sem interseção
        }

        double sqrtDisc = Math.sqrt(discriminante);
        double t1 = (-b - sqrtDisc) / (2 * a);
        double t2 = (-b + sqrtDisc) / (2 * a);

        return (t1 >= 0 && t1 <= 1) || (t2 >= 0 && t2 <= 1);
    }

    /**
     * Função de calculo da area de um circulo
     * @return area do circulo
     */
    @Override
    public double Area(){
        return Math.PI * Math.pow(raio, 2);
    }

    /**
     * Getter de arestas
     * @return arestas
     */
    @Override
    public SegmentoReta[] getArestas() {
        return new SegmentoReta[0];
    }
}
