package bankapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.sql.Timestamp;

class Deposit implements Runnable {

    private Account account;
    private final int wplata;

    public Deposit(Account account, int wplata) {
        this.wplata = wplata;
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
        if(currentBalance>=this.wyciag){
            int newBalance = currentBalance - this.wyciag;
            this.account.setBalance(newBalance);
        }
        else{
            System.out.println("Niewystarczajaca ilosc srodkow na koncie "+this.account.getAccnumber());
        }
    }
}

class Transfer implements Runnable {

    private Account accountA;
    private Account accountB;
    private final int kwota;

    public Transfer(Account accountA, Account accountB, int kwota) {
        this.accountA = accountA;
        this.accountB = accountB;
        this.kwota = kwota;
    }
    
    public List<Account> getAccounts(){
    List<Account> accounts = new ArrayList<Account>();    
    accounts.add(this.accountA);
    accounts.add(this.accountB);
        return accounts;
    }
    public void setAccounts(List<Account> accounts){
        this.accountA = accounts.get(0);
        this.accountB = accounts.get(1);
    }
    
    public int[] close() {
        return new int[]{accountA.getBalance(), accountB.getBalance()};
    }

    @Override
    public void run() {
        int currentBalanceA = accountA.getBalance();
        int currentBalanceB = accountB.getBalance();
        if(currentBalanceA>=this.kwota){
            int newBalanceA = currentBalanceA - this.kwota;
            int newBalanceB = currentBalanceB + this.kwota;
            accountA.setBalance(newBalanceA);
            accountB.setBalance(newBalanceB);
        }
        else{
            System.out.println("Niewystarczajaca ilosc srodkow na koncie " + accountA.getAccnumber());
        }
    }
}

public class Bank {
    
    public List<Account> accounts = new ArrayList<Account>();
    public static List<Withdraw> withdraws = new ArrayList<Withdraw>();
    public static List<Deposit> deposits = new ArrayList<Deposit>();
    public static List<Transfer> transfers = new ArrayList<Transfer>();
    private String sharedFolder = ".\\shared";
    DiscService Service;

    private Scanner s = new Scanner(System.in);

    private ThreadPoolExecutor executor;

    public Bank(int numberOfThreads) throws IOException {
        this.Service = new DiscService(sharedFolder);
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
    
    public void deposit(Deposit operacja) {
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
    
    public void transfer(Transfer operacja) {
        this.executor.execute(operacja);
    }
    
    public void materializeAccounts(){
                for (Account account : this.accounts ){
                    int tempB = account.getBalance();
                    String tempAnum = account.getAccnumber();
                    String tempN = account.getName();
                    String tempSN = account.getSurname();
                    String tempCnum = account.getCnumber();
            try{
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String fileName = this.sharedFolder + "\\state\\" + tempAnum + "_" + timestamp.getTime() + ".acc";
                PrintWriter pw = new PrintWriter(fileName); 
                pw.println(tempN);
                pw.println(tempSN);
                pw.println(tempAnum);
                pw.println(tempCnum);
                pw.println(tempB);
                pw.close();
            }
            catch(FileNotFoundException e){
                System.out.println("Nie udalo sie stworzyc pliku z zapisem aktualnego stanu konta! " + tempAnum);
            }
        }
    }
    
    public void importAccounts() throws IOException{
                for (Account konto : Service.getAccounts()) {
                this.accounts.add(konto);
        }
    }
    
    public void bindWithdrawsAccounts(){
                for (Withdraw wyciag : this.Service.getWithdraws()) {
            for (Account account : accounts){
                String nr1 = account.getAccnumber();
                String nr2 = wyciag.getAccount().getAccnumber();
                if (nr1.equals(nr2)){
                    wyciag.setAccount(account);
                }
            }
            
            withdraws.add(wyciag);
        }
    }
    
    public void importWithdraws(){
                for (Withdraw wyciag : this.withdraws) {
                this.withdraw(wyciag);
        }
    }
    
    public void bindDepositsAccounts(){
                for (Deposit deposit : this.Service.getDeposits()) {
            for (Account account : accounts){
                String nr1 = account.getAccnumber();
                String nr2 = deposit.getAccount().getAccnumber();
                if (nr1.equals(nr2)){
                    deposit.setAccount(account);
                }
            }
            
            deposits.add(deposit);
        }
    }
    
     public void importDeposits(){
                for (Deposit deposit : this.deposits) {
                this.deposit(deposit);
        }
    }
     
     public void bindTransfersAccounts(){
                Account tempAccountA = null;
                Account tempAccountB = null;
                List<Account> accounts = new ArrayList<Account>();
                
                for (Transfer transfer : this.Service.getTransfers()) {
                    for (Account account : accounts){                
                    String actualAccount = account.getAccnumber();
                    String transferAccountA = transfer.getAccounts().get(0).getAccnumber();
                    String transferAccountB = transfer.getAccounts().get(1).getAccnumber();
                    if (actualAccount.equals(transferAccountA)) tempAccountA = account;
                    if (actualAccount.equals(transferAccountB)) tempAccountB = account;
                    }
                if (tempAccountA == null || tempAccountB == null) throw new RuntimeException();
                accounts.add(tempAccountA);
                accounts.add(tempAccountB);
              transfer.setAccounts(accounts);
            
              transfers.add(transfer);
        }
    }

     public void importTransfers(){
                for (Transfer transfer : this.transfers) {
                this.transfer(transfer);
        }
    }
}
