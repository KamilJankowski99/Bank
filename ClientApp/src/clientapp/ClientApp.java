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

    public static Account checkacc() {
        boolean ch = false;
        Account ac1 = new Account();
        String sharedFolder = "C:\\Users\\Brzezik\\Documents\\NetBeansProjects\\BankApp(6)\\shared\\";
        while (!ch) {
            Scanner nr = new Scanner(System.in);
            try {
                String numer = nr.nextLine();
                ac1.setAccnumber(numer);
                numer = sharedFolder +numer+ ".acc";
                Scanner f = new Scanner(new File(numer));
                ch = true;
            } catch (FileNotFoundException e) {
                System.out.println("");
                System.out.println("Podane konto nie istnieje");
                System.out.println("");
                nr.reset();

            }
        }
        return ac1;
    }
    public static void newClient() {
        String sharedFolder = "C:\\Users\\Brzezik\\Documents\\NetBeansProjects\\BankApp(6)\\shared\\";
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

            PrintWriter pw = new PrintWriter(sharedFolder + acnum + ".acc"); 
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
        String sharedFolder = "C:\\Users\\Brzezik\\Documents\\NetBeansProjects\\BankApp(6)\\shared\\";
        Scanner c1 = new Scanner(System.in);
        Random r= new Random();
        String command="";
        for (int y = 0; y < 5; y++) {
            int pn = r.nextInt(89) + 10;
            command = command + Integer.toString(pn);
        }   
        System.out.print("Podaj kwote: ");
        String deposit = c1.nextLine();
        System.out.println("Podaj numer konta: ");
        String accnr = checkacc().getAccnumber();
                        
        try{
            PrintWriter pw = new PrintWriter(sharedFolder + command + ".dep");
            pw.println(accnr);
            pw.println(deposit);
            pw.close();
        }catch (FileNotFoundException e){
            
        }
    }
    
    public static void commandWithdraw(){
        String sharedFolder = "C:\\Users\\Brzezik\\Documents\\NetBeansProjects\\BankApp(6)\\shared\\";
        Scanner c1 = new Scanner(System.in);
        Random r= new Random();
        String command="";
        for (int y = 0; y < 5; y++) {
            int pn = r.nextInt(89) + 10;
            command = command + Integer.toString(pn);
        }
                
        System.out.print("Podaj kwote: ");
        String withdraw = c1.nextLine();
        System.out.println("Podaj numer konta: ");
        String accnr = checkacc().getAccnumber();
                        
        try{
            PrintWriter pw = new PrintWriter(sharedFolder + command + ".wit");
            pw.println(accnr);
            pw.println(withdraw);
            pw.close();
        }catch (FileNotFoundException e){
            
        }
    }
    
    public static void commandTransfer(){
        String sharedFolder = "C:\\Users\\Brzezik\\Documents\\NetBeansProjects\\BankApp(6)\\shared\\";
        Scanner c1 = new Scanner(System.in);
        Random r= new Random();
        String command="";
        for (int y = 0; y < 5; y++) {
            int pn = r.nextInt(89) + 10;
            command = command + Integer.toString(pn);
        }
        System.out.println("Podaj numer konta, z którego chcesz przelać fundusze: ");
        String accnr1 = checkacc().getAccnumber();
        System.out.println("Podaj numer konta, na które chcesz przenieść fundusze: ");   
        String accnr2 = checkacc().getAccnumber();
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
        //questionInput("Podaj wartość, którą chcesz wprowadzić: ");
        boolean cont = true;//
        boolean res = true;// 
        while (res) {
            String choice = questionInput("Co chcesz zrobic?(o=operacje bankowe/k=nowe konto)");
            if (choice.equals("o")) {
                res = false;
                

                while (cont) {
                    choice = questionInput("Co chcesz zrobic?(wp=wplata/wy=wyplata/p=przelew/pl=platnosc)");
                    if (choice.equals("wp")) {
                        System.out.println("operacja wplaty");
                        commandDeposit();                       
                    } else if (choice.equals("wy")) {
                        System.out.println("operacja wyplaty");
                        commandWithdraw();
                    }
                    else if (choice.equals("pl")){
                        commandWithdraw();//nie widze sensu tworzenia specjalnie nowej metody bo to wlasciwie jest jedno i to samo
                    }
                      else if (choice.equals("p")) {
                        commandTransfer();
                    } else { 
                        System.out.println("Nieprawidlowa komenda");
                    }
                    choice = questionInput("Czy chcesz zakonczyc?(y/n)");
                    if (choice.equals("y")) {
                        cont = false;
                    } else if (choice.equals("n")) {
                    } else {
                        System.out.println("Nieprawidlowa komenda");
                    }
                }
            } else if (choice.equals("k")) {
                res = false;
                newClient();
            } else{
                System.out.println("Nieprawidlowa komenda");
            }
        }
        
        //
    }
}
