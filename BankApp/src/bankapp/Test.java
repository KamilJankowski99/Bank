package bankapp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {

    public Scanner s; 
    public Random rand;
    public static Account login(){//metoda pozwala wybrac konto na podstawie jego numeru(nazwie pliku) i utworzyc nowy obiekt wypelniony odpowiednio danymi
        boolean ch=false;
        Account ac1= new Account();
        while (!ch){
        Scanner nr = new Scanner(System.in);
            try{
                System.out.print("Podaj numer konta: ");
                String numer = nr.nextLine();
                numer= numer+".txt";
                Scanner f = new Scanner(new File(numer));
                ac1.setName(f.nextLine());
                ac1.setSurname(f.nextLine());
                ac1.setAccnumber(f.nextLine());
                ac1.setCnumber(f.nextLine());
                ac1.setBalance(f.nextInt());
                ch=true;
        }
        catch(FileNotFoundException e){
            System.out.println("");
            System.out.println("Konto nie istnieje"); 
            System.out.println("");
            nr.reset();

        }   
        }
        return ac1;
    }
    
    
    public static void update(Account a,int newbal){//metoda zmienia saldo w odpowiednim pliku
        String num = a.getAccnumber();
        num=num+".txt"; 
        try{
            
            PrintWriter pw= new PrintWriter(num);
            pw.println(a.getName());
            pw.println(a.getSurname());
            pw.println(a.getAccnumber());
            pw.println(a.getCnumber());
            pw.println(newbal);
            pw.close();
            
        }
        catch(FileNotFoundException e){
            System.out.println("nie wiem jak, ale pliku brak :/");
        }
        
    }
    public static void newClient(){//metoda tworzy nowy plik klienta nazwany losowym numerem konta
        Random rac= new Random();
        String acnum="";
        String cnum="";
        for(int y=0;y<13;y++){
            int pn= rac.nextInt(89)+10;
            acnum=acnum+Integer.toString(pn);
        }
        for(int y=0;y<8;y++){
            int pn= rac.nextInt(89)+10;
            cnum=cnum+Integer.toString(pn);
        }
        Scanner cl= new Scanner(System.in);
        System.out.println("");
        System.out.print("Podaj imię: ");
        String cname=cl.nextLine();
        System.out.print("Podaj nazwisko: ");
        String csname=cl.nextLine();
        System.out.print("Podaj saldo: ");
        String cbalance=cl.nextLine();
        try{
            
            PrintWriter pw= new PrintWriter(acnum+".txt");
            pw.println(cname);
            pw.println(csname);
            pw.println(acnum);
            pw.println(cnum);
            pw.println(cbalance);
            pw.close();
            System.out.println("");
            System.out.println("Konto zostało utworzone pomyślnie");
            System.out.println("Twoj numer konta to: "+acnum);
            System.out.println("Twoj numer karty to: "+cnum);
        }
        catch(FileNotFoundException e){
            System.out.println("nie wiem jak, ale pliku brak :/");
        }
    }
    public static void main(String[] args) throws FileNotFoundException{
        Scanner s = new Scanner(System.in); 
        Random rand = new Random();
        int initialBalanceA = 50000;
        int initialBalanceB = 50000;
        int wygenerowanaLiczba;
        int wygenerowanaLiczba2;
        int withdrawAmountB = 0;
        int depositAmountA = 0;
        int depositAmountB = 0;
            
        Account acA = new Account("John", "Doe", initialBalanceA, "12345678901234567890123456");
        Account acB = new Account("John", "Doe", initialBalanceB, "12345678901234567890123456");
        Bank bank = new Bank(1);

        System.out.println("Saldo poczatkowe konta A: " + acA.getBalance());
        System.out.println("Saldo poczatkowe konta B: " + acB.getBalance());

        int transferAmount = rand.nextInt(100);
        System.out.println("Kwota transferowana z konta A na konto B: " + transferAmount);
        bank.transfer(acA, acB, transferAmount);
        System.out.println("Zadanie transferu zostalo umieszczone w puli watkow");

        for (int i = 1; i <= 40000; i++) {
            wygenerowanaLiczba = rand.nextInt(100);
            bank.withdraw(acB, wygenerowanaLiczba);
            withdrawAmountB = withdrawAmountB + wygenerowanaLiczba;
        }
        System.out.println("Kwota do pobrania z konta B: " + withdrawAmountB);
        System.out.println("Zadania pobrania zostalo umieszczone w puli watkow");

        for (int i = 1; i <= 40000; i++) {
            wygenerowanaLiczba = rand.nextInt(100);
            wygenerowanaLiczba2 = rand.nextInt(100);
            bank.deposit(acA, wygenerowanaLiczba);
            bank.deposit(acB, wygenerowanaLiczba2);
            depositAmountA = depositAmountA + wygenerowanaLiczba;
            depositAmountB = depositAmountB + wygenerowanaLiczba2;
        }

        System.out.println("Suma kwot wplaty na konto A: " + depositAmountA);
        System.out.println("Suma kwot wplaty na konto B: " + depositAmountB);
        System.out.println("Zlecenia wplat przyjete");

        bank.close(); //zamknij pule watkow

        while (!bank.isExecutorDone()) {
        }//poczekaj az pula watkow jest zamknieta tzn wszystkie taski zostaly wykonane
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }//troche czasu na propagacje - w kolejce wszystko jest estymacja
        System.out.println("Saldo konta A: " + acA.getBalance());
        System.out.println("Saldo konta B: " + acB.getBalance());
        int expectedBalanceA = initialBalanceA - transferAmount + depositAmountA;
        int expectedBalanceB = initialBalanceB + transferAmount + depositAmountB - withdrawAmountB;
        System.out.println("Spodziewane saldo konta A: " + expectedBalanceA);
        System.out.println("Spodziewane saldo konta B: " + expectedBalanceB);
        /////////////////////////////////////////////////////////
        //Account ac1=login();//wprowadzenie konta A, ktorego maja dotyczyc wszystkie podstawowe operacje(dopiero po wyborze opcji przelewu wykonywana bedzie podobna metoda pobierajaca dane z B)
        //System.out.println("Name: "+ac1.getName()+" Surname: "+ac1.getSurname()+" Account Number: "+ac1.getAccnumber()+" Card Number: "+ac1.getCnumber()+" Balance: "+ac1.getBalance());
        //int nb=15000;//przykladowe nowe saldo konta
        //update(ac1,nb);//przyklad wprowadzania nowego salda konta po wykonaniu wybranej operacji
        //newClient();//przyklad tworzenia konta
    }
        
        
    } 

