import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandStorageTest {
    String invalidCommand = "create";
    String invalidCommand2 = "deposit";
    String invalidCommand3 = "create 20394 02 dlw";
    String invalidCommandWithCd = "deposit cd 1000";

    String validCommand = "create checking 01234567 0.1";
    String validCommand2 = "create saving 12345678 0.1";
    String validCommand3 = "create cd 00000000 0.1 1000";

    Bank bank;
    Validation validation;
    CommandStorage storage;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        validation = new Validation(bank);
        storage = new CommandStorage();
    }

    @Test
    void store_invalid_command() {
        checkValid(invalidCommand);
        assertEquals(invalidCommand, storage.getInvalidCommands().get(0));
    }

    @Test
    void do_not_store_valid_command() {
        checkValid(validCommand);
        assertTrue(storage.getInvalidCommands().isEmpty());
    }

    private void checkValid(String string) {
        if (!validation.validate(string)) {
            storage.storeInvalidCommand(string);
        }
    }
}
