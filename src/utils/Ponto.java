package utils;

/**
 * Classe que representa um ponto no plano bidimensional com coordenadas x e y
 * Permite tambem calcular a distância à origem e obter a representação textual do ponto
 *
 * @author Pedro Ambrósio, nº88589
 * @version 1.0 [09/02/26]
 *
 */
public class Ponto {
    private final double x;
    private final double y;
    /**
     *  Constroi um ponto com as coordenadas x e y
     * @param x Coordenada x
     * @param y Coordenada y
     */
    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Getter de x
     * @return x
     */
    public double getX() {
        return x;
    }
    /**
     * Getter de y
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * Calcula a distancia do ponto a (0,0)
     * @return A distância do ponto a origem
     */
    public double distanceOrigin(){
        Vetor v = new Vetor(this.x, this.y);
        return v.calcMod();
    }

    /**
     * Calcula a distancia entre dois pontos a e b
     * @return a distancia de A a B.
     */
    public double CalcDistance(Ponto that){
        return Math.sqrt(Math.pow(that.getX()- this.x, 2)+Math.pow(that.getY()- this.y, 2));
    }
    /**
     * Da return as coordenadas x e y em texto com duas casas decimais
     * @return as coordenadas x e y na formatacao indicada
     */
    public String toString() {
        return  String.format("(%.2f,%.2f)", x, y);
    }

}
