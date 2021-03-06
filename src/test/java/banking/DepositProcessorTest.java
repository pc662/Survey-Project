package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositProcessorTest {

    String depositZeroDollarsToChecking = "deposit 01234567 0";
    String depositHundredDollarsToChecking = "deposit 01234567 100";
    String depositFiveHundredDollarsToChecking = "deposit 01234567 500";

    String depositZeroDollarsToSaving = "deposit 12345678 0";
    String depositHundredDollarsToSaving = "deposit 12345678 100";
    String depositFiveHundredDollarsToSaving = "deposit 12345678 500";

    double APR = 0.1;

    Bank bank;

    Checking checking;
    Saving saving;

    String checkingID = "01234567";
    String savingID = "12345678";


    @BeforeEach
    void setUp() {
        bank = new Bank();
        checking = new Checking("01234567", APR);
        saving = new Saving("12345678", APR);
        bank.addAccount(checking);
        bank.addAccount(saving);
    }

    @Test
    void deposit_zero_into_checking() {
        process(depositZeroDollarsToChecking);
        assertEquals(0, getBalance(checkingID));
    }


    @Test
    void deposit_hundred_into_checking() {
        process(depositHundredDollarsToChecking);
        assertEquals(100, getBalance(checkingID));
    }

    @Test
    void deposit_two_hundred_into_checking() {
        process(depositHundredDollarsToChecking);
        process(depositHundredDollarsToChecking);
        assertEquals(200, getBalance(checkingID));
    }

    @Test
    void deposit_one_thousand_into_checking() {
        process(depositFiveHundredDollarsToChecking);
        process(depositFiveHundredDollarsToChecking);
        assertEquals(1000, getBalance(checkingID));
    }

    @Test
    void deposit_zero_into_saving() {
        process(depositZeroDollarsToSaving);
        assertEquals(0, getBalance(savingID));
    }


    @Test
    void deposit_hundred_into_saving() {
        process(depositHundredDollarsToSaving);
        assertEquals(100, getBalance(savingID));
    }

    @Test
    void deposit_two_hundred_into_saving() {
        process(depositHundredDollarsToSaving);
        process(depositHundredDollarsToSaving);
        assertEquals(200, getBalance(savingID));
    }

    @Test
    void deposit_one_thousand_into_saving() {
        process(depositFiveHundredDollarsToSaving);
        process(depositFiveHundredDollarsToSaving);
        assertEquals(1000, getBalance(savingID));
    }


    private double getBalance(String ID) {
        return bank.getAccount(ID).getBalance();
    }

    private void process(String string) {
        CommandProcessor command = new CommandProcessor(bank);
        command.process(string);
    }


}
