package banking;

public class Saving extends Account {

    public Saving(String identifier, double apr) {
        super("savings", identifier, apr);
    }

    @Override
    public void withdraw(double amount) {
        if (amount >= getBalance()) {
            setAmountWithdrawn(getBalance());
            setBalance(0);
        } else {
            setAmountWithdrawn(amount);
            setBalance(getBalance() - amount);
        }
        setWithdrawFalse();
    }
}



