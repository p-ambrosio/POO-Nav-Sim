package apps.lab1;

import utils.Vetor;
import java.util.Scanner;

public class C_Cliente {
    static void main(String[] args){
        Scanner input = new Scanner(System.in);

        Vetor a = new Vetor(input.nextDouble(),input.nextDouble());
        Vetor b = new Vetor(input.nextDouble(),input.nextDouble());

        double res = a.cossineSimilarity(b);

        System.out.printf("%.2f%n",res);
    }
}
