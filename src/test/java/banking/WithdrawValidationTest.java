package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawValidationTest {

    String savingsID = "00000000";
    double APR = 0.6;

    Bank bank;

    Account savings;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        savings = new Saving(savingsID, APR);
    }

    @Test
    void withdraw_savings_0() {

    }

}
