package banking;

public class TransferProcessor extends CommandProcessor {

    Account accountToWithdraw;
    Account accountToDeposit;
    double moneyToTransfer;

    public TransferProcessor(Bank bank) {
        super(bank);
    }

    public void process(String[] parsedCommand) {
        accountToWithdraw = bank.getAccount(parsedCommand[1]);
        accountToDeposit = bank.getAccount(parsedCommand[2]);
        moneyToTransfer = Double.parseDouble(parsedCommand[3]);
        bank.transfer(accountToWithdraw, accountToDeposit, moneyToTransfer);

    }
}
