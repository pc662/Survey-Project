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

    public Account getAccount(String ID) {
        return (Account) accounts.get(ID);
    }

    public boolean isAccountAChecking(String id, String depositAmount) {
        Account account = (Account) accounts.get(id);
        boolean isValid = account.isTypeValid("checking", depositAmount);
        return isValid;
    }

    public boolean isAccountASaving(String id, String depositAmount) {

    }

}
