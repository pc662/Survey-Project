import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandProcessorTest {

    String checkingAccountCommand = "create checking 01234567 0.1";
    String savingAccountCommand = "create saving 12345678 0.1";
    String cdAccountCommand = "create cd 00000000 0.1 100";

    String checkingType = "checking";
    String savingType = "saving";
    String cdType = "cd";

    String checkingID = "01234567";
    String savingID = "12345678";
    String cdID = "00000000";

    double APR = 0.1;

    Bank bank = new Bank();


    @Test
    void create_checking_type() {
        process(bank, checkingAccountCommand);
        assertEquals(getAccountType(checkingID), checkingType);
    }

    @Test
    void create_checking_ID() {
        process(bank, checkingAccountCommand);
        assertEquals(getAccountID(checkingID), checkingID);
    }

    @Test
    void create_checking_APR() {
        process(bank, checkingAccountCommand);
        assertEquals(getAccountAPR(checkingID), APR);
    }

    @Test
    void create_checking_balance() {
        process(bank, checkingAccountCommand);
        assertEquals(getAccountBalance(checkingID), 0);
    }

    @Test
    void create_savings() {
        process(bank, savingAccountCommand);
        assertEquals(getAccountType(savingID), savingType);
    }


    @Test
    void create_cd() {
        process(bank, cdAccountCommand);
        assertEquals(getAccountType(cdID), cdType);
    }


    private String getAccountType(String ID) {
        return getAccount(ID).getAccountType();
    }

    private Account getAccount(String string) {
        return bank.getAccount(string);
    }

    private void process(Bank bank, String checkingAccountCommand) {
        CommandProcessor commandProcessor = new CommandProcessor(checkingAccountCommand, bank);
        commandProcessor.process();
    }

    private String getAccountID(String ID) {
        return getAccount(ID).getID();
    }

    private double getAccountAPR(String ID) {
        return getAccount(ID).getAPR();
    }

    private double getAccountBalance(String ID) {
        return getAccount(ID).getBalance();
    }


}
