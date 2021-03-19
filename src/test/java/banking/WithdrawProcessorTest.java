package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawProcessorTest {

    String savingsID = "00000000";
    String checkingID = "12345678";
    String cdID = "01234567";
    double APR = 0.6;

    Bank bank;

    Account saving;
    Account checking;
    Account cd;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        saving = new Saving(savingsID, APR);
        checking = new Checking(checkingID, APR);
        cd = new CertificateOfDeposit(cdID, APR, 5000);

        bank.addAccount(saving);
        bank.addAccount(checking);
        bank.addAccount(cd);

        saving.deposit(5000);
        checking.deposit(5000);
    }

    @Test
    void withdraw_from_checking_1000() {
        process("withdraw 12345678");
        assertEquals(4000, bank.getAccount(checkingID).getBalance());
    }

    private void process(String checkingAccountCommand) {
        CommandProcessor commandProcessor = new CommandProcessor(bank);
        commandProcessor.process(checkingAccountCommand);
    }
}
