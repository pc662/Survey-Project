package banking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    public static final double APR = 0.01;
    public static final double startAmount = 1000;
    public static final String checkingID = "09283745";
    public static final String savingID = "39405948";
    public static final String cdID = "39203940";

    Bank bank = new Bank();
    Account checking = new Checking(checkingID, APR);
    Account saving = new Saving(savingID, APR);
    Account cd = new CertificateOfDeposit(cdID, APR, startAmount);

    @Test
    void bank_is_empty() {
        assertTrue(bank.getStoredAccounts().isEmpty());
    }

    @Test
    void add_checking_account_to_bank() {
        bank.addAccount(checking);
        assertEquals(checking, bank.getAccount(checking.getID()));
    }

    @Test
    void add_saving_account_to_bank() {
        bank.addAccount(saving);
        assertEquals(saving, bank.getAccount(saving.getID()));
    }

    @Test
    void add_cd_account_to_bank() {
        bank.addAccount(cd);
        assertEquals(cd, bank.getAccount(cd.getID()));
    }

    @Test
    void add_multiple_accounts_to_bank() {
        bank.addAccount(checking);
        bank.addAccount(saving);
        bank.addAccount(cd);
        assertEquals(bank.getAccount(savingID), saving);
        assertEquals(bank.getAccount(checkingID), checking);
        assertEquals(bank.getAccount(cdID), cd);
    }

    @Test
    void initial_balance_for_checking_and_saving() {
        bank.addAccount(checking);
        bank.addAccount(saving);
        assertEquals(0, getBalance(checkingID));
        assertEquals(0, getBalance(savingID));
    }

    @Test
    void initial_balance_for_cd() {
        bank.addAccount(cd);
        assertEquals(startAmount, getBalance(cdID));
    }

    @Test
    void single_deposit_into_checking() {
        bank.addAccount(checking);
        bank.getAccount(checkingID).deposit(1000);
        assertEquals(1000, getBalance(checkingID));
    }


    @Test
    void single_deposit_into_saving() {
        bank.addAccount(saving);
        bank.getAccount(savingID).deposit(1000);
        assertEquals(1000, getBalance(savingID));
    }

    @Test
    void single_deposit_into_cd() {
        bank.addAccount(cd);
        bank.getAccount(cdID).deposit(1000);
        assertEquals(1000 + startAmount, getBalance(cdID));
    }

    @Test
    void multiple_deposit_into_checking() {
        bank.addAccount(checking);
        bank.getAccount(checkingID).deposit(1000);
        bank.getAccount(checkingID).deposit(1000);
        bank.getAccount(checkingID).deposit(1000);
        assertEquals(3000, getBalance(checkingID));
    }

    @Test
    void multiple_deposit_into_saving() {
        bank.addAccount(saving);
        bank.getAccount(savingID).deposit(1000);
        bank.getAccount(savingID).deposit(1000);
        bank.getAccount(savingID).deposit(1000);
        assertEquals(3000, getBalance(savingID));
    }

    @Test
    void multiple_deposit_into_cd() {
        bank.addAccount(cd);
        bank.getAccount(cdID).deposit(1000);
        bank.getAccount(cdID).deposit(1000);
        bank.getAccount(cdID).deposit(1000);
        assertEquals(3000 + startAmount, getBalance(cdID));
    }

    @Test
    void withdraw_from_checking() {
        bank.addAccount(checking);
        bank.getAccount(checkingID).deposit(2000);
        bank.getAccount(checkingID).withdraw(1000);
        assertEquals(1000, getBalance(checkingID));

    }

    @Test
    void withdraw_from_saving() {
        bank.addAccount(saving);
        bank.getAccount(savingID).deposit(2000);
        bank.getAccount(savingID).withdraw(1000);
        assertEquals(1000, getBalance(savingID));
    }

    @Test
    void withdraw_from_saving_test_able_to_withdraw() {
        bank.addAccount(saving);
        bank.getAccount(savingID).deposit(1000);
        bank.getAccount(savingID).withdraw(500);
        assertFalse(bank.getAccount(savingID).isAbleToWithdraw());
    }

    @Test
    void withdraw_from_cd() {
        bank.addAccount(cd);
        bank.getAccount(cdID).deposit(2000);
        bank.getAccount(cdID).withdraw(1000);
        assertEquals(1000 + startAmount, getBalance(cdID));
    }


    @Test
    void withdraw_from_checking_past_zero() {
        bank.addAccount(checking);
        bank.getAccount(checkingID).withdraw(1000);
        assertEquals(0, getBalance(checkingID));
    }

    @Test
    void withdraw_from_saving_past_zero() {
        bank.addAccount(saving);
        bank.getAccount(savingID).withdraw(1000);
        assertEquals(0, getBalance(savingID));
    }

    @Test
    void withdraw_from_cd_past_zero() {
        bank.addAccount(cd);
        bank.getAccount(cdID).withdraw(5000);
        assertEquals(0, getBalance(cdID));
    }


    public double getBalance(String savingID) {
        return bank.getAccount(savingID).getBalance();
    }


}
