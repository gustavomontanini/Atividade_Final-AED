import java.util.Scanner;

public class uri1004 {

    public static void main(String[] args) {


        System.out.println("Digite um numero e depois outro: ");
        Scanner sc = new Scanner(System.in);

        int A, B, soma;

        A = sc.nextInt();
        B = sc.nextInt();

        soma = A + B;

        System.out.println("SOMA = " + soma);

        sc.close();
    }
}