package banking;

import java.util.Map;

public class PassTime {

    Map<String, Object> passedAccounts;
    Account account;
    double balance;
    double extraBalance;
    double APR;
    double newAPR;


    public PassTime(Map<String, Object> accounts) {
        passedAccounts = accounts;
    }

    public void passTime(int months) {
        for (int i = 0; i < months; i++) {
            for (Map.Entry<String, Object> entry : passedAccounts.entrySet()) {
                account = (Account) passedAccounts.get(entry.getKey());
                if (account.getAccountType().equalsIgnoreCase("cd")) {
                    calculateCdAPR(account);
                } else {
                    calculateAPR(account);
                }
            }
        }
    }

    private void calculateCdAPR(Account account) {
        setUpCalculations(account);
        extraBalance = 4 * extraBalance;
        balance = balance + extraBalance;
        account.setBalance(balance);

    }


    private void calculateAPR(Account account) {
        setUpCalculations(account);
        balance = balance + extraBalance;
        account.setBalance(balance);
    }

    private void setUpCalculations(Account account) {
        balance = account.getBalance();
        APR = account.getAPR();

        newAPR = APR / 100;
        newAPR = newAPR / 12;
        extraBalance = newAPR * balance;
    }


}
