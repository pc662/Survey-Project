import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositValidationTest {

    DepositValidation validation;
    Bank bank = new Bank();
    Checking checking = new Checking("01234567", 0.01);
    Saving saving = new Saving("01234567", 0.01);


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


    private DepositValidation verify(String s) {
        return new DepositValidation(s, bank);
    }

}
