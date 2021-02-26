public class DepositProcessor extends CommandProcessor {

    private String accountID;
    private double depositAmount;

    DepositProcessor(String command, Bank bank) {
        super(command, bank);
        accountID = parsedCommand[1];
        depositAmount = Double.parseDouble(parsedCommand[2]);
    }

    @Override
    public void process() {
        bank.getAccount(accountID).deposit(depositAmount);
    }


}
