package utils;

/**
 * Representa um polígono definido por uma lista ordenada de vértices no sentido
 * dos ponteiros do relógio, com no mínimo 3 vértices.
 *
 * @author Pedro Ambrósio, nº88589
 * @version 1.0 [23/03/26]
 * @inv Os polígonos são definidos por uma lista ordenada de pontos (vértices), no sentido dos ponteiros do relógio, em número igual ou superior a 3.
 */
public class Poligno extends Figura {
    /**
     * Contrutor de uma figura do tipo poligno
     * @param vertices vertices do poligno que necessitam de >=3
     */
   public Poligno(Ponto[] vertices){
       super(vertices);
       if (vertices == null || vertices.length < 3) {
           System.out.println("Poligono:iv");
           System.exit(0);
       }
   }

    /**
     * Calculo da area de um poligno
     * @return A area
     */
    @Override
    public double Area() {
        Ponto[] v = getVertices();
        double soma = 0;
        int n = v.length;
        for (int i = 0; i < n; i++) {
            Ponto p1 = v[i];
            Ponto p2 = v[(i + 1) % n];
            soma += p1.getX() * p2.getY() - p2.getX() * p1.getY();
        }
        return Math.abs(soma) / 2.0;
    }
}
