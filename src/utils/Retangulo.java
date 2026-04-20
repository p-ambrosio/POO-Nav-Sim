package utils;
/**
 * Representa um retângulo definido por 4 vértices no sentido dos ponteiros do relógio.
 *  @author Pedro Ambrósio, nº88589
 *  @version 1.0 [23/03/26]
 *  @inv Lados opostos iguais e diagonais com o mesmo comprimento.
 */
public class Retangulo extends Poligno{
    /**
     * Construtor de um poligno retangular
     * @param p1 ponto 1
     * @param p2 ponto 2
     * @param p3 ponto 3
     * @param p4 ponto 4
     */
    public Retangulo(Ponto p1, Ponto p2, Ponto p3, Ponto p4) {
        super(new Ponto[]{p1, p2, p3, p4});
        if (!isValid(p1, p2, p3, p4)) {
            System.out.println("Retangulo:iv");
            System.exit(0);
        }
    }

    /**
     * Verificador do retangulo
     * @param p1 ponto 1
     * @param p2 ponto 2
     * @param p3 ponto 3
     * @param p4 ponto 4
     * @return t or f
     */
    public boolean isValid(Ponto p1, Ponto p2, Ponto p3, Ponto p4) {
        double lado12 = p1.CalcDistance(p2);
        double lado23 = p2.CalcDistance(p3);
        double lado34 = p3.CalcDistance(p4);
        double lado41 = p4.CalcDistance(p1);
        double diag13 = p1.CalcDistance(p3);
        double diag24 = p2.CalcDistance(p4);

        boolean ladosOpostos = Math.abs(lado12 - lado34) <= 1e-9
                && Math.abs(lado23 - lado41) <= 1e-9;
        boolean diagonaisIguais = Math.abs(diag13 - diag24) <= 1e-9;

        return ladosOpostos && diagonaisIguais;
    }


}
