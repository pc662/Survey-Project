import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandProcessorTest {
    String checkingType = "checking";

    @Test
    void create_checking() {
        Bank bank = new Bank();
        String checkingAccountCommand = "create checking 01234567 1.0";
        CommandProcessor commandProcessor = new CommandProcessor(checkingAccountCommand, bank);
        commandProcessor.process();
        assertEquals(bank.getAccount("0000000").getAccountType(), checkingType);
    }


}
