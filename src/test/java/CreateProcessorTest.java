import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateProcessorTest {

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
    double cdStartAmount = 100;

    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }


    @Test
    void create_checking_type() {
        process(checkingAccountCommand);
        assertEquals(getAccountType(checkingID), checkingType);
    }

    @Test
    void create_checking_ID() {
        process(checkingAccountCommand);
        assertEquals(getAccountID(checkingID), checkingID);
    }

    @Test
    void create_checking_APR() {
        process(checkingAccountCommand);
        assertEquals(getAccountAPR(checkingID), APR);
    }

    @Test
    void create_checking_balance() {
        process(checkingAccountCommand);
        assertEquals(getAccountBalance(checkingID), 0);
    }

    @Test
    void create_checking_balance_with_decimal() {
        process(checkingAccountCommand);
        assertEquals(getAccountBalance(checkingID), 0.00);
    }

    @Test
    void create_savings_type() {
        process(savingAccountCommand);
        assertEquals(getAccountType(savingID), savingType);
    }

    @Test
    void create_savings_ID() {
        process(savingAccountCommand);
        assertEquals(getAccountID(savingID), savingID);
    }

    @Test
    void create_savings_APR() {
        process(savingAccountCommand);
        assertEquals(getAccountAPR(savingID), APR);
    }

    @Test
    void create_savings_balance() {
        process(savingAccountCommand);
        assertEquals(getAccountBalance(savingID), 0);
    }


    @Test
    void create_cd_type() {
        process(cdAccountCommand);
        assertEquals(getAccountType(cdID), cdType);
    }

    @Test
    void create_cd_ID() {
        process(cdAccountCommand);
        assertEquals(getAccountID(cdID), cdID);
    }

    @Test
    void create_cd_APR() {
        process(cdAccountCommand);
        assertEquals(getAccountAPR(cdID), APR);
    }

    @Test
    void create_cd_balance() {
        process(cdAccountCommand);
        assertEquals(getAccountBalance(cdID), cdStartAmount);
    }


    private String getAccountType(String ID) {
        return getAccount(ID).getAccountType();
    }

    private Account getAccount(String string) {
        return bank.getAccount(string);
    }

    private void process(String checkingAccountCommand) {
        CommandProcessor commandProcessor = new CommandProcessor(bank);
        commandProcessor.process(checkingAccountCommand);
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
