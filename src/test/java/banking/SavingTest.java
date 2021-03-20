package banking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingTest {

    public static final String SAVING = "savings";
    public static final String ZERO_IDENTIFIER = "00000000";
    public static final double APR = 0.01;

    Account saving = new Saving(ZERO_IDENTIFIER, APR);

    @Test
    void saving_account_type() {
        assertEquals(accountType(saving), SAVING);
    }


    @Test
    void saving_account_id() {
        assertEquals(accountID(saving), ZERO_IDENTIFIER);
    }


    @Test
    void saving_account_balance() {
        assertEquals(accountBalance(saving), 0);
    }

    @Test
    void saving_account_APR() {
        assertEquals(APR, getApr());
    }

    private double getApr() {
        return saving.getAPR();
    }


    public String accountType(Account account) {
        return account.getAccountType();
    }

    public String accountID(Account account) {
        return account.getID();
    }

    private double accountBalance(Account account) {
        return account.getBalance();
    }
}
