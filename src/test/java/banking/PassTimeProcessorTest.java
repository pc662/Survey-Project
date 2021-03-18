package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassTimeProcessorTest {

    public static final double APR = 0.01;
    public static final double startAmount = 1000;
    public static final String checkingID = "09283745";
    public static final String savingID = "39405948";
    public static final String cdID = "39203940";

    Bank bank;
    Account checking;
    Account saving;
    Account cd;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        checking = new Checking(checkingID, APR);
        saving = new Saving(savingID, APR);
        cd = new CertificateOfDeposit(cdID, APR, startAmount);


    }

    @Test
    void process_pass_time_for_checking() {
        bank.addAccount(checking);
        process("pass 1");
        assertTrue(bank.getStoredAccounts().isEmpty());
    }

    @Test
    void process_pass_time_for_saving() {
        bank.addAccount(saving);
        process("pass 1");
        assertTrue(bank.getStoredAccounts().isEmpty());
    }


    private void process(String passTimeCommand) {
        CommandProcessor commandProcessor = new CommandProcessor(bank);
        commandProcessor.process(passTimeCommand);
    }
}
