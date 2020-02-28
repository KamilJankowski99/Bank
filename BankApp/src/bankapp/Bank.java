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
        return account.getBalance()+x;
    }
    public int deposit(Account account){
        System.out.println("Ile wpłacić");
        int x= s.nextInt();
        return account.getBalance()+x;
    }
    public int withdraw(Account account){
        System.out.println("Ile wypłacić");
        int x= s.nextInt();
        return account.getBalance()-x;
    }
    public int[] transfer(Account accountA,Account accountB){
        System.out.println("Ile przelać");
        int x= s.nextInt();
        return new int[]{accountA.getBalance()-x,accountB.getBalance()+x};
    }
}
