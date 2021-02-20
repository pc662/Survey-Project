import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateValidationTest {

    //Testing the create portion
    @Test
    void validate_proper_string() {
        Validation validation = new Validation("create checking 01234567 0.01%");
        assertTrue(validation.validate());
    }

    @Test
    void validate_create_command_case() {
        Validation validation = new Validation("Create checking 01234567 0.01%");
        assertTrue(validation.validate());

    }

    @Test
    void validate_incorrect_command_with_letters() {
        Validation validation = new Validation("apple2 Checking 01234567 0.01%");
        assertFalse(validation.validate());
    }

    @Test
    void validate_incorrect_command_with_numbers() {
        Validation validation = new Validation("12304 Checking 01234567 0.01%");
        assertFalse(validation.validate());
    }

    //Testing the account type portion
    @Test
    void validate_account_type_as_number() {
        Validation validation = new Validation("create 123456 01234567 0.01%");
        assertFalse(validation.validate());
    }


}
