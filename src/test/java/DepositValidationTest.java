import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositValidationTest {

    public static final String savingsID = "12345678";
    public static final String checkingID = "01234567";
    DepositValidation validation;
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
        validation = verify("Deposit " + checkingID + " 1000");
        assertTrue(validation.validate());
    }

    @Test
    void command_validate_incorrect_deposit_name() {
        validation = verify("ddddddd " + checkingID + " 1000");
        assertFalse(validation.validate());
    }

    @Test
    void command_validate_lowercase_deposit_name() {
        validation = verify("deposit " + checkingID + " 1000");
        assertTrue(validation.validate());
    }

    @Test
    void command_validate_case_insensitive_test() {
        validation = verify("dEpoSiT " + checkingID + " 1000");
        assertTrue(validation.validate());
    }

    @Test
    void command_validate_random_letters() {
        validation = verify("daskdksaldklsadklasjdksk " + checkingID + " 1000");
        assertFalse(validation.validate());
    }

    @Test
    void command_validate_numbers_as_command() {
        validation = verify(checkingID + " 01234567 1000");
        assertFalse(validation.validate());
    }

    @Test
    void command_validate_symbols_as_command() {
        validation = verify("-+=?! " + checkingID + " 1000");
        assertFalse(validation.validate());
    }

    @Test
    void command_validate_nothing_after_deposit() {
        validation = verify("deposit");
        assertFalse(validation.validate());
    }

    //ID portion
    @Test
    void ID_validate_correct_8_digit() {
        validation = verify("deposit " + checkingID + " 1000");
        assertTrue(validation.validate());
    }

    @Test
    void ID_validate_as_letters() {
        validation = verify("deposit abcdedfs 1000");
        assertFalse(validation.validate());
    }

    @Test
    void ID_validate_incorrect_7_digit() {
        validation = verify("deposit 0123456 1000");
        assertFalse(validation.validate());
    }

    @Test
    void ID_validate_incorrect_9_digit() {
        validation = verify("deposit 0" + savingsID + " 1000");
        assertFalse(validation.validate());
    }

    @Test
    void ID_validate_account_does_not_exist() {
        validation = verify("deposit 01234568 1000");
        assertFalse(validation.validate());
    }

    @Test
    void ID_validate_account_does_not_exist_2() {
        validation = verify("deposit 10293847 500");
        assertFalse(validation.validate());
    }

    @Test
    void amount_validate_as_letters() {
        validation = verify("deposit " + checkingID + " abcd");
        assertFalse(validation.validate());
    }

    @Test
    void amount_validate_savings_with_negative_one() {
        validation = verify("deposit " + savingsID + " -1");
        assertFalse(validation.validate());
    }

    @Test
    void amount_validate_savings_with_zero() {
        validation = verify("deposit " + savingsID + " 0");
        assertTrue(validation.validate());
    }

    @Test
    void amount_validate_savings_with_1() {
        validation = verify("deposit " + savingsID + " 1");
        assertTrue(validation.validate());
    }

    @Test
    void amount_validate_savings_with_2500() {
        validation = verify("deposit " + savingsID + " 2500");
        assertTrue(validation.validate());
    }

    @Test
    void amount_validate_savings_with_2501() {
        validation = verify("deposit " + savingsID + " 2501");
        assertFalse(validation.validate());
    }

    @Test
    void amount_validate_savings_with_2499() {
        validation = verify("deposit " + savingsID + " 2499");
        assertTrue(validation.validate());
    }

    @Test
    void amount_validate_savings_with_dollar_sign() {
        validation = verify("deposit " + savingsID + " $250");
        assertFalse(validation.validate());
    }

    @Test
    void amount_validate_savings_with_decimals() {
        validation = verify("deposit " + savingsID + " 250.01");
        assertTrue(validation.validate());
    }

    @Test
    void amount_validate_checking_with_negative_one() {
        validation = verify("deposit " + checkingID + " -1");
        assertFalse(validation.validate());
    }

    @Test
    void amount_validate_checking_with_0() {
        validation = verify("deposit " + checkingID + " 0");
        assertTrue(validation.validate());
    }

    @Test
    void amount_validate_checking_with_one() {
        validation = verify("deposit " + checkingID + " 1");
        assertTrue(validation.validate());
    }

    @Test
    void amount_validate_checking_with_1000() {
        validation = verify("deposit " + checkingID + " 1000");
        assertTrue(validation.validate());
    }

    @Test
    void amount_validate_checking_with_999() {
        validation = verify("deposit " + checkingID + " 999");
        assertTrue(validation.validate());
    }

    @Test
    void amount_validate_checking_with_1001() {
        validation = verify("deposit " + checkingID + " 1001");
        assertFalse(validation.validate());
    }


    private DepositValidation verify(String s) {
        return new DepositValidation(s, bank);
    }

}
