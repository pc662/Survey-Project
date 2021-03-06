package banking;

public abstract class Account {

    protected boolean ableToWithdraw = true;
    int monthsPassed = 1;
    private double balance = 0;
    private double amountWithdrawn;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double amount) {
        balance = amount;
    }

    public boolean isAbleToWithdraw() {
        return ableToWithdraw;
    }

    public void setWithdrawFalse() {
        ableToWithdraw = false;
    }

    public void setWithdrawTrue() {
        ableToWithdraw = true;
    }

    public int getMonthsPassed() {
        return monthsPassed;
    }

    public void incrementMonthsPassed() {
        this.monthsPassed += 1;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount >= balance) {
            amountWithdrawn = balance;
            balance = 0;
        } else {
            amountWithdrawn = amount;
            balance = balance - amount;
        }
    }

    public double getAmountWithdrawn() {
        return amountWithdrawn;
    }

    public void setAmountWithdrawn(double amount) {
        amountWithdrawn = amount;
    }

    public double getAPR() {
        return APR;
    }

    public boolean isValid(double depositAmount) {
        switch (accountType) {
            case "checking":
                return validateChecking(depositAmount);
            case "savings":
                return validateSavings(depositAmount);
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

}
