package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawValidationTest {

    String savingsID = "00000000";
    double APR = 0.6;

    Bank bank;

    Account savings;
    Validation validation;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        savings = new Saving(savingsID, APR);
        validation = verify();
    }

    @Test
    void withdraw_savings_0() {
        assertTrue(validation.validate("withdraw 00000000 0"));
    }

    private Validation verify() {
        return new Validation(bank);
    }

}
