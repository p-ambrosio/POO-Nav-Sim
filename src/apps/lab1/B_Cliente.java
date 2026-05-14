package apps.lab1;

import utils.Vetor;

import java.util.Scanner;

public class B_Cliente {
    static void main(String[] args){
        Scanner input = new Scanner(System.in);

        Vetor a = new Vetor(input.nextDouble(),input.nextDouble());
        Vetor b = new Vetor(input.nextDouble(),input.nextDouble());

        double v = a.produtoInterno(b);
        System.out.printf("%.2f%n",v);
    }
}
