package banking;


public class PassTime {

    Account passedAccount;
    double balance;
    double extraBalance;
    double APR;
    double newAPR;


    public PassTime(Account account) {
        passedAccount = account;
    }

    public void passTime() {
        calculateAPR(passedAccount);
    }

    private void calculateAPR(Account account) {
        if (account.getAccountType().equalsIgnoreCase("cd")) {
            setUpCalculations(account);
            extraBalance = 4 * extraBalance;
            balance = balance + extraBalance;
            account.setBalance(balance);
        } else {
            setUpCalculations(account);
            balance = balance + extraBalance;
            account.setBalance(balance);
        }
    }

    private void setUpCalculations(Account account) {
        balance = account.getBalance();
        APR = account.getAPR();

        newAPR = APR / 100;
        newAPR = newAPR / 12;
        extraBalance = newAPR * balance;
    }


}
