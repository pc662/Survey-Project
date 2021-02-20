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

    public boolean isValid(double depositAmount) {
        switch (accountType) {
            case "checking":
                return validateChecking(depositAmount);
            case "saving":
                return validateSavings(depositAmount);
            case "cd":
                return false;
            default:
                return false;
        }
    }

    private boolean validateChecking(double depositAmount) {
        return !(depositAmount > 1000) && !(depositAmount < 0);
    }

    private boolean validateSavings(double depositAmount) {
        return !(depositAmount > 2500) && !(depositAmount < 0);
    }

    private boolean isLessThanZero(int amount) {
        return amount < 0;
    }

}
