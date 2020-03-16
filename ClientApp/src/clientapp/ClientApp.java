/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientApp {

    public Scanner s;
    public Random rand;

    public static Account login() {//metoda pozwala wybrac konto na podstawie jego numeru(nazwie pliku) i utworzyc nowy obiekt wypelniony odpowiednio danymi
        boolean ch = false;
        Account ac1 = new Account();
        while (!ch) {
            Scanner nr = new Scanner(System.in);
            try {
                System.out.print("Podaj numer konta: ");
                String numer = nr.nextLine();
                numer = numer + ".txt";
                Scanner f = new Scanner(new File(numer));
                ac1.setName(f.nextLine());
                ac1.setSurname(f.nextLine());
                ac1.setAccnumber(f.nextLine());
                ac1.setCnumber(f.nextLine());
                ac1.setBalance(f.nextInt());
                ch = true;
            } catch (FileNotFoundException e) {
                System.out.println("");
                System.out.println("Konto nie istnieje");
                System.out.println("");
                nr.reset();

            }
        }
        return ac1;
    }

    public static void update(Account a, int newbal) {//metoda zmienia saldo w odpowiednim pliku
        String num = a.getAccnumber();
        num = num + ".txt";
        try {

            PrintWriter pw = new PrintWriter(num);
            pw.println(a.getName());
            pw.println(a.getSurname());
            pw.println(a.getAccnumber());
            pw.println(a.getCnumber());
            pw.println(newbal);
            pw.close();

        } catch (FileNotFoundException e) {
            System.out.println("nie wiem jak, ale pliku brak :/");
        }

    }

    public static void newClient() {//metoda tworzy nowy plik klienta nazwany losowym numerem konta
        String sharedFolder = "C:\\Users\\Kamil\\Desktop\\Bank\\BankApp\\shared\\";
        Random rac = new Random();
        String acnum = "";
        String cnum = "";
        for (int y = 0; y < 13; y++) {
            int pn = rac.nextInt(89) + 10;
            acnum = acnum + Integer.toString(pn);
        }
        for (int y = 0; y < 8; y++) {
            int pn = rac.nextInt(89) + 10;
            cnum = cnum + Integer.toString(pn);
        }

        Scanner cl = new Scanner(System.in);
       
        System.out.print("Podaj imię: ");
        String cname = cl.nextLine();
        System.out.print("Podaj nazwisko: ");
        String csname = cl.nextLine();
        System.out.print("Podaj saldo: ");
        String cbalance = cl.nextLine();
        try {

            PrintWriter pw = new PrintWriter(sharedFolder + acnum + ".acc"); //zrobic read 
            pw.println(cname);
            pw.println(csname);
            pw.println(acnum);
            pw.println(cnum);
            pw.println(cbalance);
            pw.close();
            System.out.println("");
            System.out.println("Konto zostało utworzone pomyślnie");
            System.out.println("Twoj numer konta to: " + acnum);
            System.out.println("Twoj numer karty to: " + cnum);

        } catch (FileNotFoundException e) {
            System.out.println("nie wiem jak, ale pliku brak :/");
        }

    }
    
    public static void commandDeposit(){
        String sharedFolder = "C:\\Users\\Kamil\\Desktop\\Bank\\BankApp\\shared\\";
        Scanner c1 = new Scanner(System.in);
        String command = "Wplata";
                
        System.out.print("Podaj kwote: ");
        String deposit = c1.nextLine();
        System.out.println("Podaj numer konta: ");
        String accnr = c1.nextLine();
                        
        try{
            PrintWriter pw = new PrintWriter(sharedFolder + command + ".dep");
            pw.println(accnr);
            pw.println(deposit);
            pw.close();
        }catch (FileNotFoundException e){
            
        }
    }
    
    public static void commandWithdraw(){
        String sharedFolder = "C:\\Users\\Kamil\\Desktop\\Bank\\BankApp\\shared\\";
        Scanner c1 = new Scanner(System.in);
        String command = "Wyciąg";
                
        System.out.print("Podaj kwote: ");
        String withdraw = c1.nextLine();
        System.out.println("Podaj numer konta: ");
        String accnr = c1.nextLine();
                        
        try{
            PrintWriter pw = new PrintWriter(sharedFolder + command + ".wit");
            pw.println(accnr);
            pw.println(withdraw);
            pw.close();
        }catch (FileNotFoundException e){
            
        }
    }
    
    public static void commandTransfer(){
        String sharedFolder = "C:\\Users\\Kamil\\Desktop\\Bank\\BankApp\\shared\\";
        Scanner c1 = new Scanner(System.in);
        String command = "Przelew";
        System.out.println("Podaj numer konta, z którego chcesz przelać fundusze: ");
        String accnr1 = c1.nextLine();
        System.out.println("Podaj numer konta, na które chcesz przenieść fundusze: ");   
        String accnr2 = c1.nextLine();
        System.out.println("Podaj kwote: ");
        String transfer = c1.nextLine();
        try{
            PrintWriter pw = new PrintWriter(sharedFolder + command + ".tra");
            pw.println(accnr1);
            pw.println(accnr2);
            pw.println(transfer);
            pw.close();
        }catch (FileNotFoundException e){
            
        }
    }
    
    public static String questionInput(String question){
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        
        return answer;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String acnum = "";
        String sharedFolder = "C:\\Users\\Kamil\\Desktop\\Bank\\BankApp\\shared\\";;
        //questionInput("Podaj wartość, którą chcesz wprowadzić: ");
        boolean cont = true;//
        boolean res = true;//
        boolean exist = false;//
        Random rand = new Random();
        int initialBalanceA = 50000;
        int initialBalanceB = 50000;
        int wygenerowanaLiczba;
        int wygenerowanaLiczba2;
        int withdrawAmountB = 0;
        int depositAmountA = 0;
        int depositAmountB = 0;
        Account[] ac = new Account[5];//
        int t;//
        int acn = 1;//
        
        while (res) {
            String choice = questionInput("Co chcesz zrobic?(o=operacje bankowe/k=nowe konto)");
            if (choice.equals("o")) {
                res = false;
                //ac[0] =login();

                while (cont) {
                    choice = questionInput("Co chcesz zrobic?(wp=wplata/wy=wyplata/p=przelew)");
                    if (choice.equals("wp")) {
                        System.out.println("operacja wplaty");
                        commandDeposit();                       
                    } else if (choice.equals("wy")) {
                        System.out.println("operacja wyplaty");
                        commandWithdraw();
                    } else if (choice.equals("p")) {
                        if (acn == 5) {
                            System.out.println("przekroczono max ilosc kont");//mozna zmienic limit ale wydaje mi sie ze raczej tylu przelewow na raz nie bedziemy robic 
                        } else {
                           commandTransfer();
                        }
                    } else {
                        System.out.println("Nieprawidlowa komenda");
                    }
                    choice = questionInput("Czy chcesz zakonczyc?(y/n)");
                    if (choice.equals("y")) {
                        cont = false;
                        System.out.println("operacje zamykajace watki i zapisujace koncowe saldo do pliku/plikow");
                        if (exist) {
                            for (int i = 0; i < acn; i++) {
                                System.out.println("zapis w pliku " + i);
                                //update(ac[i],obecnesaldo);
                            }
                        } else {
                            System.out.println("zapis w jednym pliku");
                            //update(ac[0],obecnesaldo); 
                        }
                    } else if (choice.equals("n")) {
                        System.out.println("nowy watek");
                    } else {
                        System.out.println("Nieprawidlowa komenda");
                    }
                }
            } else if (choice.equals("k")) {
                res = false;
                newClient();
            } else {
                System.out.println("Nieprawidlowa komenda");
            }
        }
        
    }
}
