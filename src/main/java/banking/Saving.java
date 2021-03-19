package banking;

public class Saving extends Account {

    public Saving(String identifier, double apr) {
        super("saving", identifier, apr);
    }

    @Override
    public void withdraw(double amount) {
        setBalance(getBalance() - amount);
        if (isLessThanZero(getBalance())) {
            setBalance(0);
        }

    }
}



