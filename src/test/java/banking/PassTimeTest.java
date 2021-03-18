package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassTimeTest {

    public static final String checkingID = "09283745";
    public static final String savingID = "39405948";
    public static final String cdID = "39203940";

    public static final double APR = 0.6;
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
        assertEquals(0, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_one_month_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(1);
        assertEquals(5002.50, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_two_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(2);
        assertEquals(5005, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_three_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(3);
        assertEquals(5007.50, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_four_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(4);
        assertEquals(5010, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_five_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(5);
        assertEquals(5012.5, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_six_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(6);
        assertEquals(5015, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_seven_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(7);
        assertEquals(5017.50, bank.getAccount(checkingID).getBalance());
    }


    @Test
    void pass_twelve_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(12);
        assertEquals(5030, bank.getAccount(checkingID).getBalance());
    }
}
