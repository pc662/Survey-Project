import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CertificateOfDepositTest {

    public static final String CD = "CD";
    public static final String ZERO_IDENTIFIER = "00000000";
    public static final double APR = 0.01;
    public static final int START_AMOUNT = 1000;

    Account cd = new CertificateOfDeposit(ZERO_IDENTIFIER, APR, START_AMOUNT);

    @Test
    void CD_account_type() {
        assertEquals(accountType(cd), CD);
    }

    @Test
    void cd_account_id() {
        assertEquals(accountID(cd), ZERO_IDENTIFIER);
    }

    @Test
    void check_start_amount() {
        assertEquals(accountBalance(cd), START_AMOUNT);
    }

    @Test
    void cd_account_APR() {
        assertEquals(APR, getApr());
    }

    private double getApr() {
        return cd.getAPR();
    }


    public String accountType(Account account) {
        return account.getAccountType();
    }

    public String accountID(Account account) {
        return account.getID();
    }

    private int accountBalance(Account account) {
        return account.getBalance();
    }

}
