package banking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingTest {

    public static final String CHECKING = "checking";
    public static final String ZERO_IDENTIFIER = "00000000";
    public static final double APR = 0.01;

    Account checking = new Checking(ZERO_IDENTIFIER, APR);

    @Test
    void checking_account_type() {
        assertEquals(accountType(checking), CHECKING);
    }

    @Test
    void checking_account_id() {
        assertEquals(accountID(checking), ZERO_IDENTIFIER);
    }

    @Test
    void checking_account_balance() {
        assertEquals(accountBalance(checking), 0);
    }

    @Test
    void checking_account_APR() {
        assertEquals(APR, getApr());
    }

    private double getApr() {
        return checking.getAPR();
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
