package banking;

public class Transfer {
    Account accountToWithdraw;
    Account accountToDeposit;
    double amountWithdrawn;

    public Transfer(Account accountFrom, Account accountTo) {
        accountToWithdraw = accountFrom;
        accountToDeposit = accountTo;
    }

    public void transfer(int amountToTransfer) {
        accountToWithdraw.withdraw(amountToTransfer);
        amountWithdrawn = accountToWithdraw.getAmountWithdrawn();
        accountToDeposit.deposit(amountWithdrawn);
    }
}
