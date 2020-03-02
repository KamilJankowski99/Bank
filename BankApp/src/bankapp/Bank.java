package bankapp;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Deposit implements Runnable {

    private final Account account;
    private final int wplata;

    public Deposit(Account account, int wplata) {
        this.wplata = wplata;
        this.account = account;
    }

    public int close() {
        return this.account.getBalance();
    }

    @Override
    public void run() {
        try {
            int currentBalance = this.account.getBalance();
            int newBalance = currentBalance + this.wplata;
            this.account.setBalance(newBalance);
            //return this.account.getBalance();
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Bank {

    public Scanner s = new Scanner(System.in);

    private ThreadPoolExecutor executor;

    public Bank(int numberOfThreads) {
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
    }

    public void close() {
        this.executor.shutdown();
    }

    public boolean isExecutorDone() {
        return (this.executor.isShutdown());
    }

    public int storepayment(Account account) {
        System.out.println("Kwota do zapłaty");
        int x = s.nextInt();
        int currentBalance = account.getBalance();
        int newBalance = currentBalance - x;
        account.setBalance(newBalance);
        return account.getBalance();
    }

    public void deposit(Account account, int wplata) {
        Deposit operacja = new Deposit(account, wplata);
        this.executor.execute(operacja);
    }

    public int withdraw(Account account) {
        System.out.println("Ile wypłacić");
        int x = s.nextInt();
        int currentBalance = account.getBalance();
        int newBalance = currentBalance - x;
        account.setBalance(newBalance);
        return account.getBalance();
    }

    public int[] transfer(Account accountA, Account accountB) {
        System.out.println("Ile przelać");
        int x = s.nextInt();
        int currentBalanceA = accountA.getBalance();
        int currentBalanceB = accountB.getBalance();
        int newBalanceA = currentBalanceA - x;
        int newBalanceB = currentBalanceB + x;
        accountA.setBalance(newBalanceA);
        accountB.setBalance(newBalanceB);
        return new int[]{accountA.getBalance(), accountB.getBalance()};
    }
}
