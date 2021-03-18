package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferTest {

    public static final String checkingID = "09283745";
    public static final String savingID = "39405948";

    public static final double APR = 0.6;


    Account checking;
    Account saving;

    Bank bank;

    @BeforeEach
    void setup() {
        bank = new Bank();
        checking = new Checking(checkingID, APR);
        saving = new Saving(savingID, APR);
        checking.deposit(1000);
        saving.deposit(1000);
    }

    @Test
    void transfer_0_from_checking_to_saving() {

    }
}
