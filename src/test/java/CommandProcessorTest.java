import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandProcessorTest {

    String checkingAccountCommand = "create checking 01234567 1.0";
    String savingAccountCommand = "create saving 12345678 1.0";
    String cdAccountCommand = "create cd 00000000 1.0 100";

    String checkingType = "checking";
    String savingType = "saving";
    String cdType = "cd";

    String checkingID = "01234567";
    String savingID = "12345678";
    String cdID = "00000000";

    double APR = 0.1;

    Bank bank = new Bank();


    @Test
    void create_checking() {
        process(bank, checkingAccountCommand);
        assertEquals(getAccount(checkingID).getAccountType(), checkingType);
    }

    @Test
    void create_savings() {
        process(bank, savingAccountCommand);
        assertEquals(getAccount(savingID).getAccountType(), savingType);
    }


    private Account getAccount(String string) {
        return bank.getAccount(string);
    }

    private void process(Bank bank, String checkingAccountCommand) {
        CommandProcessor commandProcessor = new CommandProcessor(checkingAccountCommand, bank);
        commandProcessor.process();
    }


}
