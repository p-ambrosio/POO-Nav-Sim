package apps;

import utils.*;

import java.util.Scanner;
/**
 * Classe responsável pela leitura dos dados de entrada
 * Cria Ponto de partida e ponto de chegada, obtem os parametros necessarios
 * e utiliza AutoPilot para calcular e assim obter o tempo de viagem e
 * o vetor das velocidades.
 *
 * @author Pedro Ambrósio, nº88589
 * @version 6.0 [30/03/26]
 */
public class ClienteK {
    public static void main() {
        Scanner sc = new Scanner(System.in);
    //Get start and finish points
        Ponto start = new Ponto(sc.nextDouble(), sc.nextDouble());
        Ponto finish = new Ponto(sc.nextDouble(), sc.nextDouble());
    //Get wind speed and direction
        Vetor w = new Vetor(sc.nextDouble(), sc.nextDouble());
    //Get linear speed
        double s = sc.nextDouble();
        sc.close();
    //Setup auto pilot and compute:
    // i) desired time to reach the finish point
    // ii) vectorial speed required
        AutoPilot ap = new AutoPilot(start, finish);
        double t = ap.time(s);
        IO.println(String.format("%.2f", t));
        IO.println(ap.speed(w, t));
    }
}
