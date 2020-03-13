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

public class DiscService {

    List<Account> accounts = new ArrayList<Account>();

    public DiscService(String sourceFilesFolder) throws IOException {
        Path path = Paths.get(sourceFilesFolder);
        List<Path> filesList = getSourceFiles(path);

        for (Path plik : filesList) {
            accounts.add(readFromFile(plik));            
            Files.move(plik, Paths.get(plik.getParent()+"\\processed\\"+plik.getFileName()));
            System.out.println("Importuje konto z pliku: " + plik.getFileName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }//troche czasu na propagacje - w kolejce wszystko jest estymacja
        }
    }

    public List<Account> getAccounts() {
        return this.accounts;
    }

    private List<Path> getSourceFiles(Path dir) throws IOException {
        List<Path> result = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{acc}")) {
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
}
