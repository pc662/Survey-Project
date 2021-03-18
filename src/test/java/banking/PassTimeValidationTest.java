package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassTimeValidationTest {

    Validation validation;
    Bank bank;

    @BeforeEach
    void setup() {
        bank = new Bank();
        validation = verify();
    }

    @Test
    void pass_time_2() {
        assertTrue(validation.validate("Pass 2"));
    }

    private Validation verify() {
        return new Validation(bank);
    }


}
