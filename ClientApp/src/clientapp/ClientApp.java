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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;


public class ClientApp {

    public Scanner s;
    public Random rand = new Random();

    public static Account checkacc() {
        boolean ch = false;
        Account ac1 = new Account();
        String sharedFolder = "..\\BankApp\\shared\\processed\\";
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
    public void newClient() throws NoSuchAlgorithmException, FileNotFoundException {
        String sharedFolder = "..\\BankApp\\shared\\";
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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        System.out.print("Podaj imię: ");
        String cname = cl.nextLine();
        System.out.print("Podaj nazwisko: ");
        String csname = cl.nextLine();
        System.out.print("Podaj saldo: ");
        String cbalance = cl.nextLine();
        String fileName = sharedFolder + acnum + "_" + Integer.toString(rand.nextInt(1000)) + "_" + timestamp.getTime() + ".acc";
        PrintWriter pw = new PrintWriter(fileName);
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

    }
    
    public void commandDeposit(){
        String sharedFolder = "..\\BankApp\\shared\\";
        Scanner c1 = new Scanner(System.in);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String command = "Wplata";
                
        System.out.print("Podaj kwote: ");
        String deposit = c1.nextLine();
        System.out.println("Podaj numer konta: ");
        String accnr = c1.nextLine();
                        
        try{
            String fileName = sharedFolder + accnr + "_" + Integer.toString(rand.nextInt(1000)) + "_" + timestamp.getTime() + ".dep";
            PrintWriter pw = new PrintWriter(fileName);
            pw.println(accnr);
            pw.println(deposit);
            pw.close();
        }catch (FileNotFoundException e){
            
        }
    }
    
    public void commandWithdraw(){
        String sharedFolder = "..\\BankApp\\shared\\";
        Scanner c1 = new Scanner(System.in);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String command = "Wyciąg";
                
        System.out.print("Podaj kwote: ");
        String withdraw = c1.nextLine();
        System.out.println("Podaj numer konta: ");
        String accnr = c1.nextLine();
                        
        try{
            String fileName = sharedFolder + accnr + "_" + Integer.toString(rand.nextInt(1000)) + "_" + timestamp.getTime() + ".wit";
            PrintWriter pw = new PrintWriter(fileName);
            pw.println(accnr);
            pw.println(withdraw);
            pw.close();
        }catch (FileNotFoundException e){
            
        }
    }
    
    public void commandTransfer(){
        String sharedFolder = "..\\BankApp\\shared\\";
        boolean ch=false;
        Scanner c1 = new Scanner(System.in);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String command = "Przelew";
        System.out.println("Podaj numer konta, z którego chcesz przelać fundusze: ");
        String accnr1 = c1.nextLine();
        System.out.println("Podaj numer konta, na które chcesz przenieść fundusze: ");   
        String accnr2 = c1.nextLine();
        System.out.println("Podaj kwote: ");
        String transfer = c1.nextLine();
        try{
            String fileName = sharedFolder + accnr1 + "_" + Integer.toString(rand.nextInt(1000)) + "_" + timestamp.getTime() + ".tra";
            PrintWriter pw = new PrintWriter(fileName);
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

    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
        
        ClientApp clientApp = new ClientApp();
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
                        clientApp.commandDeposit();                       
                    } else if (choice.equals("wy")) {
                        System.out.println("operacja wyplaty");
                        clientApp.commandWithdraw();
                    }
                    else if (choice.equals("pl")){
                        clientApp.commandWithdraw();
                    }
                      else if (choice.equals("p")) {
                        clientApp.commandTransfer();
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
                clientApp.newClient();
            } else{
                System.out.println("Nieprawidlowa komenda");
            }
        }
        
        //
    }
}

