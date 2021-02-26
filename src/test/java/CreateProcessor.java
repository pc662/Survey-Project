public class CreateProcessor extends CommandProcessor {

    private String accountType;
    private String accountID;
    private double accountAPR;

    CreateProcessor(String command, Bank bank) {
        super(command, bank);
        accountType = parsedCommand[1];
        accountID = parsedCommand[2];
        accountAPR = Double.parseDouble(parsedCommand[3]);
    }

    @Override
    public void process() {
        if (accountType.equalsIgnoreCase("checking")) {
            Checking checkingAccount = new Checking(accountID, accountAPR);
            bank.addAccount(checkingAccount);
        }
    }
}
