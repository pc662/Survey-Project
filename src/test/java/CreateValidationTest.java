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
    void command_validate_create_command_case() {
        validation = verify("Create checking 01234567 0.01");
        assertTrue(validation.validate());

    }

    @Test
    void command_validate_create_command_case_insensitive() {
        validation = verify("cReAte checking 01234567 0.01");
        assertTrue(validation.validate());
    }

    @Test
    void command_validate_incorrect_command_with_letters() {
        validation = verify("apple2 Checking 01234567 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void command_validate_nothing_else_after_command() {
        validation = verify("create");
        assertFalse(validation.validate());
    }

    @Test
    void command_validate_more_than_five() {
        validation = verify("create Checking 01234567 0.01 0.01 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void command_validate_incorrect_command_with_numbers() {
        validation = verify("12304 Checking 01234567 0.01");
        assertFalse(validation.validate());
    }

    //Testing the account type portion

    @Test
    void account_type_validate_checking() {
        validation = verify("create checking 01234567 0.01");
        assertTrue(validation.validate());

    }

    @Test
    void account_type_validate_savings() {
        validation = verify("create savings 01234567 0.01");
        assertTrue(validation.validate());
    }

    @Test
    void account_type_validate_cd() {
        validation = verify("create cd 01234567 0.01 1000");
        assertTrue(validation.validate());
    }

    @Test
    void account_type_validate_case_insensitive_for_all_account_types() {
        Validation checkingValidation = verify("create cHecKing 01234567 0.01");
        Validation savingsValidation = verify("create sAviNgs 01234567 0.01");
        Validation cdValidation = verify("create cD 01234567 0.01 1000");
        assertTrue(checkingValidation.validate());
        assertTrue(savingsValidation.validate());
        assertTrue(cdValidation.validate());
    }

    @Test
    void account_type_validate_as_number() {
        validation = verify("create 123456 01234567 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void account_type_validate_as_letters() {
        validation = verify("create Gheckinc 01234567 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void account_type_validate_nothing_after_account_type() {
        validation = verify("create checking");
        assertFalse(validation.validate());
    }

    @Test
    void account_type_validate_more_than_four_and_not_cd() {
        validation = verify("create checking 01234567 0.01 100");
        assertFalse(validation.validate());
    }

    @Test
    void account_type_validate_five_arguments_for_cd() {
        validation = verify("create cD 01234567 0.01 1000");
        assertTrue(validation.validate());
    }

    //Test for ID
    @Test
    void ID_validate_correct_id() {
        validation = verify("create checking 01234567 0.01");
        assertTrue(validation.validate());
    }

    @Test
    void ID_validate_incorrect_digits_id() {
        validation = verify("create checking 0123456 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void ID_validate_letters_as_id() {
        validation = verify("create checking abcdesle 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void ID_validate_letters_and_numbers_as_id() {
        validation = verify("create checking abc123d4 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void ID_validate_duplication() {
        Checking checking = new Checking("01234567", 0.01);
        bank.addAccount(checking);
        validation = verify("create savings 01234567 0.01");
        assertFalse(validation.validate());

    }

    @Test
    void ID_validate_as_decimals() {
        validation = verify("create savings 012345.6 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void ID_validate_with_commas() {
        validation = verify("create savings 012345,6 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void ID_validate_with_seven_digits() {
        validation = verify("create savings 0123456 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void ID_validate_with_nine_digits() {
        validation = verify("create savings 012345678 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void ID_validate_with_one_digit() {
        validation = verify("create savings 1 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void ID_validate_with_twenty_digits() {
        validation = verify("create savings 01234567890123456789 0.01");
        assertFalse(validation.validate());
    }

    @Test
    void ID_validate_with_nothing_else() {
        validation = verify("create savings 01234567");
        assertFalse(validation.validate());
    }

    //APR
    @Test
    void APR_validate_with_correct_number() {
        validation = verify("create savings 01234567 0.01");
        assertTrue(validation.validate());
    }

    @Test
    void APR_validate_with_greater_than_10() {
        validation = verify("create savings 01234567 11");
        assertFalse(validation.validate());
    }

    @Test
    void APR_validate_with_9() {
        validation = verify("create savings 01234567 9.00");
        assertTrue(validation.validate());
    }

    @Test
    void APR_validate_with_0() {
        validation = verify("create savings 01234567 0.00");
        assertTrue(validation.validate());
    }

    @Test
    void APR_validate_with_negative() {
        validation = verify("create savings 01234567 -0.01");
        assertFalse(validation.validate());
    }

    @Test
    void APR_validate_with_sign_inside() {
        validation = verify("create savings 01234567 0.-01");
        assertFalse(validation.validate());
    }

    @Test
    void APR_validate_not_a_number() {
        validation = verify("create savings 01234567 abc");
        assertFalse(validation.validate());
    }

    @Test
    void APR_validate_with_a_percent() {
        validation = verify("create savings 01234567 0.01%");
        assertFalse(validation.validate());
    }

    @Test
    void APR_validate_with_large_positive() {
        validation = verify("create savings 01234567 10000000000000");
        assertFalse(validation.validate());
    }

    @Test
    void APR_validate_with_large_negative() {
        validation = verify("create savings 01234567 -10000000000000");
        assertFalse(validation.validate());
    }

    @Test
    void APR_validate_with_edge_9() {
        validation = verify("create savings 01234567 9.999");
        assertTrue(validation.validate());
    }

    //cd testing
    @Test
    void CD_validate_amount_with_1000() {
        validation = verify("create cd 01234567 0.01 1000");
        assertTrue(validation.validate());
    }

    @Test
    void CD_validate_amount_with_999() {
        validation = verify("create cd 01234567 0.01 999");
        assertFalse(validation.validate());
    }

    @Test
    void CD_validate_amount_with_1001() {
        validation = verify("create cd 01234567 0.01 1001");
        assertTrue(validation.validate());
    }

    @Test
    void CD_validate_amount_with_0() {
        validation = verify("create cd 01234567 0.01 0)");
        assertFalse(validation.validate());
    }

    @Test
    void CD_validate_amount_with_10000() {
        validation = verify("create cd 01234567 0.01 10000");
        assertTrue(validation.validate());
    }

    @Test
    void CD_validate_amount_with_10001() {
        validation = verify("create cd 01234567 0.01 10001");
        assertFalse(validation.validate());
    }

    @Test
    void CD_validate_amount_with_9999() {
        validation = verify("create cd 01234567 0.01 9999");
        assertTrue(validation.validate());
    }

    @Test
    void CD_validate_amount_with_letters() {
        validation = verify("create cd 01234567 0.01 abcd");
        assertFalse(validation.validate());
    }

    @Test
    void CD_validate_amount_with_symbols() {
        validation = verify("create cd 01234567 0.01 %@#$!");
        assertFalse(validation.validate());
    }

    @Test
    void CD_validate_cd_amount_with_right_number() {
        validation = verify("create cd 01234567 0.01 5693");
        assertTrue(validation.validate());
    }

    //abnormal cases

    @Test
    void abnormal_validate_empty_string() {
        validation = verify("");
        assertFalse(validation.validate());
    }

    @Test
    void abnormal_validate_single_number() {
        validation = verify("99999999999");
        assertFalse(validation.validate());
    }

    @Test
    void abnormal_validate_account_types_with_strings_containing_them() {
        validation = verify("create cd01923 01234567 0.01 1000");
        assertFalse(validation.validate());
    }

    @Test
    void abnormal_validate_amalgamation_of_everything() {
        validation = verify("createcd 01234 dkdks 222 0.001 100000");
        assertFalse(validation.validate());
    }

    @Test
    void abnormal_validate_only_symbols() {
        validation = verify("+-@/?!");
        assertFalse(validation.validate());
    }

    @Test
    void abnormal_multiple_white_space() {
        validation = verify("create  checking 01234567 0.01");
        assertFalse(validation.validate());
    }

    private Validation verify(String s) {
        return new Validation(s, bank);
    }


}
