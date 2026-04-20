package utils;

/**
 * Classe responsavel pelo vetor bidimensional criado, incluindo tambem
 * as operacoes matematicas necessarias como: Modulo, Produto interno, Cosine e intersecao
 *
 * @author Pedro Ambrosio, nº88589
 * @version 3.0 [10/2/26]
 * @inv O modulo do vetor calculado devera sempre ser maior que 0
 */
public class Vetor {
    private final double x;
    private final double y;
    /**
     * Construtor do vetor com validacao de erro
     * @param x coordenada x do vetor
     * @param y coordenada y do vetor
     */
    public Vetor(double x, double y) {
        this.x = x;
        this.y = y;
        if (calcMod()<= 0){
            System.out.println("Vetor:iv");
            System.exit(0);
        }

    }

    /**
     * Construtor do vetor com os pontos
     * @param p Ponto cujas coordenadas são usadas para criar o vetor
     */
    public Vetor(Ponto p){
        this(p.getX(), p.getY());
    }

    /**
     * Func to calculate mod
     * @return o modulo calculado do vetor
     */
    public double calcMod(){
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    /**
     * Func to calculate produto int
     * @param that o outro vetor com que se calcula o produto interno
     * @return o produto interno
     */
    public double prodInt(Vetor that){
        return (this.x*that.x)+(this.y*that.y);
    }

    /**
     * Func to calculate cosine
     * @param that o outro vetor para calcular a sim
     * @return A similaridade do cosseno
     */
    public double cosineSim(Vetor that){
        double prodInt = this.prodInt(that);
        double Modu = this.calcMod();
        double Modv = that.calcMod();

        return prodInt /(Modu * Modv);
    }
    /**
     * Getter para a coordenada x
     * @return a coordenada
     */
    public double getX() {
        return x;
    }
    /**
     * Getter para a coordenada y
     * @return a coordenada
     */
    public double getY() {
        return y;
    }

    /**
     * Calcula o ponto de interseção entre este vetor e segmento de reta
     * @param v o segmento de reta com o qual calcular a interseção
     * @return o ponto de interseção, ou null se não houver interseção
     */
    public Ponto Intersect (SegmentoReta v){
        return v.Intersect(this);
    }

    /**
     * Calcula os novos valores do vetor multiplicados pelo escalar de d
     * @param d
     * @return Vetor multiplicado pelo escalar de d
     */
    public Vetor mult(double d){
        return new Vetor(x*d,y*d);
    }

    /**
     * Calcula e retorna um vetor adicionado a outro Vetor
     * @param v Vetor a adicionar
     * @return Vetor novo vetor com a adicao de v
     */
    public Vetor add(Vetor v){
        return new Vetor(x+v.x,y+v.y);
    }

    /**
     * Calcula e retorna um vetor subtraido a outro Vetor
     * @param v Vetor a subtrair
     * @return Vetor, um novo vetor com a subtração do vetor v
     */
    public Vetor sub(Vetor v){
        return new Vetor(x-v.x,y-v.y);
    }

    /**
     * Pretende a retornar uma representação do Vetor numa string com o
     * formato [x,y]
     * @return Vetor numa string com formato [x,y] onde cada componente tem a precisão de
     * duas casas decimais
     */
    public String toString(){
        return String.format("[%.2f,%.2f]", this.x, this.y);

    }
}