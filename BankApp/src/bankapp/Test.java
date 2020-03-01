/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapp;

/**
 *
 * @author Brzezik
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Account acA = new Account("John", "Doe", 50000, "12345678901234567890123456");
        Account acB = new Account("John", "Doe", 50000, "12345678901234567890123456");
        transferObject operacja = new transferObject();
        Bank bank = new Bank(operacja);
        System.out.println("Saldo konta A: " +acA.getBalance());
        System.out.println("Saldo konta B: " +acB.getBalance());
        
        operacja.accountA = acA;
        operacja.accountB = acB;
        operacja.operacja = "transfer";
        
        
        bank.run();
        int[] result = operacja.endBalance;
        System.out.println("Konto A: "+result[0]+" Konto B: "+result[1]);
        System.out.println("Saldo konta A: " +acA.getBalance());
        System.out.println("Saldo konta B: " +acB.getBalance());
       // int test = bank.deposit(acB);
        System.out.println("Saldo konta A: " +acA.getBalance());
        System.out.println("Saldo konta B: " +acB.getBalance());
    }
    
}
