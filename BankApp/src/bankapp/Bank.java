package bankapp;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
        int currentBalance = this.account.getBalance();
        int newBalance = currentBalance + this.wplata;
        this.account.setBalance(newBalance);
    }
}

class Withdraw implements Runnable {

    private Account account;
    private final int wyciag;
    
    public Withdraw(Account account, int wyciag) {
        this.wyciag = wyciag;
        this.account = account;
    }
    
    public Account getAccount(){
        return this.account;
    }
    public void setAccount(Account account){
        this.account = account;
    }

    public int close() {
        return this.account.getBalance();
    }

    @Override
    public void run() {
        int currentBalance = this.account.getBalance();
        int newBalance = currentBalance - this.wyciag;
        this.account.setBalance(newBalance);
    }
}

class Transfer implements Runnable {

    private final Account accountA;
    private final Account accountB;
    private final int kwota;

    public Transfer(Account accountA, Account accountB, int kwota) {
        this.accountA = accountA;
        this.accountB = accountB;
        this.kwota = kwota;
    }

    public int[] close() {
        return new int[]{accountA.getBalance(), accountB.getBalance()};
    }

    @Override
    public void run() {
        int currentBalanceA = accountA.getBalance();
        int currentBalanceB = accountB.getBalance();
        int newBalanceA = currentBalanceA - this.kwota;
        int newBalanceB = currentBalanceB + this.kwota;
        accountA.setBalance(newBalanceA);
        accountB.setBalance(newBalanceB);
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

    public void withdraw(Account account, int wyciag) {
        Withdraw operacja = new Withdraw(account, wyciag);
        this.executor.execute(operacja);
    }
    
    public void withdraw(Withdraw operacja){
        this.executor.execute(operacja);
    }

    public void transfer(Account accountA, Account accountB, int kwota) {
        Transfer operacja = new Transfer(accountA, accountB, kwota);
        this.executor.execute(operacja);
    }
}
