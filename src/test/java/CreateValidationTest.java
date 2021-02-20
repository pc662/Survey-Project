import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateValidationTest {

    Validation validation;
    Bank bank = new Bank();

    //Overall test for a correct string
    @Test
    void validate_proper_string() {
        validation = verify("create checking 01234567 0.01");
        assertTrue(validation.validate());
    }

    //Testing the create portion
    @Test
    void validate_create_command_case() {
        validation = verify("Create checking 01234567 0.01");
        assertTrue(validation.validate());

    }

    @Test
    void validate_create_command_case_insensitive() {
        validation = verify("cReAte checking 01234567 0.01");
        assertTrue(validation.validate());
    }

    @Test
    void validate_incorrect_command_with_letters() {
        validation = verify("apple2 Checking 01234567 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void validate_nothing_else_after_command() {
        validation = verify("create");
        assertFalse(validation.validate());
    }

    @Test
    void validate_more_than_five() {
        validation = verify("create Checking 01234567 0.01 0.01 0.01");
        assertFalse(validation.validate());
    }


    //Testing the account type portion

    @Test
    void validate_incorrect_command_with_numbers() {
        validation = verify("12304 Checking 01234567 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void validate_checking_account_type() {
        validation = verify("create checking 01234567 0.01");
        assertTrue(validation.validate());

    }

    @Test
    void validate_saving_account_type() {
        validation = verify("create saving 01234567 0.01");
        assertTrue(validation.validate());
    }

    @Test
    void validate_cd_account_type() {
        validation = verify("create cd 01234567 0.01");
        assertTrue(validation.validate());
    }

    @Test
    void validate_case_insensitive_for_all_account_types() {
        Validation checkingValidation = verify("create cHecKing 01234567 0.01");
        Validation savingValidation = verify("create sAviNg 01234567 0.01");
        Validation cdValidation = verify("create cD 01234567 0.01");
        assertTrue(checkingValidation.validate());
        assertTrue(savingValidation.validate());
        assertTrue(cdValidation.validate());
    }

    @Test
    void validate_account_type_as_number() {
        validation = verify("create 123456 01234567 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void validate_account_type_as_letters() {
        validation = verify("create Gheckinc 01234567 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void validate_nothing_after_account_type() {
        validation = verify("create checking");
        assertFalse(validation.validate());
    }

    @Test
    void validate_more_than_four_and_not_cd() {
        validation = verify("create checking 01234567 0.01 100");
        assertFalse(validation.validate());
    }

    @Test
    void validate_five_arguments_for_cd() {
        validation = verify("create cD 01234567 0.01 100");
        assertTrue(validation.validate());
    }

    //Test for ID
    @Test
    void validate_correct_id() {
        validation = verify("create checking 01234567 0.01");
        assertTrue(validation.validate());
    }

    @Test
    void validate_incorrect_digits_id() {
        validation = verify("create checking 0123456 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void validate_letters_as_id() {
        validation = verify("create checking abcdesle 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void validate_letters_and_numbers_as_id() {
        validation = verify("create checking abc123d4 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void validate_id_duplication() {
        Checking checking = new Checking("01234567", 0.01);
        bank.addAccount(checking);
        validation = verify("create saving 01234567 0.01");
        assertFalse(validation.validate());

    }

    @Test
    void validate_id_decimals() {
        validation = verify("create saving 012345.6 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void validate_id_with_commas() {
        validation = verify("create saving 012345,6 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void validate_id_with_seven_digits() {
        validation = verify("create saving 0123456 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void validate_id_with_nine_digits() {
        validation = verify("create saving 012345678 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void validate_id_with_one_digit() {
        validation = verify("create saving 1 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void validate_id_with_twenty_digits() {
        validation = verify("create saving 01234567890123456789 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void validate_id_with_nothing_else() {
        validation = verify("create saving 01234567");
        assertFalse(validation.validate());
    }

    //APR
    @Test
    void validate_apr_with_correct_number() {
        validation = verify("create saving 01234567 0.01");
        assertTrue(validation.validate());
    }

    @Test
    void validate_apr_with_greater_than_10() {
        validation = verify("create saving 01234567 11");
        assertFalse(validation.validate());
    }

    @Test
    void validate_apr_with_9() {
        validation = verify("create saving 01234567 9.00");
        assertTrue(validation.validate());
    }

    @Test
    void validate_apr_with_0() {
        validation = verify("create saving 01234567 0.00");
        assertTrue(validation.validate());
    }

    @Test
    void validate_apr_with_negative() {
        validation = verify("create saving 01234567 -0.01");
        assertFalse(validation.validate());
    }

    @Test
    void validate_apr_with_sign_inside() {
        validation = verify("create saving 01234567 0.-01");
        assertFalse(validation.validate());
    }

    @Test
    void validate_not_a_number_apr() {
        validation = verify("create saving 01234567 abc");
        assertFalse(validation.validate());
    }

    @Test
    void validate_apr_with_a_percent() {
        validation = verify("create saving 01234567 0.01%");
        assertFalse(validation.validate());
    }

    @Test
    void validate_apr_with_large_positive() {
        validation = verify("create saving 01234567 10000000000000");
        assertFalse(validation.validate());
    }

    @Test
    void validate_apr_with_large_negative() {
        validation = verify("create saving 01234567 -10000000000000");
        assertFalse(validation.validate());
    }


    private Validation verify(String s) {
        return new Validation(s, bank);
    }


}
