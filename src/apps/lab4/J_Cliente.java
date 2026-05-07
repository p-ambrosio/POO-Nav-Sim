package apps.lab4;

import utils.Circulo;
import utils.Poligono;
import utils.Lab4.Quadrado;
import utils.Lab4.Retangulo;
import utils.Lab4.Triangulo;
import utils.Ponto;
import utils.Route;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class J_Cliente {

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
        String s2 = sc.nextLine();
        String geo = s2.substring(0,1);
        String verts = s2.substring(2);

        String[] d2 = verts.split(" ");

        sc.close();

        Ponto[] p1 = new Ponto[(d.length/2)];
        Ponto[] p2 = new Ponto[(d2.length/2)];
        Ponto C = null;
        double raio = 0;
        grabCoordsFromStringArray(p1, d);
        if(!geo.equals("C"))
            grabCoordsFromStringArray(p2, d2);
        else {
            C = new Ponto(Integer.parseInt(d2[0]), Integer.parseInt(d2[1]));
            raio = Integer.parseInt(d2[2]);
        }

        Route r = new Route(p1);
        Ponto[] ip;

        switch (geo){
            case "S":
                Quadrado q = new Quadrado(p2);
                ip = q.findIntersection(r);

                if(ip == null || ip.length == 0){
                    IO.println("null");
                    System.exit(0);
                }
                for(int i=0;i<ip.length-1;i++){
                    IO.print(ip[i] + " ");
                }
                IO.println(ip[ip.length-1]);

                break;
            case "P":
                Poligono p = new Poligono(p2);
                ip = p.findIntersection(r);

                if(ip == null || ip.length == 0){
                    IO.println("null");
                    System.exit(0);
                }
                for(int i=0;i<ip.length-1;i++){
                    IO.print(ip[i] + " ");
                }
                IO.println(ip[ip.length-1]);
                break;
            case "T":
                Triangulo t = new Triangulo(p2);
                ip = t.findIntersection(r);

                if(ip == null || ip.length == 0){
                    IO.println("null");
                    System.exit(0);
                }
                for(int i=0;i<ip.length-1;i++){
                    IO.print(ip[i] + " ");
                }
                IO.println(ip[ip.length-1]);
                break;
            case "R":
                Retangulo ret = new Retangulo(p2);
                ip = ret.findIntersection(r);

                if(ip == null || ip.length == 0){
                    IO.println("null");
                    System.exit(0);
                }
                for(int i=0;i<ip.length-1;i++){
                    IO.print(ip[i] + " ");
                }
                IO.println(ip[ip.length-1]);
                break;
            case "C":

                Circulo cir = new Circulo(C,raio);
                ip = cir.findIntersection(r);

                if(ip == null || ip.length == 0){
                    IO.println("null");
                    System.exit(0);
                }
                for(int i=0;i<ip.length-1;i++){
                    IO.print(ip[i] + " ");
                }
                IO.println(ip[ip.length-1]);
                break;
            default: break;
        }

    }

}
