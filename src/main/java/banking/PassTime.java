package banking;


import java.util.HashMap;
import java.util.Map;

public class PassTime extends Bank {

    double balance;
    double extraBalance;
    double APR;
    double newAPR;

    PassTime(Map<String, Object> passedAccounts) {
        accounts = passedAccounts;
    }

    public void passTheTime(int months) {
        for (int i = 0; i < months; i++) {
            IterateAccounts();
        }
    }

    private void IterateAccounts() {
        iteratingAccounts = new HashMap<>(accounts);
        for (Object account : iteratingAccounts.values()) {
            removeAccountOrAPRCalculations(account);
        }
    }

    private void removeAccountOrAPRCalculations(Object entry) {
        Account account = (Account) entry;
        if (account.getBalance() == 0) {
            removeAccount(account.getID());
        } else if (account.getBalance() < 100) {
            subtractTwentyFive(account);
            determineAccountType(account);
        } else {
            determineAccountType(account);
        }
    }

    private void subtractTwentyFive(Account account) {
        double temporaryBalance;

        temporaryBalance = account.getBalance() - 25;
        if (temporaryBalance <= 0) {
            temporaryBalance = 0;
        }
        account.setBalance(temporaryBalance);
    }

    private void determineAccountType(Account passedAccount) {
        if (!passedAccount.getAccountType().equalsIgnoreCase("cd")) {
            passedAccount.setWithdrawTrue();
        } else {
            checkCDMonths(passedAccount);
        }
        calculateAPR(passedAccount);
        passedAccount.incrementMonthsPassed();
    }

    private void checkCDMonths(Account passedAccount) {
        if (passedAccount.getMonthsPassed() >= 12) {
            passedAccount.setWithdrawTrue();
        }
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
