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
    void test_correct_command_with_checking() {
        input.add("create checking 01234567 6.0");

        List<String> actual = masterControl.start(input);

        assertEquals(1, actual.size());
        assertEquals("Checking 01234567 0.00 6.00", actual.get(0));
    }

    @Test
    void test_2_correct_commands_with_checking() {
        input.add("create checking 01234567 6.0");
        input.add("deposit 01234567 400");

        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("Checking 01234567 400.00 6.00", actual.get(0));
        assertEquals("deposit 01234567 400", actual.get(1));

    }

    @Test
    void test_3_correct_commands_with_checking() {
        input.add("create checking 01234567 6.0");
        input.add("deposit 01234567 400");
        input.add("deposit 01234567 400");
        input.add("withdraw 01234567 250");

        List<String> actual = masterControl.start(input);

        assertEquals(4, actual.size());
        assertEquals("Checking 01234567 550.00 6.00", actual.get(0));
        assertEquals("deposit 01234567 400", actual.get(1));
        assertEquals("deposit 01234567 400", actual.get(2));
        assertEquals("withdraw 01234567 250", actual.get(3));

    }

    @Test
    void test_correct_command_with_cd() {
        input.add("create cd 01234567 6.0 5000");

        List<String> actual = masterControl.start(input);

        assertEquals(1, actual.size());
        assertEquals("Cd 01234567 5000.00 6.00", actual.get(0));
    }

    @Test
    void test_4_correct_command_with_cd() {
        input.add("create cd 01234567 6.0 5000");
        input.add("Pass 1");
        input.add("Pass 1");
        input.add("Pass 10");
        input.add("withdraw 01234567 7000");

        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("Cd 01234567 0.00 6.00", actual.get(0));
        assertEquals("withdraw 01234567 7000", actual.get(1));
    }


    @Test
    void break_it_down1() {
        input.add("create savings 12345678 0.6");
        input.add("create checking 01234567 0.6");
        input.add("deposit 01234567 400");
        input.add("Pass 2");

        List<String> actual = masterControl.start(input);

        assertEquals(2, actual.size());
        assertEquals("Checking 01234567 400.40 0.60", actual.get(0));
        assertEquals("deposit 01234567 400", actual.get(1));
    }

    @Test
    void break_it_down2() {
        input.add("Create savings 12345678 0.6");
        input.add("Deposit 12345678 700");
        input.add("Deposit 12345678 5000");
        input.add("creAte cHecKing 98765432 0.01");
        input.add("Deposit 98765432 300");
        input.add("Transfer 98765432 12345678 300");

        List<String> actual = masterControl.start(input);

        assertEquals(7, actual.size());
        assertEquals("Savings 12345678 1000.00 0.60", actual.get(0));
        assertEquals("Deposit 12345678 700", actual.get(1));
        assertEquals("Transfer 98765432 12345678 300", actual.get(2));
        assertEquals("Checking 98765432 0.00 0.01", actual.get(3));
        assertEquals("Deposit 98765432 300", actual.get(4));
        assertEquals("Transfer 98765432 12345678 300", actual.get(5));
        assertEquals("Deposit 12345678 5000", actual.get(6));

    }

    @Test
    void break_it_down3_with_pass_time() {
        input.add("Create savings 12345678 0.6");
        input.add("Deposit 12345678 700");
        input.add("Deposit 12345678 5000");
        input.add("creAte cHecKing 98765432 0.01");
        input.add("Deposit 98765432 300");
        input.add("Transfer 98765432 12345678 300");
        input.add("Pass 1");

        List<String> actual = masterControl.start(input);

        assertEquals(4, actual.size());
        assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
        assertEquals("Deposit 12345678 700", actual.get(1));
        assertEquals("Transfer 98765432 12345678 300", actual.get(2));
        assertEquals("Deposit 12345678 5000", actual.get(3));

    }

    @Test
    void sample_make_sure_this_passes_unchanged_or_you_will_fail() {
        input.add("Create savings 12345678 0.6");
        input.add("Deposit 12345678 700");
        input.add("Deposit 12345678 5000");
        input.add("creAte cHecKing 98765432 0.01");
        input.add("Deposit 98765432 300");
        input.add("Transfer 98765432 12345678 300");
        input.add("Pass 1");
        input.add("Create cd 23456789 1.2 2000");
        List<String> actual = masterControl.start(input);

        assertEquals(5, actual.size());
        assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
        assertEquals("Deposit 12345678 700", actual.get(1));
        assertEquals("Transfer 98765432 12345678 300", actual.get(2));
        assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
        assertEquals("Deposit 12345678 5000", actual.get(4));
    }

    @Test
    void sample_make_sure_this_passes_unchanged_or_you_will_fail_2() {
        input.add("Create savings 12345678 0.6");
        input.add("Deposit 12345678 700");
        input.add("Deposit 12345678 5000");
        input.add("creAte cHecKing 98765432 0.01");
        input.add("Deposit 98765432 300");
        input.add("Transfer 98765432 12345678 300");
        input.add("withDraw 98765432 400.01");
        input.add("Pass 1");
        input.add("Create cd 23456789 1.2 2000");
        List<String> actual = masterControl.start(input);

        assertEquals(6, actual.size());
        assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
        assertEquals("Deposit 12345678 700", actual.get(1));
        assertEquals("Transfer 98765432 12345678 300", actual.get(2));
        assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
        assertEquals("Deposit 12345678 5000", actual.get(4));
        assertEquals("withDraw 98765432 400.01", actual.get(5));

    }


}
