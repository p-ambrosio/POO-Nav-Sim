package apps.lab1;

import utils.Vetor;
import java.util.Scanner;

class A_Cliente {
    static void main(String[] args){
        Scanner input = new Scanner(System.in);

        Vetor a = new Vetor(input.nextDouble(),input.nextDouble());

        double v = a.moduloPosicao();
        System.out.printf("%.2f%n",a.moduloPosicao());
    }
}
