package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawValidationTest {

    String savingsID = "00000000";
    String checkingID = "12345678";
    double APR = 0.6;

    Bank bank;

    Account saving;
    Account checking;
    Validation validation;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        saving = new Saving(savingsID, APR);
        checking = new Checking(checkingID, APR);
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


    private Validation verify() {
        return new Validation(bank);
    }

}
