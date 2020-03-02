/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapp;

import java.util.Scanner;

/**
 *
 * @author Brzezik
 */
public class Test {

    public Scanner s;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Account acA = new Account("John", "Doe", 50000, "12345678901234567890123456");
        Account acB = new Account("John", "Doe", 50000, "12345678901234567890123456");
        Bank bank = new Bank(4);
        System.out.println("Saldo konta A: " + acA.getBalance());
        System.out.println("Saldo konta B: " + acB.getBalance());
        int result[] = bank.transfer(acA, acB);
        System.out.println("Konto A: " + result[0] + " Konto B: " + result[1]);
        System.out.println("Saldo konta A: " + acA.getBalance());
        System.out.println("Saldo konta B: " + acB.getBalance());
        System.out.println("Kwota do wplaty na konto B");
        int depositAmount = s.nextInt();
        bank.deposit(acB, depositAmount);
        bank.close(); //zamknij pule watkow
        while (!bank.isExecutorDone()) {
        }//poczekaj az pula watkow jest zamknieta tzn wszystkie taski zostaly wykonane
        System.out.println("Saldo konta A: " + acA.getBalance());
        System.out.println("Saldo konta B: " + acB.getBalance());
    }
}
