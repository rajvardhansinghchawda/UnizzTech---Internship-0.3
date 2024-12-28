package firstTask;

import java.util.Scanner;

public class currency {
    public static void main(String[] args) {
        int from;
        int to;
         String[] currenies = {"rupee", "EUR", "BRITISH POUND","USD"};
 while(true) {
        System.out.println("1. rupee    2.EUR   3.BRITISH POUND  4.USD");
        Scanner input = new Scanner(System.in);
        while (true) {
        System.out.print(" From: ");
        from = input.nextInt();
         if (from>0 && from<=4) { break;}
        }
        while (true) {
            System.out.print(" To: ");
            to = input.nextInt();
            if (to>0 && to<=4) { break;}
        }
         if (from<=4 && to<=4) {
            double amount;
            System.out.println("Entre amount");
            amount = input.nextDouble();
            var sc = new convertor(to, from, amount);
            sc.setCurrenies(currenies);
            sc.Calculate();
        }
        else
            System.out.println("Invalid input");
     System.out.println("You want to convert further \n yes(y): NO(n):");
     char choice = input.next().charAt(0);
     if (choice == 'n'|| choice=='N') {break;}
    }
    }
}
