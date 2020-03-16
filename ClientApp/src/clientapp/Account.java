package clientapp;
import java.lang.Object;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;


public class Account {

    private String name, surname, accnumber, cnumber;
    private int balance;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setAccnumber(String accnumber) {
        this.accnumber = accnumber;
    }

    public String getAccnumber() {
        return accnumber;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber;
    }

    public String getCnumber() {
        return cnumber;
    }

    public Account() {

    }

    public Account(String name, String surname, int balance, String accnumber) {
        setName(name);
        setSurname(surname);
        setBalance(balance);
        setAccnumber(accnumber);
    }

    public Account(String name, String surname, int balance, String accnumber, String cnumber) {
        setName(name);
        setSurname(surname);
        setBalance(balance);
        setAccnumber(accnumber);
        setCnumber(cnumber);
    }
}

