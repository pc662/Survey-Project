import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandProcessorTest {
    String checkingType = "checking";
    String checkingAccountCommand = "create checking 01234567 1.0";
    String checkingID = "01234567";
    Bank bank = new Bank();


    @Test
    void create_checking() {
        process(bank, checkingAccountCommand);
        assertEquals(getAccount(checkingID).getAccountType(), checkingType);
    }


    private Account getAccount(String string) {
        return bank.getAccount(string);
    }

    private void process(Bank bank, String checkingAccountCommand) {
        CommandProcessor commandProcessor = new CommandProcessor(checkingAccountCommand, bank);
        commandProcessor.process();
    }


}
