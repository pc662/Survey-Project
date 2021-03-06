import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterControlTest {

    MasterControl masterControl;
    List<String> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>();
        Bank bank = new Bank();
        masterControl = new MasterControl(bank, new Validation(bank), new CommandProcessor(bank),
                new CommandStorage());

    }

    @Test
    void typo_in_create_command_is_invalid() {
        input.add("creat checking 12345678 1.0");

        List<String> actual = masterControl.start(input);
        assertSingleCommand(actual, "creat checking 12345678 1.0");
    }


    @Test
    void typo_in_deposit_command_is_invalid() {
        input.add("depositt 12345678 100");

        List<String> actual = masterControl.start(input);
        assertSingleCommand(actual, "depositt 12345678 100");
    }

    @Test
    void two_typo_commands_both_invalid() {
        input.add("creat checking 12345678 1.0");
        input.add("depositt 12345678 100");

        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("creat checking 12345678 1.0", actual.get(0));
        assertEquals("depositt 12345678 100", actual.get(1));
    }

    @Test
    void invalid_to_create_accounts_with_same_ID() {
        input.add("create checking 12345678 1.0");
        input.add("create checking 12345678 1.0");

        List<String> actual = masterControl.start(input);

        assertSingleCommand(actual, "create checking 12345678 1.0");
    }

    @Test
    void invalid_to_deposit_into_cd() {
        input.add("create cd 12345678 1.0 1000");
        input.add("deposit 12345678 100");

        List<String> actual = masterControl.start(input);

        assertSingleCommand(actual, "deposit 12345678 100");
    }

    private void assertSingleCommand(List<String> actual, String s) {
        assertEquals(1, actual.size());
        assertEquals(s, actual.get(0));
    }

}