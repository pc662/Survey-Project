package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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
        for (Map.Entry<String, Object> entry : passedAccounts.entrySet()) {
            account = (Account) passedAccounts.get(entry.getKey());
            if (account.getAccountType().equalsIgnoreCase("cd")) {
                calculateCdAPR(account, months);
            } else {
                calculateAPR(account, months);
            }
        }
    }

    private void calculateCdAPR(Account account, int months) {

    }

    private void calculateAPR(Account account, int months) {
        for (int i = 0; i < months; i++) {
            balance = account.getBalance();
            APR = account.getAPR();

            newAPR = APR / 100;
            newAPR = newAPR / 12;
            extraBalance = newAPR * balance;
            balance = balance + extraBalance;
            account.setBalance(balance);
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        decimalFormat.format(account.getBalance());
        balance = Double.parseDouble(decimalFormat.format(account.getBalance()));

        account.setBalance(balance);
    }


}
