package bankapp;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiscService {

    List<Account> accounts = new ArrayList<Account>();
    List<Withdraw> withdraws = new ArrayList<Withdraw>();
    List<Deposit> deposits = new ArrayList<Deposit>();
    List<Transfer> transfers = new ArrayList<Transfer>();
    
    public DiscService(String sourceFilesFolder) throws IOException {
        Path path = Paths.get(sourceFilesFolder);
        List<Path> filesList = getSourceFiles(path);

        for (Path plik : filesList) {
            if(validateFileExtnAcc(plik.getFileName().getFileName().toString())){
            accounts.add(readFromFile(plik));
            }
            
            if(validateFileExtnWit(plik.getFileName().getFileName().toString())){
            withdraws.add(readWithdrawFromFile(plik));
            }
            
            Files.move(plik, Paths.get(plik.getParent()+"\\processed\\"+plik.getFileName()));
            System.out.println("Importuje dane z pliku: " + plik.getFileName());
            try {
                TimeUnit.SECONDS.sleep(0);
            } catch (InterruptedException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }//troche czasu na propagacje - w kolejce wszystko jest estymacja
        }
    }

    private static Pattern fileExtnAcc = Pattern.compile("([^\\s]+(\\.(?i)(acc))$)");     
    public static boolean validateFileExtnAcc(String userName){
         
        Matcher mtch = fileExtnAcc.matcher(userName);
        if(mtch.matches()){
            return true;
        }
        return false;
    }
    private static Pattern fileExtnWit = Pattern.compile("([^\\s]+(\\.(?i)(wit))$)");     
    public static boolean validateFileExtnWit(String userName){
         
        Matcher mtch = fileExtnWit.matcher(userName);
        if(mtch.matches()){
            return true;
        }
        return false;
    }     
    private static Pattern fileExtnDep = Pattern.compile("([^\\s]+(\\.(?i)(dep))$)");     
    public static boolean validateFileExtnDep(String userName){
         
        Matcher mtch = fileExtnDep.matcher(userName);
        if(mtch.matches()){
            return true;
        }
        return false;
    }     
    private static Pattern fileExtnTrans = Pattern.compile("([^\\s]+(\\.(?i)(tra))$)");     
    public static boolean validateFileExtnTrans(String userName){
         
        Matcher mtch = fileExtnTrans.matcher(userName);
        if(mtch.matches()){
            return true;
        }
        return false;
    }     
    
    
    public List<Account> getAccounts() {
        return this.accounts;
    }
    
    public List<Withdraw> getWithdraws(){
        return this.withdraws;
    }
    
    public List<Deposit> getDeposits(){
        return this.deposits;
    }
    
    public List<Transfer> getTransfers(){
        return this.transfers;
    }
    
    private List<Path> getSourceFiles(Path dir) throws IOException {
        List<Path> result = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{acc,dep,wit,tra}")) {
            for (Path entry : stream) {
                result.add(entry);
              
            }
        } catch (DirectoryIteratorException ex) {
            // I/O error encounted during the iteration, the cause is an IOException
            throw ex.getCause();
        }
        return result;
    }

    private Account readFromFile(Path filePath) throws IOException {
        Account tempAcc = new Account();
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            tempAcc.setName(reader.readLine());
            tempAcc.setSurname(reader.readLine());
            tempAcc.setAccnumber(reader.readLine());
            tempAcc.setCnumber(reader.readLine());
            tempAcc.setBalance(Integer.parseInt(reader.readLine()));
            reader.close();
        }
        return tempAcc;
    }
    
    private Withdraw readWithdrawFromFile (Path filePath) throws IOException{
       
        Account tempAccount = new Account();
        int amount;
        String accNr = new String();
        try(BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)){
            accNr = reader.readLine();
            amount = Integer.parseInt(reader.readLine());
            tempAccount.setAccnumber(accNr);
            Withdraw tempWith = new Withdraw(tempAccount, amount);
            return tempWith;
        }        
    }
    
    private Deposit readDepositFromFile (Path filePath) throws IOException{
       
        Account tempAccount = new Account();
        int amount;
        String accNr = new String();
        try(BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)){
            accNr = reader.readLine();
            amount = Integer.parseInt(reader.readLine());
            tempAccount.setAccnumber(accNr);
            Deposit tempDep = new Deposit(tempAccount, amount);
            return tempDep;
        }        
    }
    
    private Transfer readTransferFromFile (Path filePath) throws IOException{
       
        Account tempAccount1 = new Account();
        Account tempAccount2 = new Account();
        int amount;
        String accNr1 = new String();
        String accNr2 = new String();
        try(BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)){
            accNr1 = reader.readLine();
            accNr2 = reader.readLine();
            amount = Integer.parseInt(reader.readLine());
            tempAccount1.setAccnumber(accNr1);
            tempAccount2.setAccnumber(accNr2);
            Transfer tempTrans = new Transfer(tempAccount1,tempAccount2,amount);
            return tempTrans;
        }        
    }
//    private Withdraw readDepositFromFile (Path filePath) throws IOException{
//       
//        Account tempAccount = new Account();
//        int amount;
//        String accNr = new String();
//        try(BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)){
//            accNr = reader.readLine();
//            amount = Integer.parseInt(reader.readLine());
//            tempAccount.setAccnumber(accNr);
//            Withdraw tempWith = new Withdraw(tempAccount, amount);
//            return tempWith;
//        }        
//    }

    
}
   

