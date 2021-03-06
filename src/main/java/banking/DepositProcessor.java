package banking;

public class DepositProcessor extends CommandProcessor {

    private String accountID;
    private double depositAmount;

    DepositProcessor(Bank bank) {
        super(bank);
    }


    public void process(String[] parsedCommand) {
        accountID = parsedCommand[1];
        depositAmount = Double.parseDouble(parsedCommand[2]);
        bank.getAccount(accountID).deposit(depositAmount);
    }


}
