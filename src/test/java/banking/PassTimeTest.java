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
        assertEquals(5005.00125, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_three_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(3);
        assertEquals(5007.503750625, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_four_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(4);
        assertEquals(5010.0075025003125, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_five_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(5);
        assertEquals(5012.512506251563, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_six_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(6);
        assertEquals(5015.018762504688, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_seven_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(7);
        assertEquals(5017.52627188594, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_eight_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(8);
        assertEquals(5020.035035021883, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_nine_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(9);
        assertEquals(5022.545052539394, bank.getAccount(checkingID).getBalance());
    }


    @Test
    void pass_twelve_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(12);
        assertEquals(5030.082637654811, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_sixty_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(60);
        assertEquals(5152.234040743225, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_one_month_cd_with_5000_dollars() {
        bank.getAccount(cdID).deposit(4000);
        bank.passTime(1);
        assertEquals(5010, bank.getAccount(cdID).getBalance());
    }

    @Test
    void pass_twelve_months_cd_with_5000_dollars() {
        bank.getAccount(cdID).deposit(4000);
        bank.passTime(12);
        assertEquals(5121.328839727015, bank.getAccount(cdID).getBalance());
    }

    @Test
    void pass_sixty_months_cd_with_5000_dollars() {
        bank.getAccount(cdID).deposit(4000);
        bank.passTime(60);
        assertEquals(5636.808700912838, bank.getAccount(cdID).getBalance());
    }


}
