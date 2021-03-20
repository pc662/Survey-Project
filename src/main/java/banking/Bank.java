package banking;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    public Map<String, Object> accounts;
    public Map<String, Object> iteratingAccounts;

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
        new PassTime(accounts).passTheTime(months);
    }

    public void transfer(Account accountFrom, Account accountTo, double amountToTransfer) {
        new Transfer(accountFrom, accountTo).transfer(amountToTransfer);
    }
}
