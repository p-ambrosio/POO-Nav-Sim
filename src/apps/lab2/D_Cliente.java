package apps.lab2;

import utils.Ponto;
import utils.SegmentoReta;
import utils.Vetor;

import java.util.Scanner;

public class D_Cliente {
    static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Ponto p = new Ponto(input.nextDouble(), input.nextDouble());
        Vetor v = new Vetor(input.nextDouble(), input.nextDouble());
        SegmentoReta sr = new SegmentoReta(p, v);

        System.out.println(sr.distOrdem());
    }
}
