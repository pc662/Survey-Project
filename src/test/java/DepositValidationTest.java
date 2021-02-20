import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositValidationTest {

    DepositValidation validation;
    Bank bank = new Bank();
    Checking checking = new Checking("01234567", 0.01);
    Saving saving = new Saving("01234567", 0.01);

    @BeforeEach
    void initEach() {
        bank.addAccount(checking);
    }

    @Test
    void command_validate_correct_deposit() {
        validation = verify("Deposit 01234567 1000");
        assertTrue(validation.validate());
    }

    @Test
    void command_validate_incorrect_deposit_name() {
        validation = verify("ddddddd 01234567 1000");
        assertFalse(validation.validate());
    }

    @Test
    void command_validate_lowercase_deposit_name() {
        validation = verify("deposit 01234567 1000");
        assertTrue(validation.validate());
    }

    @Test
    void command_validate_case_insensitive_test() {
        validation = verify("dEpoSiT 01234567 1000");
        assertTrue(validation.validate());
    }

    @Test
    void command_validate_random_letters() {
        validation = verify("daskdksaldklsadklasjdksk 01234567 1000");
        assertFalse(validation.validate());
    }

    @Test
    void command_validate_numbers_as_command() {
        validation = verify("01234567 01234567 1000");
        assertFalse(validation.validate());
    }

    @Test
    void command_validate_symbols_as_command() {
        validation = verify("-+=?! 01234567 1000");
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
        validation = verify("deposit 01234567 1000");
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
        validation = verify("deposit 012345678 1000");
        assertFalse(validation.validate());
    }

    @Test
    void ID_validate_account_does_not_exist() {
        validation = verify("deposit 01234568 1000");
        assertFalse(validation.validate());
    }


    private DepositValidation verify(String s) {
        return new DepositValidation(s, bank);
    }

}
