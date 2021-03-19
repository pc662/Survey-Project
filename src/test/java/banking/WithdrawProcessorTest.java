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
    void withdraw_from_checking_400() {
        process("withdraw 12345678 400");
        assertEquals(4600, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void withdraw_from_checking_0() {
        process("withdraw 12345678 0");
        assertEquals(5000, bank.getAccount(checkingID).getBalance());
    }

    @Test
    void withdraw_from_saving_1000() {
        process("withdraw 00000000 1000");
        assertEquals(4000, bank.getAccount(savingsID).getBalance());
    }

    @Test
    void withdraw_from_saving_1() {
        process("withdraw 00000000 1");
        assertEquals(4999, bank.getAccount(savingsID).getBalance());
    }

    @Test
    void withdraw_from_cd_after_12_months() {
        process("pass 12");
        process("withdraw 01234567 6000");
        assertEquals(0, bank.getAccount(cdID).getBalance());
    }


    private void process(String checkingAccountCommand) {
        CommandProcessor commandProcessor = new CommandProcessor(bank);
        commandProcessor.process(checkingAccountCommand);
    }
}
