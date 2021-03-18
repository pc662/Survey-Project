package banking;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Object> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public Map<String, Object> getStoredAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.put(account.getID(), account);
    }

    public void removeAccount(String accountID) {
        accounts.remove(accountID);
    }

    public Account getAccount(String ID) {
        return (Account) accounts.get(ID);
    }

    public boolean isAccountDepositAmountValid(String id, double depositAmount) {
        Account account = (Account) accounts.get(id);
        return account.isValid(depositAmount);
    }


    public void passTime(int months) {
        for (int i = 0; i < months; i++) {
            IterateAccounts();
        }
    }

    private void IterateAccounts() {
        for (Map.Entry<String, Object> entry : accounts.entrySet()) {
            removeAccountOrAPRCalculations(entry);
        }
    }

    private void removeAccountOrAPRCalculations(Map.Entry<String, Object> entry) {
        double zero = 0;
        Account account = (Account) accounts.get(entry.getKey());
        if (account.getBalance() == zero) {
            removeAccount(account.getID());
        } else {
            new PassTime(account).passTime();
        }
    }
}
