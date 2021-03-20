import banking.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferProcessorTest {

    String savingID1 = "00000000";
    String savingID2 = "11111111";
    String checkingID1 = "12345678";
    String checkingID2 = "01234567";
    double APR = 0.6;

    Bank bank;

    Account saving1;
    Account saving2;
    Account checking1;
    Account checking2;


    @BeforeEach
    void setUp() {
        bank = new Bank();

        saving1 = new Saving(savingID1, APR);
        saving2 = new Saving(savingID2, APR);
        checking1 = new Checking(checkingID1, APR);
        checking2 = new Checking(checkingID2, APR);

        bank.addAccount(saving1);
        bank.addAccount(saving2);
        bank.addAccount(checking1);
        bank.addAccount(checking2);

        saving1.deposit(5000);
        saving2.deposit(5000);
        checking1.deposit(5000);
        checking2.deposit(5000);
    }

    @Test
    void transfer_1000_from_saving1_to_checking1() {
        process("transfer " + savingID1 + " " + checkingID1 + " 1000");
        assertEquals(4000, saving1.getBalance());
        assertEquals(6000, checking1.getBalance());
    }

    private void process(String checkingAccountCommand) {
        CommandProcessor commandProcessor = new CommandProcessor(bank);
        commandProcessor.process(checkingAccountCommand);
    }
}
