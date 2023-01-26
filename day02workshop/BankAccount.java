package day02workshop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BankAccount {

    private String accountName;
    private String accountNumber;
    private float accountBalance;
    private List<String> tansactions;
    private boolean isClosed;
    private String accountCreateDate;
    private String accountCloseDate;

    public BankAccount(String accountName) {
        this.accountName = accountName;
        this.accountNumber=autoGenerateAccountNumber();
        this.accountBalance = 0f;
        this.tansactions=new ArrayList<String>();
        this.accountCreateDate=(new Date()).toString();
    }

    public BankAccount(String accountName, float accountBalance) {
        this.accountName = accountName;
        this.accountNumber=autoGenerateAccountNumber();
        this.accountBalance = accountBalance;
        this.tansactions=new ArrayList<String>();
        this.accountCreateDate=(new Date()).toString();

    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<String> getTansactions() {
        return tansactions;
    }

    public void setTansactions(List<String> tansactions) {
        this.tansactions = tansactions;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean isClosed) {
        Date date=new Date();
        if(isClosed){
            this.accountCloseDate=date.toString();
        }
        this.isClosed = isClosed;
    }

    public String getAccountCreateDate() {
        return accountCreateDate;
    }

    public String getAccountCloseDate() {
        return accountCloseDate;
    }

    public void deposit(float amount){

        if (amount<=0 || isClosed){
            throw new IllegalArgumentException("Invalid operation, deposit amount must be positive and cannot deposit to closed account");
        }else {
            accountBalance+=amount;
            LocalDateTime currentDateTime = LocalDateTime.now();
            String date= currentDateTime.toString();
            tansactions.add(String.format("deposit $%f at <%s>", amount,date));
        }

    }


    public void withdraw (float amount){

        if (amount<=0 && isClosed && accountBalance<amount){
            throw new IllegalArgumentException("Invalid operation, deposit amount must be positive and cannot deposit to closed account");
        }else {
            accountBalance-=amount;
            LocalDateTime currentDateTime = LocalDateTime.now();
            String date= currentDateTime.toString();
            tansactions.add(String.format("withdraw $%f at <%s>", amount,date));
        }

    }

    private String autoGenerateAccountNumber(){
        Random random=new Random();
        return String.format("%04d-%04d-%04d",random.nextInt(10000),random.nextInt(10000),random.nextInt(10000));
    }

    

    @Override
    public String toString() {
       return ("AccountName: "+getAccountName()+"\nAccountNumber: "+getAccountNumber()+"\nAccountBanlacne: "+getAccountBalance()+
                "\nTansaction History: "+getTansactions()+"\nAccountCreateDate: "+getAccountCreateDate()+"\nAccountCloseDate: "+
                getAccountCloseDate()+"\nisClosed: "+isClosed());
    }
    
}
