package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferValidationTest {

    String checkingID1 = "01234567";
    String checkingID2 = "12345678";

    String savingID1 = "00000000";
    String savingID2 = "00000001";

    String cdID1 = "87654321";
    String cdID2 = "76543210";

    double APR = 0.6;

    Validation validation;
    Bank bank;

    Account checking1;
    Account checking2;
    Account saving1;
    Account saving2;
    Account cd1;
    Account cd2;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        validation = verify();
        checking1 = new Checking(checkingID1, APR);
        checking2 = new Checking(checkingID2, APR);
        saving1 = new Saving(savingID1, APR);
        saving2 = new Saving(savingID2, APR);
        cd1 = new CertificateOfDeposit(cdID1, APR, 5000);
        cd2 = new CertificateOfDeposit(cdID2, APR, 5000);

        bank.addAccount(checking1);
        bank.addAccount(checking2);
        bank.addAccount(saving1);
        bank.addAccount(saving2);
        bank.addAccount(cd1);
        bank.addAccount(cd2);
    }

    @Test
    void transfer_0_from_checking_to_checking() {
        assertTrue(validation.validate("transfer " + checkingID1 + " " + checkingID2 + " 0"));
    }

    @Test
    void transfer_1_cent_from_checking_to_checking() {
        assertTrue(validation.validate("transfer " + checkingID1 + " " + checkingID2 + " 0.01"));
    }

    @Test
    void transfer_1_from_checking_to_checking() {
        assertTrue(validation.validate("transfer " + checkingID1 + " " + checkingID2 + " 1"));
    }

    @Test
    void transfer_100_from_checking_to_checking() {
        assertTrue(validation.validate("transfer " + checkingID1 + " " + checkingID2 + " 100"));
    }

    @Test
    void transfer_399_from_checking_to_checking() {
        assertTrue(validation.validate("transfer " + checkingID1 + " " + checkingID2 + " 399"));
    }

    @Test
    void transfer_400_from_checking_to_checking() {
        assertTrue(validation.validate("transfer " + checkingID1 + " " + checkingID2 + " 400"));
    }

    @Test
    void transfer_399_and_99_cents_from_checking_to_checking() {
        assertTrue(validation.validate("transfer " + checkingID1 + " " + checkingID2 + " 399.99"));
    }

    @Test
    void transfer_250_from_checking_to_checking() {
        assertTrue(validation.validate("transfer " + checkingID1 + " " + checkingID2 + " 250"));
    }

    @Test
    void transfer_negative_1000_from_checking_to_checking() {
        assertFalse(validation.validate("transfer" + checkingID1 + " " + checkingID2 + " -1000"));
    }

    @Test
    void transfer_401_from_checking_to_checking() {
        assertFalse(validation.validate("transfer" + checkingID1 + " " + checkingID2 + " 401"));
    }

    @Test
    void transfer_400_and_a_cent_from_checking_to_checking() {
        assertFalse(validation.validate("transfer" + checkingID1 + " " + checkingID2 + " 400.01"));
    }

    @Test
    void transfer_500_from_checking_to_checking() {
        assertFalse(validation.validate("transfer" + checkingID1 + " " + checkingID2 + " 401"));
    }

    @Test
    void transfer_abc_from_checking_to_checking() {
        assertFalse(validation.validate("transfer" + checkingID1 + " " + checkingID2 + " abc"));
    }

    @Test
    void transfer_0_from_saving_to_saving() {
        assertTrue(validation.validate("transfer " + savingID1 + " " + savingID2 + " 0"));
    }

    @Test
    void transfer_1_from_saving_to_saving() {
        assertTrue(validation.validate("transfer " + savingID1 + " " + savingID2 + " 1"));
    }

    @Test
    void transfer_1000_from_saving_to_saving() {
        assertTrue(validation.validate("transfer " + savingID1 + " " + savingID2 + " 1000"));
    }

    @Test
    void transfer_500_from_saving_to_saving() {
        assertTrue(validation.validate("transfer " + savingID1 + " " + savingID2 + " 0"));
    }

    @Test
    void transfer_999_points_99_from_saving_to_saving() {
        assertTrue(validation.validate("transfer " + savingID1 + " " + savingID2 + " 999.99"));
    }

    @Test
    void transfer_999_from_saving_to_saving() {
        assertTrue(validation.validate("transfer " + savingID1 + " " + savingID2 + " 999"));
    }

    @Test
    void transfer_1000_from_saving_to_saving_after_time_passed() {
        bank.transfer(saving1, saving2, 1000);
        saving1.deposit(5000);
        saving2.deposit(6000);
        bank.passTime(1);
        assertTrue(validation.validate("transfer " + savingID1 + " " + savingID2 + " 0"));
    }

    @Test
    void transfer_negative_point_01_from_saving_to_saving() {
        assertFalse(validation.validate("transfer " + savingID1 + " " + savingID2 + " -0.01"));
    }

    @Test
    void transfer_negative_1000_from_saving_to_saving() {
        assertFalse(validation.validate("transfer " + savingID1 + " " + savingID2 + " -1000"));
    }

    @Test
    void transfer_1001_from_saving_to_saving() {
        assertFalse(validation.validate("transfer " + savingID1 + " " + savingID2 + " 1001"));
    }

    @Test
    void transfer_1000_point_01_from_saving_to_saving() {
        assertFalse(validation.validate("transfer " + savingID1 + " " + savingID2 + " 1000.01"));
    }

    @Test
    void transfer_1500_from_saving_to_saving() {
        assertFalse(validation.validate("transfer " + savingID1 + " " + savingID2 + " 1500"));
    }

    @Test
    void transfer_abc_from_saving_to_saving() {
        assertFalse(validation.validate("transfer " + savingID1 + " " + savingID2 + " abc"));
    }

    @Test
    void transfer_1000_from_saving_to_saving_with_no_pass_time() {
        saving1.deposit(5000);
        saving2.deposit(5000);
        bank.transfer(saving1, saving2, 1000);
        assertFalse(validation.validate("transfer " + savingID1 + " " + savingID2 + " 1001"));
    }

    @Test
    void transfer_400_from_checking_to_saving() {
        assertTrue(validation.validate("transfer " + checkingID1 + " " + savingID1 + " 400"));
    }

    @Test
    void transfer_0_from_checking_to_saving() {
        assertTrue(validation.validate("transfer " + checkingID1 + " " + savingID1 + " 0"));
    }

    @Test
    void transfer_1_from_checking_to_saving() {
        assertTrue(validation.validate("transfer " + checkingID1 + " " + savingID1 + " 1"));
    }

    @Test
    void transfer_399_point_99_from_checking_to_saving() {
        assertTrue(validation.validate("transfer " + checkingID1 + " " + savingID1 + " 399.99"));
    }

    @Test
    void transfer_0_from_saving_to_checking() {
        assertTrue(validation.validate("transfer " + savingID1 + " " + checkingID1 + " 0"));
    }

    @Test
    void transfer_1000_from_saving_to_checking() {
        assertTrue(validation.validate("transfer " + savingID1 + " " + checkingID1 + " 1000"));
    }

    @Test
    void transfer_1_from_saving_to_checking() {
        assertTrue(validation.validate("transfer " + savingID1 + " " + checkingID1 + " 1"));
    }

    @Test
    void transfer_300_from_saving_to_checking() {
        assertTrue(validation.validate("transfer " + savingID1 + " " + checkingID1 + " 0"));
    }

    @Test
    void transfer_700_from_saving_to_checking() {
        assertTrue(validation.validate("transfer " + savingID1 + " " + checkingID1 + " 0"));
    }

    @Test
    void transfer_1000_from_saving_to_checking_with_pass_time() {
        saving1.deposit(5000);
        checking1.deposit(5000);
        bank.transfer(saving1, checking1, 1000);
        bank.passTime(2);
        assertTrue(validation.validate("transfer " + savingID1 + " " + checkingID1 + " 0"));
    }

    @Test
    void transfer_401_from_checking_to_saving() {
        assertFalse(validation.validate("transfer " + checkingID1 + " " + savingID1 + " 401"));
    }

    @Test
    void transfer_400_point_01_from_checking_to_saving() {
        assertFalse(validation.validate("transfer " + checkingID1 + " " + savingID1 + " 400.01"));
    }

    @Test
    void transfer_negative_zero_point_01_from_checking_to_saving() {
        assertFalse(validation.validate("transfer " + checkingID1 + " " + savingID1 + " -0.01"));
    }

    @Test
    void transfer_1000_from_checking_to_saving() {
        assertFalse(validation.validate("transfer " + checkingID1 + " " + savingID1 + " 401"));
    }

    @Test
    void transfer_1000_point_01_from_saving_to_checking() {
        assertFalse(validation.validate("transfer " + savingID1 + " " + checkingID1 + " 1000.01"));
    }

    @Test
    void transfer_negative_zero_point_01_from_saving_to_checking() {
        assertFalse(validation.validate("transfer " + savingID1 + " " + checkingID1 + " -0.01"));
    }

    @Test
    void transfer_1000_from_saving_to_checking_with_no_time_pass() {
        saving1.deposit(5000);
        checking1.deposit(5000);
        bank.transfer(saving1, checking1, 1000);
        assertFalse(validation.validate("transfer " + savingID1 + " " + checkingID1 + " 1000"));
    }

    @Test
    void transfer_10000_from_cd_to_cd() {
        assertFalse(validation.validate("transfer " + cdID1 + " " + cdID2 + " 10000"));
    }

    @Test
    void transfer_0_from_cd_to_cd() {
        assertFalse(validation.validate("transfer " + cdID1 + " " + cdID2 + " 0"));
    }

    @Test
    void transfer_10000_from_cd_to_checking() {
        assertFalse(validation.validate("transfer " + cdID1 + " " + checkingID1 + " 10000"));
    }

    @Test
    void transfer_10000_from_cd_to_saving() {
        assertFalse(validation.validate("transfer " + cdID1 + " " + savingID1 + " 10000"));
    }

    @Test
    void transfer_10000_from_checking_to_cd() {
        assertFalse(validation.validate("transfer " + checkingID1 + " " + cdID1 + " 10000"));
    }

    @Test
    void transfer_10000_from_saving_to_cd() {
        assertFalse(validation.validate("transfer " + savingID1 + " " + cdID1 + " 10000"));
    }

    @Test
    void transfer_with_no_other_arguments() {
        assertFalse(validation.validate("transfer "));
    }

    @Test
    void transfer_with_two_arguments() {
        assertFalse(validation.validate("transfer " + checkingID1));
    }

    @Test
    void transfer_with_three_arguments() {
        assertFalse(validation.validate("transfer " + checkingID1 + " " + savingID1));
    }

    @Test
    void transfer_checking_to_itself() {
        assertFalse(validation.validate("transfer " + checkingID1 + " " + checkingID1 + " 1000"));
    }

    @Test
    void transfer_saving_to_itself() {
        assertFalse(validation.validate("transfer " + savingID1 + " " + savingID1 + " 1000"));
    }


    private Validation verify() {
        return new Validation(bank);
    }
}
