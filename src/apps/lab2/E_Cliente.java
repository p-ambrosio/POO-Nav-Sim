package apps.lab2;

import utils.Ponto;
import utils.SegmentoReta;
import utils.Vetor;

import java.util.Scanner;

public class E_Cliente {
    static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Ponto p = new Ponto(input.nextDouble(), input.nextDouble());
        Vetor v = new Vetor(input.nextDouble(), input.nextDouble());
        Vetor v2 = new Vetor(input.nextDouble(), input.nextDouble());

        SegmentoReta reta = new SegmentoReta(p,v);

        System.out.println(reta.intersect(v2));

        input.close();
    }
}
