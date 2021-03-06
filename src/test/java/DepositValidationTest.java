import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositValidationTest {

    public static final String savingsID = "12345678";
    public static final String checkingID = "01234567";
    public static final String cdID = "23456789";

    Validation validation;
    Bank bank = new Bank();

    Checking checking = new Checking(checkingID, 0.01);
    Saving saving = new Saving(savingsID, 0.01);
    CertificateOfDeposit cd = new CertificateOfDeposit("23456789", 0.01, 500);

    @BeforeEach
    void initEach() {
        bank.addAccount(checking);
        bank.addAccount(saving);
        bank.addAccount(cd);
    }

    @Test
    void command_validate_correct_deposit() {
        validation = verify();
        assertTrue(validation.validate("Deposit " + checkingID + " 1000"));
    }

    @Test
    void command_validate_incorrect_deposit_name() {
        validation = verify();
        assertFalse(validation.validate("ddddddd " + checkingID + " 1000"));
    }

    @Test
    void command_validate_lowercase_deposit_name() {
        validation = verify();
        assertTrue(validation.validate("deposit " + checkingID + " 1000"));
    }

    @Test
    void command_validate_case_insensitive_test() {
        validation = verify();
        assertTrue(validation.validate("dEpoSiT " + checkingID + " 1000"));
    }

    @Test
    void command_validate_random_letters() {
        validation = verify();
        assertFalse(validation.validate("daskdksaldklsadklasjdksk " + checkingID + " 1000"));
    }

    @Test
    void command_validate_numbers_as_command() {
        validation = verify();
        assertFalse(validation.validate(checkingID + " 01234567 1000"));
    }

    @Test
    void command_validate_symbols_as_command() {
        validation = verify();
        assertFalse(validation.validate("-+=?! " + checkingID + " 1000"));
    }

    @Test
    void command_validate_nothing_after_deposit() {
        validation = verify();
        assertFalse(validation.validate("deposit"));
    }

    //ID portion
    @Test
    void ID_validate_correct_8_digit() {
        validation = verify();
        assertTrue(validation.validate("deposit " + checkingID + " 1000"));
    }

    @Test
    void ID_validate_as_letters() {
        validation = verify();
        assertFalse(validation.validate("deposit abcdedfs 1000"));
    }

    @Test
    void ID_validate_incorrect_7_digit() {
        validation = verify();
        assertFalse(validation.validate("deposit 0123456 1000"));
    }

    @Test
    void ID_validate_incorrect_9_digit() {
        validation = verify();
        assertFalse(validation.validate("deposit 0" + savingsID + " 1000"));
    }

    @Test
    void ID_validate_account_does_not_exist() {
        validation = verify();
        assertFalse(validation.validate("deposit 01234568 1000"));
    }

    @Test
    void ID_validate_account_does_not_exist_2() {
        validation = verify();
        assertFalse(validation.validate("deposit 10293847 500"));
    }

    @Test
    void ID_validate_nothing_after_account() {
        validation = verify();
        assertFalse(validation.validate("deposit 01234567"));
    }

    @Test
    void amount_validate_as_letters() {
        validation = verify();
        assertFalse(validation.validate("deposit " + checkingID + " abcd"));
    }

    @Test
    void amount_validate_savings_with_negative_one() {
        validation = verify();
        assertFalse(validation.validate("deposit " + savingsID + " -1"));
    }

    @Test
    void amount_validate_savings_with_zero() {
        validation = verify();
        assertTrue(validation.validate("deposit " + savingsID + " 0"));
    }

    @Test
    void amount_validate_savings_with_1() {
        validation = verify();
        assertTrue(validation.validate("deposit " + savingsID + " 1"));
    }

    @Test
    void amount_validate_savings_with_2500() {
        validation = verify();
        assertTrue(validation.validate("deposit " + savingsID + " 2500"));
    }

    @Test
    void amount_validate_savings_with_2501() {
        validation = verify();
        assertFalse(validation.validate("deposit " + savingsID + " 2501"));
    }

    @Test
    void amount_validate_savings_with_2499() {
        validation = verify();
        assertTrue(validation.validate("deposit " + savingsID + " 2499"));
    }

    @Test
    void amount_validate_savings_with_dollar_sign() {
        validation = verify();
        assertFalse(validation.validate("deposit " + savingsID + " $250"));
    }

    @Test
    void amount_validate_savings_with_decimals() {
        validation = verify();
        assertTrue(validation.validate("deposit " + savingsID + " 250.01"));
    }

    @Test
    void amount_validate_savings_with_small_decimal() {
        validation = verify();
        assertTrue(validation.validate("deposit " + savingsID + " 0.01"));
    }

    @Test
    void amount_validate_savings_with_smaller_decimal() {
        validation = verify();
        assertTrue(validation.validate("deposit " + savingsID + " 0.000000000001"));
    }

    @Test
    void amount_validate_checking_with_negative_one() {
        validation = verify();
        assertFalse(validation.validate("deposit " + checkingID + " -1"));
    }

    @Test
    void amount_validate_checking_with_0() {
        validation = verify();
        assertTrue(validation.validate("deposit " + checkingID + " 0"));
    }

    @Test
    void amount_validate_checking_with_one() {
        validation = verify();
        assertTrue(validation.validate("deposit " + checkingID + " 1"));
    }

    @Test
    void amount_validate_checking_with_1000() {
        validation = verify();
        assertTrue(validation.validate("deposit " + checkingID + " 1000"));
    }

    @Test
    void amount_validate_checking_with_999() {
        validation = verify();
        assertTrue(validation.validate("deposit " + checkingID + " 999"));
    }

    @Test
    void amount_validate_checking_with_1001() {
        validation = verify();
        assertFalse(validation.validate("deposit " + checkingID + " 1001"));
    }

    @Test
    void amount_validate_checking_with_dollar_sign() {
        validation = verify();
        assertFalse(validation.validate("deposit " + checkingID + " $900"));
    }

    @Test
    void amount_validate_checking_with_decimals() {
        validation = verify();
        assertTrue(validation.validate("deposit " + checkingID + " 900.0"));
    }

    @Test
    void amount_validate_checking_with_small_decimal() {
        validation = verify();
        assertTrue(validation.validate("deposit " + checkingID + " 0.01"));
    }

    @Test
    void amount_validate_checking_with_smaller_decimal() {
        validation = verify();
        assertTrue(validation.validate("deposit " + checkingID + " 0.00000000001"));
    }

    @Test
    void CD_validate_number_as_incorrect() {
        validation = verify();
        assertFalse(validation.validate("deposit " + cdID + " 1000"));
    }

    @Test
    void CD_validate_nothing_after() {
        validation = verify();
        assertFalse(validation.validate("deposit " + cdID));
    }

    @Test
    void CD_validate_with_0_dollars() {
        validation = verify();
        assertFalse(validation.validate("deposit " + cdID + " 0.00"));
    }

    private Validation verify() {
        return new Validation(bank);
    }


}
