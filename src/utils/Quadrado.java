package utils;

/**
 * Representa um quadrado definido por 4 vértices no sentido dos ponteiros do relógio.
 * @author Pedro Ambrósio, nº88589
 * @version 1.0 [23/03/26]
 * @inv Todos os lados iguais e diagonais com o mesmo comprimento.
 */
public class Quadrado extends Poligno{
    /**
     * Contrutor do poligno representado por um quadrado de 4 vertices
     * @param p1 ponto 1
     * @param p2 ponto 2
     * @param p3 ponto 3
     * @param p4 ponto 4
     */
    public Quadrado(Ponto p1, Ponto p2, Ponto p3, Ponto p4) {
        super(new Ponto[]{p1, p2, p3, p4}); //Ir diretamente ao poligno
        if (!Valid(p1, p2, p3, p4)) {
            System.out.println("Quadrado:iv");
            System.exit(0);
        }
    }

    /**
     * Verificador se um quadrado é um quadrado valido
     * @param p1 ponto 1
     * @param p2 ponto 2
     * @param p3 ponto 3
     * @param p4 ponto 4
     * @return t or f
     */
    private static boolean Valid(Ponto p1, Ponto p2, Ponto p3, Ponto p4) {
        double lado12 = p1.CalcDistance(p2);
        double lado23 = p2.CalcDistance(p3);
        double lado34 = p3.CalcDistance(p4);
        double lado41 = p4.CalcDistance(p1);
        return Math.abs(lado12 - lado23) <= 1e-9
                && Math.abs(lado23 - lado34) <= 1e-9
                && Math.abs(lado34 - lado41) <= 1e-9;
    }
}
