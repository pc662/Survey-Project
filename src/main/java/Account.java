public abstract class Account {

    private int balance;
    private String accountType;
    private String ID;
    private double APR;

    public Account(String accountTypeInput, String identifier, double apr) {
        accountType = accountTypeInput;
        ID = identifier;
        APR = apr;
    }


    public String getAccountType() {
        return accountType;
    }

    public String getID() {
        return ID;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int amount) {
        balance = amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
        if (isLessThanZero(balance)) {
            balance = 0;
        }
    }

    public double getAPR() {
        return APR;
    }

    public abstract boolean isTypeValid(String id, String depositAmount);

    private boolean isLessThanZero(int amount) {
        if (amount < 0) {
            return true;
        } else {
            return false;
        }
    }

}
