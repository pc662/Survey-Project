import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void storeInvalidCommand() {
        if (validation.validate(invalidCommand)) {
        } else {
        }

    }
}
