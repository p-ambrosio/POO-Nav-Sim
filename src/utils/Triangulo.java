package utils;

/**
 * Representa um triângulo definido por 3 vértices não colineares,
 * no sentido dos ponteiros do relógio.
 *
 * @author Pedro Ambrósio, nº88589
 * @version 1.0 [23/03/26]
 * @inv Os três vértices não podem ser colineares.
 */
public class Triangulo extends Poligno{
    /**
     * Construtor de um trinagulo valido
     * @param p1 ponto 1
     * @param p2 ponto 2
     * @param p3 ponto 3
     */
    public Triangulo(Ponto p1, Ponto p2, Ponto p3) {
        super (new Ponto[]{p1,p2,p3});
        if(Colineares(p1,p2,p3)){
            System.out.println("Triangulo:iv");
            System.exit(0);
        }
    }

    /**
     * Verifica se o triangulo é colinear
     * @param p1 ponto 1
     * @param p2 ponto 2
     * @param p3 ponto 3
     * @return
     */
    public boolean Colineares(Ponto p1, Ponto p2, Ponto p3) {
        double area = (p2.getX() - p1.getX()) * (p3.getY() - p1.getY())
                - (p3.getX() - p1.getX()) * (p2.getY() - p1.getY());
        return Math.abs(area) <= 1e-9;
    }


}
