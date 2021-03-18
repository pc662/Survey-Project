package banking;

import java.util.ArrayList;
import java.util.Map;

public class PassTime {

    Map<String, Object> passedAccounts;
    ArrayList<String> accountsToBeRemoved = new ArrayList<>();
    Account account;
    double zero = 0;
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
                if (account.getBalance() == zero) {
                    accountsToBeRemoved.add(account.getID());
                    continue;
                } else if (account.getAccountType().equalsIgnoreCase("cd")) {
                    calculateCdAPR(account);
                } else {
                    calculateAPR(account);
                }
            }
        }
        if (accountsToBeRemoved.size() > 0) {
            removeAccounts();
        }

    }

    private void removeAccounts() {
        for (int i = 0; i <= accountsToBeRemoved.size(); i++) {
            passedAccounts.remove(accountsToBeRemoved.get(i));
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
