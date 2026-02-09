package Programs;

import java.util.Scanner;

public class FactorialNo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number");
        int no = scanner.nextInt();
        int factorial = 1;
        for (int i = 1; i <= no; i++) {
            factorial = factorial * i;
        }
        System.out.print("Factorial of " + no + " = " + factorial);
    }
}

