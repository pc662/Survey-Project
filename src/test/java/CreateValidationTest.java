import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateValidationTest {

    Validation validation;
    Bank bank = new Bank();

    //Overall test for a correct string
    @Test
    void validate_proper_string() {
        validation = getValidation("create checking 01234567 0.01%");
        assertTrue(validation.validate());
    }

    //Testing the create portion
    @Test
    void validate_create_command_case() {
        validation = getValidation("Create checking 01234567 0.01%");
        assertTrue(validation.validate());

    }

    @Test
    void validate_create_command_case_insensitive() {
        validation = getValidation("cReAte checking 01234567 0.01%");
        assertTrue(validation.validate());
    }

    @Test
    void validate_incorrect_command_with_letters() {
        validation = getValidation("apple2 Checking 01234567 0.01%");
        assertFalse(validation.validate());
    }

    //Testing the account type portion

    @Test
    void validate_incorrect_command_with_numbers() {
        validation = getValidation("12304 Checking 01234567 0.01%");
        assertFalse(validation.validate());
    }

    @Test
    void validate_checking_account_type() {
        validation = getValidation("create checking 01234567 0.01%");
        assertTrue(validation.validate());

    }

    @Test
    void validate_saving_account_type() {
        validation = getValidation("create saving 01234567 0.01%");
        assertTrue(validation.validate());
    }

    @Test
    void validate_cd_account_type() {
        validation = getValidation("create cd 01234567 0.01%");
        assertTrue(validation.validate());
    }

    @Test
    void validate_case_insensitive_for_all_account_types() {
        Validation checkingValidation = getValidation("create cHecKing 01234567 0.01%");
        Validation savingValidation = getValidation("create sAviNg 01234567 0.01%");
        Validation cdValidation = getValidation("create cD 01234567 0.01%");
        assertTrue(checkingValidation.validate());
        assertTrue(savingValidation.validate());
        assertTrue(cdValidation.validate());
    }

    @Test
    void validate_account_type_as_number() {
        validation = getValidation("create 123456 01234567 0.01%");
        assertFalse(validation.validate());
    }

    @Test
    void validate_account_type_as_letters() {
        validation = getValidation("create Gheckinc 01234567 0.01%");
        assertFalse(validation.validate());
    }

    //Test for ID
    @Test
    void validate_correct_id() {
        validation = getValidation("create checking 01234567 0.01%");
        assertTrue(validation.validate());
    }

    @Test
    void validate_incorrect_digits_id() {
        validation = getValidation("create checking 0123456 0.01%");
        assertFalse(validation.validate());
    }

    @Test
    void validate_letters_as_id() {
        validation = getValidation("create checking abcdesle 0.01%");
        assertFalse(validation.validate());
    }

    @Test
    void validate_letters_and_numbers_as_id() {
        validation = getValidation("create checking abc123d4 0.01%");
        assertFalse(validation.validate());
    }

    @Test
    void validate_id_duplication() {
        Checking checking = new Checking("01234567", 0.01);
        bank.addAccount(checking);
        validation = getValidation("create saving 01234567 0.01%");
        assertFalse(validation.validate());

    }

    @Test
    void validate_id_decimals() {
        validation = getValidation("create saving 012345.6 0.01%");
        assertFalse(validation.validate());
    }

    @Test
    void validate_id_with_commas() {
        validation = getValidation("create saving 012345,6 0.01%");
        assertFalse(validation.validate());
    }

    @Test
    void validate_id_with_seven_digits() {
        validation = getValidation("create saving 0123456 0.01%");
        assertFalse(validation.validate());
    }

    @Test
    void validate_id_with_nine_digits() {
        validation = getValidation("create saving 012345678 0.01%");
        assertFalse(validation.validate());
    }

    @Test
    void validate_id_with_one_digit() {
        validation = getValidation("create saving 1 0.01%");
        assertFalse(validation.validate());
    }

    @Test
    void validate_id_with_twenty_digits() {
        validation = getValidation("create saving 01234567890123456789 0.01%");
        assertFalse(validation.validate());
    }


    private Validation getValidation(String s) {
        return new Validation(s, bank);
    }


}
