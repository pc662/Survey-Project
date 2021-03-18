package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassTimeTest {

    public static final String checkingID = "09283745";
    public static final String cdID = "39203940";

    public static final double APR = 0.6;
    public static final double startAmount = 1000;

    Account checking = new Checking(checkingID, APR);
    Account cd = new CertificateOfDeposit(cdID, APR, startAmount);

    Bank bank;

    @BeforeEach
    void setup() {
        bank = new Bank();
        bank.addAccount(checking);
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
    void pass_46_months_checking_with_5000_dollars() {
        bank.getAccount(checkingID).deposit(5000);
        bank.passTime(46);
        assertEquals(5116.303288710226, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_one_month_cd_with_5000_dollars() {
        bank.addAccount(cd);
        bank.getAccount(cdID).deposit(4000);
        bank.passTime(1);
        assertEquals(5010, bank.getAccount(cdID).getBalance());
    }

    @Test
    void pass_twelve_months_cd_with_5000_dollars() {
        bank.addAccount(cd);
        bank.getAccount(cdID).deposit(4000);
        bank.passTime(12);
        assertEquals(5121.328839727015, bank.getAccount(cdID).getBalance());
    }

    @Test
    void pass_sixty_months_cd_with_5000_dollars() {
        bank.addAccount(cd);
        bank.getAccount(cdID).deposit(4000);
        bank.passTime(60);
        assertEquals(5636.808700912838, bank.getAccount(cdID).getBalance());
    }

    @Test
    void pass_46_months_cd_with_5000_dollars() {
        bank.addAccount(cd);
        bank.getAccount(cdID).deposit(4000);
        bank.passTime(46);
        assertEquals(5481.320477152612, bank.getAccount(cdID).getBalance());
    }

    @Test
    void pass_one_month_checking_with_0_dollars() {
        bank.passTime(1);
        assertTrue(bank.getStoredAccounts().isEmpty());
    }

    @Test
    void pass_ten_months_checking_with_0_dollars() {
        bank.passTime(10);
        assertTrue(bank.getStoredAccounts().isEmpty());
    }

    @Test
    void pass_sixty_months_checking_with_0_dollars() {
        bank.passTime(60);
        assertTrue(bank.getStoredAccounts().isEmpty());
    }

    @Test
    void pass_one_month_checking_with_100_dollars() {
        bank.getAccount(checkingID).deposit(100);
        bank.passTime(1);
        assertEquals(100.05, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_one_month_checking_with_50_dollars() {
        bank.getAccount(checkingID).deposit(50);
        bank.passTime(1);
        assertEquals(25.0125, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_two_month_checking_with_50_dollars() {
        bank.getAccount(checkingID).deposit(50);
        bank.passTime(2);
        assertEquals(0.012506249999999289, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_three_months_checking_with_50_dollars() {
        bank.getAccount(checkingID).deposit(50);
        bank.passTime(3);
        assertEquals(0, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_four_months_checking_with_50_dollars() {
        bank.getAccount(checkingID).deposit(50);
        bank.passTime(4);
        assertTrue(bank.getStoredAccounts().isEmpty());

    }

    @Test
    void pass_one_month_checking_with_99_dollars() {
        bank.getAccount(checkingID).deposit(99);
        bank.passTime(1);
        assertEquals(74.037, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_two_month_checking_with_99_dollars() {
        bank.getAccount(checkingID).deposit(99);
        bank.passTime(2);
        assertEquals(49.061518500000005, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_three_months_checking_with_99_dollars() {
        bank.getAccount(checkingID).deposit(99);
        bank.passTime(3);
        assertEquals(24.073549259250004, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void pass_five_months_checking_with_99_dollars() {
        bank.getAccount(checkingID).deposit(99);
        bank.passTime(5);
        assertTrue(bank.getStoredAccounts().isEmpty());
    }


}
