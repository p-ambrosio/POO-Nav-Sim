package apps;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
/**
 * Class para leitura dos inputs dos utilizadores do programa estes sendo estes as
 * as coordenadas de dois pontos que definem um segmento de reta e as coordenadas
 * de um vetor e apresentando o ponto de interseção entre o vetor e o segmento de reta.
 *
 * @author Pedro Ambrosio, nº88589
 * @version 4.0 [23/3/26]
 *
 **/
public class Cliente {
    /**
     * A main do programa
     *
     * @param args argumentos de input
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Leitura da rota (primeira linha)
        String linhaRota = sc.nextLine().trim();
        String[] tokensRota = linhaRota.split("\\s+");
        List<Ponto> pontos = new ArrayList<>();
        for (int i = 0; i < tokensRota.length; i += 2) {
            double x = Double.parseDouble(tokensRota[i]);
            double y = Double.parseDouble(tokensRota[i + 1]);
            pontos.add(new Ponto(x, y));
        }
        Route rota = new Route(pontos);

        // Leitura do segmento AB (segunda linha)
        String linhaSegmento = sc.nextLine().trim();
        String[] tokensSegmento = linhaSegmento.split("\\s+");
        double xA = Double.parseDouble(tokensSegmento[0]);
        double yA = Double.parseDouble(tokensSegmento[1]);
        double xB = Double.parseDouble(tokensSegmento[2]);
        double yB = Double.parseDouble(tokensSegmento[3]);

        // Comprimento da rota
        System.out.printf("%.2f%n", rota.Length());

        SegmentoReta segAB = new SegmentoReta(new Ponto(xA, yA), new Ponto(xB, yB));
        List<Ponto> intersecoes = rota.Intersect_Route(segAB);
        if (intersecoes.isEmpty()) {
            System.out.println("null");
        } else {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < intersecoes.size(); i++) {
                if (i > 0) sb.append(" ");
                Ponto p = intersecoes.get(i);
                sb.append(String.format("(%.2f,%.2f)", p.getX(), p.getY()));
            }
            System.out.println(sb.toString());
        }
    }
}
