package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawValidationTest {

    String savingsID = "00000000";
    String checkingID = "12345678";
    String cdID = "01234567";
    double APR = 0.6;

    Bank bank;

    Account saving;
    Account checking;
    Account cd;
    Validation validation;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        saving = new Saving(savingsID, APR);
        checking = new Checking(checkingID, APR);
        cd = new CertificateOfDeposit(cdID, APR, 5000);
        validation = verify();
    }

    @Test
    void withdraw_savings_0() {
        bank.addAccount(saving);
        assertTrue(validation.validate("withdraw 00000000 0"));
    }

    @Test
    void withdraw_savings_1() {
        bank.addAccount(saving);
        assertTrue(validation.validate("withdraw 00000000 1"));
    }

    @Test
    void withdraw_savings_01() {
        bank.addAccount(saving);
        assertTrue(validation.validate("withdraw 00000000 01"));
    }

    @Test
    void withdraw_savings_000001() {
        bank.addAccount(saving);
        assertTrue(validation.validate("withdraw 00000000 000001"));
    }

    @Test
    void withdraw_savings_dollar_and_50_cents() {
        bank.addAccount(saving);
        assertTrue(validation.validate("withdraw 00000000 1.50"));
    }

    @Test
    void withdraw_savings_500() {
        bank.addAccount(saving);
        assertTrue(validation.validate("withdraw 00000000 500"));
    }

    @Test
    void withdraw_savings_999() {
        bank.addAccount(saving);
        assertTrue(validation.validate("withdraw 00000000 999"));
    }

    @Test
    void withdraw_savings_1000() {
        bank.addAccount(saving);
        assertTrue(validation.validate("withdraw 00000000 1000"));
    }

    @Test
    void withdraw_savings_1000_two_times_after_time_passed() {
        bank.addAccount(saving);
        saving.deposit(5000);
        saving.withdraw(1000);
        bank.passTime(1);
        assertTrue(validation.validate("withdraw 00000000 1000"));
    }

    @Test
    void withdraw_savings_1000_three_times_after_time_passed() {
        bank.addAccount(saving);
        saving.deposit(5000);
        saving.withdraw(1000);
        bank.passTime(1);
        saving.withdraw(1000);
        bank.passTime(1);
        assertTrue(validation.validate("withdraw 00000000 1000"));
    }

    @Test
    void withdraw_savings_with_100_decimals() {
        bank.addAccount(saving);
        assertTrue(validation.validate("withdraw 00000000 100.00"));
    }

    @Test
    void withdraw_savings_with_negative_one() {
        bank.addAccount(saving);
        assertFalse(validation.validate("withdraw 00000000 -1"));
    }

    @Test
    void withdraw_savings_with_big_number_100000000() {
        bank.addAccount(saving);
        assertFalse(validation.validate("withdraw 00000000 100000000"));
    }

    @Test
    void withdraw_savings_with_1001() {
        bank.addAccount(saving);
        assertFalse(validation.validate("withdraw 00000000 1001"));
    }

    @Test
    void withdraw_savings_with_1000_and_a_cent() {
        bank.addAccount(saving);
        assertFalse(validation.validate("withdraw 00000000 1000.01"));
    }

    @Test
    void withdraw_savings_back_to_back_with_no_time_pass() {
        bank.addAccount(saving);
        saving.withdraw(1000);
        assertFalse(validation.validate("withdraw 00000000 1000"));
    }

    @Test
    void withdraw_savings_3_times_with_no_time_pass() {
        bank.addAccount(saving);
        saving.withdraw(1000);
        saving.withdraw(1000);
        saving.withdraw(1000);
        assertFalse(validation.validate("withdraw 00000000 1000"));
    }

    @Test
    void withdraw_savings_abc() {
        bank.addAccount(saving);
        assertFalse(validation.validate("withdraw 00000000 abc"));
    }

    @Test
    void withdraw_checking_0() {
        bank.addAccount(checking);
        assertTrue(validation.validate("withdraw 12345678 0"));
    }

    @Test
    void withdraw_checking_1() {
        bank.addAccount(checking);
        assertTrue(validation.validate("withdraw 12345678 1"));
    }

    @Test
    void withdraw_checking_399() {
        bank.addAccount(checking);
        assertTrue(validation.validate("withdraw 12345678 399"));
    }

    @Test
    void withdraw_checking_399_and_99_cents() {
        bank.addAccount(checking);
        assertTrue(validation.validate("withdraw 12345678 399.99"));
    }

    @Test
    void withdraw_checking_400() {
        bank.addAccount(checking);
        assertTrue(validation.validate("withdraw 12345678 400"));
    }

    @Test
    void withdraw_checking_400_with_decimal() {
        bank.addAccount(checking);
        assertTrue(validation.validate("withdraw 12345678 400.00"));
    }

    @Test
    void withdraw_checking_253() {
        bank.addAccount(checking);
        assertTrue(validation.validate("withdraw 12345678 253"));
    }

    @Test
    void withdraw_checking_253_with_decimal() {
        bank.addAccount(checking);
        assertTrue(validation.validate("withdraw 12345678 253.50"));
    }

    @Test
    void withdraw_checking_400_two_times() {
        bank.addAccount(checking);
        checking.withdraw(400);
        assertTrue(validation.validate("withdraw 12345678 0"));
    }

    @Test
    void withdraw_checking_400_two_times_after_passed_time() {
        bank.addAccount(checking);
        checking.deposit(1000);
        checking.withdraw(400);
        bank.passTime(1);
        assertTrue(validation.validate("withdraw 12345678 0"));
    }

    @Test
    void withdraw_checking_with_negative_1() {
        bank.addAccount(checking);
        assertFalse(validation.validate("withdraw 12345678 -1"));
    }

    @Test
    void withdraw_checking_with_big_number() {
        bank.addAccount(checking);
        assertFalse(validation.validate("withdraw 12345678 10293"));
    }

    @Test
    void withdraw_checking_with_401() {
        bank.addAccount(checking);
        assertFalse(validation.validate("withdraw 12345678 401"));
    }

    @Test
    void withdraw_checking_with_400_and_1_cent() {
        bank.addAccount(checking);
        assertFalse(validation.validate("withdraw 12345678 400.01"));
    }

    @Test
    void withdraw_checking_with_abc() {
        bank.addAccount(checking);
        assertFalse(validation.validate("withdraw 12345678 abc"));
    }

    @Test
    void withdraw_checking_with_negative_400() {
        bank.addAccount(checking);
        assertFalse(validation.validate("withdraw 12345678 -400"));
    }

    @Test
    void withdraw_cd_after_12_months_with_a_bit_higher_withdraw_amount() {
        bank.addAccount(cd);
        bank.passTime(12);
        assertTrue(validation.validate("withdraw 01234567 5500"));
    }

    @Test
    void withdraw_cd_after_13_months_with_a_bit_higher_withdraw_amount() {
        bank.addAccount(cd);
        bank.passTime(13);
        assertTrue(validation.validate("withdraw 01234567 5500"));
    }

    @Test
    void withdraw_cd_after_12_months_with_almost_exact_number() {
        bank.addAccount(cd);
        bank.passTime(12);
        assertTrue(validation.validate("withdraw 01234567 5122"));
    }


    @Test
    void withdraw_cd_after_60_months_with_almost_exact_number() {
        bank.addAccount(cd);
        bank.passTime(60);
        assertTrue(validation.validate("withdraw 01234567 5637"));
    }

    @Test
    void withdraw_cd_after_60_months_with_big_number() {
        bank.addAccount(cd);
        bank.passTime(60);
        assertTrue(validation.validate("withdraw 01234567 10000"));
    }

    @Test
    void withdraw_cd_with_no_time_passed() {
        bank.addAccount(cd);
        assertFalse(validation.validate("withdraw 01234567 10000"));
    }

    @Test
    void withdraw_cd_11_months_passed() {
        bank.addAccount(cd);
        bank.passTime(11);
        assertFalse(validation.validate("withdraw 01234567 10000"));
    }

    @Test
    void withdraw_cd_12_months_passed_with_a_bit_less_than_balance() {
        bank.addAccount(cd);
        bank.passTime(12);
        assertFalse(validation.validate("withdraw 01234567 5121"));
    }

    @Test
    void withdraw_cd_60_months_passed_with_a_bit_less_than_balance() {
        bank.addAccount(cd);
        bank.passTime(60);
        assertFalse(validation.validate("withdraw 01234567 5635"));
    }


    private Validation verify() {
        return new Validation(bank);
    }

}
