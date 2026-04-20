package apps;

import utils.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe cliente, responsavel pela leitura de dados
 *
 * @author Pedro Ambrósio, nº88589
 * @version 7.0 [06/04/26]
 * @inv A entrada deve ter um número par de coordenadas para a rota; caso contrário imprime "Rota:iv".
 */
public class ClienteL {

    /**
     * Ponto de entrada do programa.
     * @param args argumentos da linha de comandos
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Coordenadas
        String[] tokens = sc.nextLine().trim().split("\\s+");
        if (tokens.length % 2 != 0) {
            System.out.println("Rota:iv"); //If ood
            return;
        }
        List<Ponto> points = new ArrayList<>();
        for (int i = 0; i < tokens.length; i += 2) {
            double x = Double.parseDouble(tokens[i]);
            double y = Double.parseDouble(tokens[i + 1]);
            points.add(new Ponto(x, y));
        }

       //Vetor de vento
        String[] wTokens = sc.nextLine().trim().split("\\s+");
        Vetor w = new Vetor(Double.parseDouble(wTokens[0]), Double.parseDouble(wTokens[1]));
       //velocidade linear vl
        double vl = Double.parseDouble(sc.nextLine().trim());
        // tempo
        double t = Double.parseDouble(sc.nextLine().trim());
        Navegacao nav = new Navegacao(new Route(points));

        System.out.printf("%.2f%n", nav.routeLength());
        System.out.printf("%.2f%n", nav.totalTime(vl));
        System.out.println(nav.positionAtTime(t, vl));

        List<Vetor> speeds = nav.speedVectors(w, vl);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < speeds.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(speeds.get(i).toString());
        }
        System.out.println(sb);
    }
}
