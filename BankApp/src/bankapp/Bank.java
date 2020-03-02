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
class Task implements Runnable 
{
    private String name;
 
    public Task(String name) 
    {
        this.name = name;
    }
     
    public String getName() {
        return name;
    }
 
    @Override
    public void run() 
    {
        try
        {
            Long duration = (long) (Math.random() * 10);
            System.out.println("Doing a task during : " + name);
            TimeUnit.SECONDS.sleep(duration);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
}


public class Bank 
{ 
    public Scanner s= new Scanner(System.in);
    
    private ThreadPoolExecutor executor;
    
    public Bank(int numberOfThreads)
    {       
         this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < 10; i++) 
        {
            Task task = new Task("Task " + i);
            System.out.println("A new task has been added : " + task.getName());
            executor.execute(task);
        }
        System.out.println("Maximum threads inside pool " + executor.getMaximumPoolSize());

    }
    
    public void close(){
                this.executor.shutdown();
    }
    
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
