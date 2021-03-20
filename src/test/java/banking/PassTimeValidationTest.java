package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    void pass_time_2_with_end_space() {
        assertTrue(validation.validate("Pass 2        "));
    }

    @Test
    void pass_time_2_different_cases() {
        assertTrue(validation.validate("pAsS 2"));
    }

    @Test
    void pass_time_2_with_middle_space() {
        assertFalse(validation.validate("Pass        2"));
    }

    @Test
    void pass_time_0() {
        assertFalse(validation.validate("Pass 0"));
    }

    @Test
    void pass_time_1() {
        assertTrue(validation.validate("pass 1"));
    }

    @Test
    void pass_time_1000() {
        assertFalse(validation.validate("pass 1000"));
    }

    @Test
    void pass_time_2000() {
        assertFalse(validation.validate("pass 2000"));
    }

    @Test
    void pass_time_3000() {
        assertFalse(validation.validate("pass 3000"));
    }

    @Test
    void pass_time_negative_one() {
        assertFalse(validation.validate("pass -1"));
    }

    @Test
    void pass_time_with_61() {
        assertFalse(validation.validate("pass 61"));
    }

    @Test
    void pass_time_with_one_plus_one() {
        assertFalse(validation.validate("pass 1+1"));
    }

    @Test
    void pass_time_with_two_plus_two() {
        assertFalse(validation.validate("pass 2+2"));
    }

    @Test
    void pass_time_with_one_times_two() {
        assertFalse(validation.validate("pass 1*2"));
    }

    @Test
    void pass_time_with_ten_divided_by_2() {
        assertFalse(validation.validate("pass 10/2"));
    }

    @Test
    void pass_time_with_three() {
        assertTrue(validation.validate("pass 3"));
    }

    @Test
    void pass_time_with_59() {
        assertTrue(validation.validate("pass 59"));
    }

    @Test
    void pass_time_with_60() {
        assertTrue(validation.validate("pass 60"));
    }

    @Test
    void pass_time_with_43() {
        assertTrue(validation.validate("pass 43"));
    }

    @Test
    void pass_time_with_letters() {
        assertFalse(validation.validate("pass absce"));
    }

    @Test
    void pass_time_with_no_arguments() {
        assertFalse(validation.validate("pass"));
    }

    @Test
    void pass_time_with_decimals() {
        assertFalse(validation.validate("pass 2.0"));
    }

    @Test
    void pass_time_with_43_decimal() {
        assertFalse(validation.validate("pass 43.0"));
    }

    @Test
    void pass_time_with_only_decimal_at_end() {
        assertFalse(validation.validate("pass 2."));
    }

    @Test
    void pass_time_with_only_decimal_at_end_and_spaces_at_end() {
        assertFalse(validation.validate("pass 2.                 "));
    }

    private Validation verify() {
        return new Validation(bank);
    }


}
