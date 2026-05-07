package apps.lab4;

import utils.Ponto;
import utils.Route;
import utils.SegmentoReta;

import java.util.ArrayList;
import java.util.Scanner;

public class I_Cliente {

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
        ArrayList<Ponto> rota = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String[] d = s1.split(" ");
        String s2 = sc.nextLine();
        String[] d2 = s2.split(" ");

        Ponto[] p1 = new Ponto[(d.length/2)];
        Ponto[] p2 = new Ponto[2];

        sc.close();
        grabCoordsFromStringArray(p1, d);
        grabCoordsFromStringArray(p2, d2);

        Route r = new Route(p1);
        SegmentoReta sr = new SegmentoReta(p2[0], p2[1]);
        IO.println(String.format("%.2f", r.findDistance()));
        Ponto[] ip = r.findIntersect(sr);

        if(ip == null){
            IO.println("null");
            System.exit(0);
        }

        for(int i=0;i<ip.length-1;i++){
            IO.print(ip[i] + " ");
        }
        IO.println(ip[ip.length-1]);
    }
}
