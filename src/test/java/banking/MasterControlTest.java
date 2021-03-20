package banking;

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
    void two_typo_commands_both_invalid() {
        input.add("creat checking 12345678 1.0");
        input.add("depositt 12345678 100");

        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("creat checking 12345678 1.0", actual.get(0));
        assertEquals("depositt 12345678 100", actual.get(1));
    }

    @Test
    void test_correct_command() {
        input.add("create checking 01234567 6.0");

        List<String> actual = masterControl.start(input);

        assertEquals(1, actual.size());
        assertEquals("Checking 01234567 0.00 6.00", actual.get(0));
    }

    @Test
    void test_2_correct_commands() {
        input.add("create checking 01234567 6.0");
        input.add("deposit 01234567 400");

        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("Checking 01234567 400.00 6.00", actual.get(0));
        assertEquals("deposit 01234567 400", actual.get(1));

    }


}
