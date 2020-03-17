package bankapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Test {

    public Scanner s;
    public Random rand;
    public static List<Account> accounts = new ArrayList<Account>();
    

    public static void main(String[] args) throws IOException {

        Bank bank = new Bank(1);
        bank.importAccounts();
        bank.importWithdraws();
        bank.materializeAccounts();
        
// Old test for manual operations       
//        Scanner s = new Scanner(System.in);
//        Random rand = new Random();
//        int initialBalanceA = 50000;
//        int initialBalanceB = 50000;
//        int wygenerowanaLiczba;
//        int wygenerowanaLiczba2;
//        int withdrawAmountB = 0;
//        int depositAmountA = 0;
//        int depositAmountB = 0;
// 
//        Account acA = new Account("John", "Doe", initialBalanceA, "12345678901234567890123456");
//        Account acB = new Account("John", "Doe", initialBalanceB, "12345678901234567890123456");
//        System.out.println("Saldo poczatkowe konta A: " + acA.getBalance());
//        System.out.println("Saldo poczatkowe konta B: " + acB.getBalance());
//
//        int transferAmount = rand.nextInt(100);
//        System.out.println("Kwota transferowana z konta A na konto B: " + transferAmount);
//        bank.transfer(acA, acB, transferAmount);
//        System.out.println("Zadanie transferu zostalo umieszczone w puli watkow");
//
//        for (int i = 1; i <= 40000; i++) {
//            wygenerowanaLiczba = rand.nextInt(100);
//            bank.withdraw(acB, wygenerowanaLiczba);
//            withdrawAmountB = withdrawAmountB + wygenerowanaLiczba;
//        }
//        System.out.println("Kwota do pobrania z konta B: " + withdrawAmountB);
//        System.out.println("Zadania pobrania zostalo umieszczone w puli watkow");
//
//        for (int i = 1; i <= 40000; i++) {
//            wygenerowanaLiczba = rand.nextInt(100);
//            wygenerowanaLiczba2 = rand.nextInt(100);
//            bank.deposit(acA, wygenerowanaLiczba);
//            bank.deposit(acB, wygenerowanaLiczba2);
//            depositAmountA = depositAmountA + wygenerowanaLiczba;
//            depositAmountB = depositAmountB + wygenerowanaLiczba2;
//        }
//
//        System.out.println("Suma kwot wplaty na konto A: " + depositAmountA);
//        System.out.println("Suma kwot wplaty na konto B: " + depositAmountB);
//        System.out.println("Zlecenia wplat przyjete");
//
//        bank.close(); //zamknij pule watkow
//
//        while (!bank.isExecutorDone()) {
//        }//poczekaj az pula watkow jest zamknieta tzn wszystkie taski zostaly wykonane
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        }//troche czasu na propagacje - w kolejce wszystko jest estymacja
//        System.out.println("Saldo konta A: " + acA.getBalance());
//        System.out.println("Saldo konta B: " + acB.getBalance());
//        int expectedBalanceA = initialBalanceA - transferAmount + depositAmountA;
//        int expectedBalanceB = initialBalanceB + transferAmount + depositAmountB - withdrawAmountB;
//        System.out.println("Spodziewane saldo konta A: " + expectedBalanceA);
//        System.out.println("Spodziewane saldo konta B: " + expectedBalanceB);

    }

}
