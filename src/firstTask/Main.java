package firstTask;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int from;
        int to;
         String[] currencies = {"rupee", "EUR", "BRITISH POUND","USD"};
 while(true) {
     System.out.println("Welcome to the Currency Converter! ");
        System.out.println("1. Rupee    2.Euro(EUR)   3.BRITISH POUND (GBP) 4.US Dollar (USD)");
        Scanner input = new Scanner(System.in);
        while (true) {
        System.out.print(" From Currency : ");
        from = input.nextInt();
         if (from>0 && from<=4) { break;}
        }
        while (true) {
            System.out.print(" To Currency : ");
            to = input.nextInt();
            if (to>0 && to<=4) { break;}
        }
         if (from<=4 && to<=4) {
            double amount;
            System.out.println("Entre amount which you want to convert");
            amount = input.nextDouble();
            var sc = new convertor(to, from, amount);
            sc.setCurrencies(currencies);
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
