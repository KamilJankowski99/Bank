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
public class Bank { 
    public Scanner s= new Scanner(System.in);
    
    public int storepayment(Account account){
        System.out.println("Kwota do zapłaty");
        int x = s.nextInt();
        int currentBalance = account.getBalance();
        int newBalance = currentBalance - x;
        account.setBalance(newBalance);
        return account.getBalance();
    }
    public int deposit(Account account){
        System.out.println("Ile wpłacić");
        int x= s.nextInt();
        int currentBalance = account.getBalance();
        int newBalance = currentBalance + x;
        account.setBalance(newBalance);
        return account.getBalance();
    }
    public int withdraw(Account account){
        System.out.println("Ile wypłacić");
        int x= s.nextInt();
        int currentBalance = account.getBalance();
        int newBalance = currentBalance - x;
        account.setBalance(newBalance);
        return account.getBalance();
    }
    public int[] transfer(Account accountA,Account accountB){
        System.out.println("Ile przelać");
        int x= s.nextInt();
        int currentBalanceA = accountA.getBalance();
        int currentBalanceB = accountB.getBalance();
        int newBalanceA = currentBalanceA - x;
        int newBalanceB = currentBalanceB + x;
        accountA.setBalance(newBalanceA);
        accountB.setBalance(newBalanceB);
        return new int[]{accountA.getBalance(),accountB.getBalance()};
    }
}
