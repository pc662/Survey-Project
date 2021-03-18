package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassTimeTest {

    public static final String checkingID = "09283745";
    public static final String savingID = "39405948";
    public static final String cdID = "39203940";

    public static final double APR = 0.1;
    public static final double startAmount = 1000;

    Account checking = new Checking(checkingID, APR);
    Account saving = new Saving(savingID, APR);
    Account cd = new CertificateOfDeposit(cdID, APR, startAmount);

    Bank bank;

    @BeforeEach
    void setup() {
        bank = new Bank();
        bank.addAccount(checking);
        bank.addAccount(saving);
        bank.addAccount(cd);
    }

    @Test
    void pass_one_month_checking_with_zero_dollars() {
        bank.passTime(1);
        assertEquals(bank.getAccount(checkingID).getBalance(), 0);

    }
}
