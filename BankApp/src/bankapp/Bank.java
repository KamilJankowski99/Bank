/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapp;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Brzezik
 */

public class Bank implements Runnable{ 
transferObject to;
    
    @Override
    public void run()
    {
        try
        {
            switch (to.operacja){
                case "transfer": to.endBalance = this.transfer(to.accountA, to.accountB);
                case "deposit":to.endBalance = this.deposit(to.accountA);
                case "withdraw":to.endBalance = this.withdraw(to.accountA);
            }
            TimeUnit.SECONDS.sleep(1);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        
    }
    
    public Bank(transferObject operacja)
    {
        this.to = operacja;
        
    }
    

    
    public Scanner s= new Scanner(System.in);
    
    public int[] storepayment(Account account){
        System.out.println("Kwota do zapłaty");
        int x = s.nextInt();
        int currentBalance = account.getBalance();
        int newBalance = currentBalance - x;
        account.setBalance(newBalance);
        return new int[]{account.getBalance()};
    }
    public int[] deposit(Account account){
        System.out.println("Ile wpłacić");
        int x= s.nextInt();
        int currentBalance = account.getBalance();
        int newBalance = currentBalance + x;
        account.setBalance(newBalance);
        return new int[]{account.getBalance()};
    }
    public int[] withdraw(Account account){
        System.out.println("Ile wypłacić");
        int x= s.nextInt();
        int currentBalance = account.getBalance();
        int newBalance = currentBalance - x;
        account.setBalance(newBalance);
        return new int[]{account.getBalance()};
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
