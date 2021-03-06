package banking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateValidationTest {

    Validation validation;
    Bank bank = new Bank();

    //Overall test for a correct string
    @Test
    void validate_proper_string() {
        validation = verify();
        assertTrue(validation.validate("create checking 01234567 0.01"));
    }

    //Testing the create portion
    @Test
    void command_validate_create_command_case() {
        validation = verify();
        assertTrue(validation.validate("Create checking 01234567 0.01"));

    }

    @Test
    void command_validate_create_command_case_insensitive() {
        validation = verify();
        assertTrue(validation.validate("cReAte checking 01234567 0.01"));
    }

    @Test
    void command_validate_incorrect_command_with_letters() {
        validation = verify();
        assertFalse(validation.validate("apple2 banking.Checking 01234567 0.01"));
    }

    @Test
    void command_validate_nothing_else_after_command() {
        validation = verify();
        assertFalse(validation.validate("create"));
    }

    @Test
    void command_validate_more_than_five() {
        validation = verify();
        assertFalse(validation.validate("create banking.Checking 01234567 0.01 0.01 0.01"));
    }

    @Test
    void command_validate_incorrect_command_with_numbers() {
        validation = verify();
        assertFalse(validation.validate("12304 banking.Checking 01234567 0.01"));
    }

    //Testing the account type portion

    @Test
    void account_type_validate_checking() {
        validation = verify();
        assertTrue(validation.validate("create checking 01234567 0.01"));

    }

    @Test
    void account_type_validate_savings() {
        validation = verify();
        assertTrue(validation.validate("create savings 01234567 0.01"));
    }

    @Test
    void account_type_validate_cd() {
        validation = verify();
        assertTrue(validation.validate("create cd 01234567 0.01 1000"));
    }

    @Test
    void account_type_validate_case_insensitive_for_all_account_types() {
        Validation checkingValidation = verify();
        Validation savingsValidation = verify();
        Validation cdValidation = verify();
        assertTrue(checkingValidation.validate("create cHecKing 01234567 0.01"));
        assertTrue(savingsValidation.validate("create sAviNgs 01234567 0.01"));
        assertTrue(cdValidation.validate("create cD 01234567 0.01 1000"));
    }

    @Test
    void account_type_validate_as_number() {
        validation = verify();
        assertFalse(validation.validate("create 123456 01234567 0.01"));
    }

    @Test
    void account_type_validate_as_letters() {
        validation = verify();
        assertFalse(validation.validate("create Gheckinc 01234567 0.01"));
    }

    @Test
    void account_type_validate_as_negative_letters() {
        validation = verify();
        assertFalse(validation.validate("create -checking 01234567 0.01"));
    }

    @Test
    void account_type_validate_nothing_after_account_type() {
        validation = verify();
        assertFalse(validation.validate("create checking"));
    }

    @Test
    void account_type_validate_more_than_four_and_not_cd() {
        validation = verify();
        assertFalse(validation.validate("create checking 01234567 0.01 100"));
    }

    @Test
    void account_type_validate_five_arguments_for_cd() {
        validation = verify();
        assertTrue(validation.validate("create cD 01234567 0.01 1000"));
    }

    //Test for ID
    @Test
    void ID_validate_correct_id() {
        validation = verify();
        assertTrue(validation.validate("create checking 01234567 0.01"));
    }

    @Test
    void ID_validate_incorrect_digits_id() {
        validation = verify();
        assertFalse(validation.validate("create checking 0123456 0.01"));
    }

    @Test
    void ID_validate_letters_as_id() {
        validation = verify();
        assertFalse(validation.validate("create checking abcdesle 0.01"));
    }

    @Test
    void ID_validate_letters_and_numbers_as_id() {
        validation = verify();
        assertFalse(validation.validate("create checking abc123d4 0.01"));
    }

    @Test
    void ID_validate_duplication() {
        Checking checking = new Checking("01234567", 0.01);
        bank.addAccount(checking);
        validation = verify();
        assertFalse(validation.validate("create savings 01234567 0.01"));

    }

    @Test
    void ID_validate_as_decimals() {
        validation = verify();
        assertFalse(validation.validate("create savings 012345.6 0.01"));
    }

    @Test
    void ID_validate_with_commas() {
        validation = verify();
        assertFalse(validation.validate("create savings 012345,6 0.01"));
    }

    @Test
    void ID_validate_with_seven_digits() {
        validation = verify();
        assertFalse(validation.validate("create savings 0123456 0.01"));
    }

    @Test
    void ID_validate_with_nine_digits() {
        validation = verify();
        assertFalse(validation.validate("create savings 012345678 0.01"));
    }

    @Test
    void ID_validate_with_one_digit() {
        validation = verify();
        assertFalse(validation.validate("create savings 1 0.01"));
    }

    @Test
    void ID_validate_with_twenty_digits() {
        validation = verify();
        assertFalse(validation.validate("create savings 01234567890123456789 0.01"));
    }

    @Test
    void ID_validate_with_nothing_else() {
        validation = verify();
        assertFalse(validation.validate("create savings 01234567"));
    }

    //APR
    @Test
    void APR_validate_with_correct_number() {
        validation = verify();
        assertTrue(validation.validate("create savings 01234567 0.01"));
    }

    @Test
    void APR_validate_with_greater_than_10() {
        validation = verify();
        assertFalse(validation.validate("create savings 01234567 11"));
    }

    @Test
    void APR_validate_with_9() {
        validation = verify();
        assertTrue(validation.validate("create savings 01234567 9.00"));
    }

    @Test
    void APR_validate_with_0() {
        validation = verify();
        assertTrue(validation.validate("create savings 01234567 0.00"));
    }

    @Test
    void APR_validate_with_negative() {
        validation = verify();
        assertFalse(validation.validate("create savings 01234567 -0.01"));
    }

    @Test
    void APR_validate_with_sign_inside() {
        validation = verify();
        assertFalse(validation.validate("create savings 01234567 0.-01"));
    }

    @Test
    void APR_validate_not_a_number() {
        validation = verify();
        assertFalse(validation.validate("create savings 01234567 abc"));
    }

    @Test
    void APR_validate_with_a_percent() {
        validation = verify();
        assertFalse(validation.validate("create savings 01234567 0.01%"));
    }

    @Test
    void APR_validate_with_large_positive() {
        validation = verify();
        assertFalse(validation.validate("create savings 01234567 10000000000000"));
    }

    @Test
    void APR_validate_with_large_negative() {
        validation = verify();
        assertFalse(validation.validate("create savings 01234567 -10000000000000"));
    }

    @Test
    void APR_validate_with_edge_9() {
        validation = verify();
        assertTrue(validation.validate("create savings 01234567 9.999"));
    }

    //cd testing
    @Test
    void CD_validate_amount_with_1000() {
        validation = verify();
        assertTrue(validation.validate("create cd 01234567 0.01 1000"));
    }

    @Test
    void CD_validate_amount_with_999() {
        validation = verify();
        assertFalse(validation.validate("create cd 01234567 0.01 999"));
    }

    @Test
    void CD_validate_amount_with_1001() {
        validation = verify();
        assertTrue(validation.validate("create cd 01234567 0.01 1001"));
    }

    @Test
    void CD_validate_amount_with_0() {
        validation = verify();
        assertFalse(validation.validate("create cd 01234567 0.01 0"));
    }

    @Test
    void CD_validate_amount_with_10000() {
        validation = verify();
        assertTrue(validation.validate("create cd 01234567 0.01 10000"));
    }

    @Test
    void CD_validate_amount_with_10001() {
        validation = verify();
        assertFalse(validation.validate("create cd 01234567 0.01 10001"));
    }

    @Test
    void CD_validate_amount_with_9999() {
        validation = verify();
        assertTrue(validation.validate("create cd 01234567 0.01 9999"));
    }

    @Test
    void CD_validate_amount_with_letters() {
        validation = verify();
        assertFalse(validation.validate("create cd 01234567 0.01 abcd"));
    }

    @Test
    void CD_validate_amount_with_symbols() {
        validation = verify();
        assertFalse(validation.validate("create cd 01234567 0.01 %@#$!"));
    }

    @Test
    void CD_validate_cd_amount_with_right_number() {
        validation = verify();
        assertTrue(validation.validate("create cd 01234567 0.01 5693"));
    }

    //abnormal cases

    @Test
    void abnormal_validate_empty_string() {
        validation = verify();
        assertFalse(validation.validate(""));
    }

    @Test
    void abnormal_validate_single_number() {
        validation = verify();
        assertFalse(validation.validate("99999999999"));
    }

    @Test
    void abnormal_validate_account_types_with_strings_containing_them() {
        validation = verify();
        assertFalse(validation.validate("create cd01923 01234567 0.01 1000"));
    }

    @Test
    void abnormal_validate_amalgamation_of_everything() {
        validation = verify();
        assertFalse(validation.validate("createcd 01234 dkdks 222 0.001 100000"));
    }

    @Test
    void abnormal_validate_only_symbols() {
        validation = verify();
        assertFalse(validation.validate("+-@/?!"));
    }

    @Test
    void abnormal_multiple_white_space() {
        validation = verify();
        assertFalse(validation.validate("create  checking 01234567 0.01"));
    }

    private Validation verify() {
        return new Validation(bank);
    }


}
