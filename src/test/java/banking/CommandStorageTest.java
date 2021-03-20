package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandStorageTest {
    String invalidCommand = "create";
    String invalidCommand2 = "deposit";
    String invalidCommand3 = "create 20394 02 dlw";
    String invalidCommandWithCd = "deposit cd 1000";

    String validCommand = "create checking 01234567 0.1";
    String validCommand2 = "create savings 12345678 0.1";
    String validCommand3 = "create cd 00000000 0.1 1000";

    Bank bank;
    Validation validation;
    CommandStorage storage;
    CommandProcessor commandProcessor;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        validation = new Validation(bank);
        storage = new CommandStorage();
        commandProcessor = new CommandProcessor(bank);
    }

    @Test
    void check_empty_list() {
        assertTrue(storage.getInvalidCommands().isEmpty());
    }

    @Test
    void store_invalid_command() {
        checkValid(invalidCommand);
        assertEquals(invalidCommand, storage.getInvalidCommands().get(0));
    }

    @Test
    void store_invalid_command_two() {
        checkValid(invalidCommand2);
        assertEquals(invalidCommand2, storage.getInvalidCommands().get(0));
    }


    @Test
    void store_invalid_command_three() {
        checkValid(invalidCommand3);
        assertEquals(invalidCommand3, storage.getInvalidCommands().get(0));
    }

    @Test
    void store_invalid_command_with_cd() {
        checkValid(invalidCommandWithCd);
        assertEquals(invalidCommandWithCd, storage.getInvalidCommands().get(0));
    }

    @Test
    void do_not_store_valid_command() {
        checkValid(validCommand);
        assertTrue(storage.getInvalidCommands().isEmpty());
    }

    @Test
    void do_not_store_valid_command_two() {
        checkValid(validCommand2);
        assertTrue(storage.getInvalidCommands().isEmpty());
    }

    @Test
    void do_not_store_valid_command_three() {
        checkValid(validCommand3);
        assertTrue(storage.getInvalidCommands().isEmpty());
    }

    @Test
    void invalid_command_makes_valid_commands_empty() {
        checkValid("pass 0");
        assertTrue(storage.getValidCommands().isEmpty());
    }

    @Test
    void store_valid_create_command() {
        checkValid("create checking 00000000 0.6");
        assertEquals(0, storage.getValidCommands().get("00000000").size());
    }

    @Test
    void store_valid_create_command_with_correct_commands() {
        checkValid("create checking 12345678 0.6");
        checkValid("deposit 12345678 600");
        assertEquals(1, storage.getValidCommands().get("12345678").size());
    }

    @Test
    void store_valid_and_invalid() {
        checkValid("create checking 12345678 0.6");
        checkValid("deposit 12345678 600");
        checkValid("depskel");

        assertEquals(3, actual().size());
        assertEquals("Checking 12345678 600.00 0.60", actual().get(0));
        assertEquals("deposit 12345678 600", actual().get(1));
        assertEquals("depskel", actual().get(2));

    }


    private List<String> actual() {
        return storage.getOutput(bank);
    }


    private void checkValid(String string) {
        if (!validation.validate(string)) {
            storage.storeInvalidCommand(string);
        } else {
            commandProcessor.process(string);
            storage.checkCommandType(string, bank);
        }
    }
}
