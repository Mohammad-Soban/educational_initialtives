import java.util.HashMap;
import java.util.Map;

class Bank{
    private static Bank instance;
    private Map<Integer, BankAccount> accounts;

    private Bank(){
        accounts = new HashMap<>();
    }

    public static Bank getInstance(){
        if (instance == null){
            instance = new Bank();
        }
        
        return instance;
    }

    public BankAccount getAccount(int accNumber){
        return accounts.get(accNumber);
    }

    public void AddAcc(BankAccount acc){
        accounts.put(acc.getAccountNumber(), acc);
    }
}

class BankAccount{
    private int AccountNumber;
    private double balance;
    
    public int getAccountNumber() {
        return AccountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void Deposit(double amt){
        balance += amt;
    }

    public void Withdraw(double amt){
        balance -= amt;
    }
}

public class Singleton {
    public static void main(String[] args) {
                
    }
}
