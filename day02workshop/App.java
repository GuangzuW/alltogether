package day02workshop;

public class App {

    public static void main(String[] args) {
        BankAccount account01=new BankAccount("Guangzu");
        System.out.println(account01);
        System.out.println("--".repeat(10));
        account01.deposit(1000);
        System.out.println(account01);
        System.out.println("--".repeat(10));
        account01.withdraw(500);
        System.out.println(account01);
        System.out.println("--".repeat(10));
    }

    
    
}
