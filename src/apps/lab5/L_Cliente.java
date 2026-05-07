package apps.lab5;

import utils.*;

import java.util.Scanner;

public class L_Cliente {

    /**
     * Void function that will grab an array of String holding numbers, parse it, then submit as points
     * (String[]) {"1","2","3","4"} -> (Ponto[]) {(1,2);(3,4)}
     * @param p     Ponto array that will be updated with new Pontos parsed from a String
     * @param d     String array that holds all the coordinate values inserted
     */
    private static void grabCoordsFromStringArray(Ponto[] p, String[] d){
        int j=0;
        for(int i=0;i<d.length/2;i++){
            int x = Integer.parseInt(d[j]);
            int y = Integer.parseInt(d[j+1]);
            j+=2;
            p[i] = new Ponto(x,y);
        }
    }

    static void main() {
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String[] d = s1.split(" ");
        if(d.length % 2 != 0){
            IO.println("Rota:iv");
            System.exit(0);
        }
        Ponto[] pts = new Ponto[(d.length/2)];
        grabCoordsFromStringArray(pts, d);
        Route r = new Route(pts);

        String s2 = sc.nextLine();
        String[] wString = s2.split(" ");
        Vetor w = new Vetor(Double.parseDouble(wString[0]), Double.parseDouble(wString[1]));

        double vl = Double.parseDouble(sc.nextLine());
        double t = Double.parseDouble(sc.nextLine());


        Navegador n = new Navegador(r);
        IO.println((String.format("%.2f",r.findDistance())));       // route length/Distance
        IO.println((String.format("%.2f",n.time(vl))));
        IO.println(n.position(t,vl));

        Vetor[] ip = n.speedArray(w,vl);
        for(int i=0;i<ip.length-1;i++){
            IO.print(ip[i] + " ");
        }
        IO.println(ip[ip.length-1]);

    }
}
