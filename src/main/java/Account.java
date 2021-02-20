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
        if (accountType.equals("checking")) {
            return validateChecking(depositAmount);
        } else if (accountType.equals("saving")) {
            return validateSavings(depositAmount);
        } else if (accountType.equals("cd")) {
            return false;
        } else {
            return false;
        }
    }

    private boolean validateChecking(double depositAmount) {
        if (depositAmount > 1000 || depositAmount < 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validateSavings(double depositAmount) {
        if (depositAmount > 2500 || depositAmount < 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isLessThanZero(int amount) {
        if (amount < 0) {
            return true;
        } else {
            return false;
        }
    }

}
