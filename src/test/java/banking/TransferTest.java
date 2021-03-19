package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {

    String checkingID = "01234567";
    String savingID = "01234567";
    double APR = 0.6;

    Bank bank;
    Account checking;
    Account saving;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        checking = new Checking(checkingID, APR);
        saving = new Saving(savingID, APR);
        bank.addAccount(checking);
        bank.addAccount(saving);
    }

    @Test
    void transfer_300_from_checking_to_saving() {
        bank.transfer(bank.getAccount(checkingID), bank.getAccount(savingID), 300);
        assertEquals(0, bank.getAccount(savingID).getBalance());
    }
}
