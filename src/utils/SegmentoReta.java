package utils ;

/**
 * A class SegmentoReta permite a criacao de um segmento de reta num plano bidimensional
 * atraves de dois pontos, o inicial e um vetor de posicao que define o seu final.
 * Permite calcular a intersecao com os vetores
 *
 * @author Pedro Ambrósio, nº88589
 * @version 3.0 [2/03/26]
 * @inv Os pontos A e B não podem ser iguais
 */
public class SegmentoReta {
    private final Ponto A;
    private final Ponto B;
    /**
     * Construtor que cria um segmento de reta atraves de dois pontos
     * caso iguais devolve a mensagem de erro
     * @param pA ponto A
     * @param pB ponto B
     */
    public SegmentoReta(Ponto pA, Ponto pB) {
        this.A = pA;
        this.B = pB;

        if(pA.getX() == pB.getX() && pA.getY() == pB.getY()){
            System.out.println("SegmentoReta:iv");
            System.exit(0);
        }
    }
    /**
     * Construtor que cria um segmento de reta atraves do ponto inicial e do vetor posicao
     * caso o vetor posicao tenha um módulo de 0 acaba o programa com a mensagem de erro
     * @param pI ponto inicial
     * @param vP ponto vetor
     */
    public SegmentoReta(Ponto pI, Vetor vP) {
        this.A = pI;
        double xB = pI.getX()+ vP.getX();
        double yB = pI.getY()+vP.getY();
        this.B = new Ponto(xB, yB);
        if(vP.calcMod() == 0){
            System.out.println("SegmentoReta:iv");
            System.exit(0);
        }
    }
    /**
     * Getter de A
     * @return Ponto A
     */
    public Ponto getA() {
        return A;
    }
    /**
     * Getter de B
     * @return Ponto B
     */
    public Ponto getB() {
        return B;
    }

    /**
     *  Calcula o ponto de interseção entre este segmento de reta e um vetor com o metodo
     *  encontrado na wikipedia (link descrito)
     * @param v vetor com que se calcula a intersecao
     * @return o ponto de intersecao caso valido ou null caso contrario
     * @see "https://en.wikipedia.org/wiki/Line–line_intersection"
     */
    public Ponto Intersect(Vetor v){
        //Cord of the points
        double x1 = A.getX();
        double y1 = A.getY();
        double x2 = B.getX();
        double y2 = B.getY();
        //cord of line of vector
        double x3= 0;
        double y3 = 0;
        double x4 = v.getX();
        double y4 = v.getY();

        double denominador = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / denominador;
        double u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / denominador;

        // Verification se esta dentro dos limites
        if (t >= 0 && t <= 1 && u >= 0) {
            // Calculo do ponto de interseção
            double xi = x1 + t * (x2 - x1);
            double yi = y1 + t * (y2 - y1);
            return new Ponto(xi, yi);
        }
        return null; //null caso contrario
    }

    /**
     * Caclcula o ponto de interseção entre dois segmentos de reta seguindo a logica
     * do outro intersect para os cálculos
     * @param sr segmento de reta a testar
     * @return o ponto de interseção ou null caso invalido
     */
    public Ponto Intersect(SegmentoReta sr){
        double x1 = this.A.getX();
        double y1 = this.A.getY();
        double x2 = this.B.getX();
        double y2 = this.B.getY();

        double x3 = sr.getA().getX();
        double y3 = sr.getA().getY();
        double x4 = sr.getB().getX();
        double y4 = sr.getB().getY();

        double denominador = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / denominador;
        double u = ((x1 - x3) * (y1 - y2) - (y1 - y3) * (x1 - x2)) / denominador;

        if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
            double xi = x1 + t * (x2 - x1);
            double yi = y1 + t * (y2 - y1);
            return new Ponto(xi, yi);
        }

        return null;
    }

    /**
     * A função premite obter o vetor posição r com módulo
     * igual à distância de A a B e direção de A para B
     * @return Vetor um novo vetor com a posição r esperada
     */
    public Vetor GetVetor(){
        double rX = B.getX() - A.getX();
        double rY = B.getY() - A.getY();
        return new Vetor(rX, rY);
    }
    /**
     * Retorna o segmento de reta no formato pedido, onde os pontos estao ordenados
     * pela sua aproximação a origem
     * @return O segmento de reta
     */
    public  String toString(){
        double dA= A.distanceOrigin();
        double dB = B.distanceOrigin();

        if(dB>=dA){
            return "sr(" + A.toString() + "; " + B.toString() + ")";
        } else {
            return "sr(" + B.toString() + "; " + A.toString() + ")";
        }
    }
}
