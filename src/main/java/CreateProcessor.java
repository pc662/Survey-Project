public class CreateProcessor extends CommandProcessor {

    private String accountType;
    private String accountID;
    private double accountAPR;
    private double accountCdStartAmount;

    CreateProcessor(Bank bank) {
        super(bank);
    }

    private boolean isCd(String accountType) {
        return accountType.equalsIgnoreCase("cd");
    }


    public void process(String[] parsedCommand) {
        setValuesOfCommand(parsedCommand);

        if (accountType.equalsIgnoreCase("checking")) {
            Checking checkingAccount = new Checking(accountID, accountAPR);
            bank.addAccount(checkingAccount);
        } else if (accountType.equalsIgnoreCase("saving")) {
            Saving savingAccount = new Saving(accountID, accountAPR);
            bank.addAccount(savingAccount);
        } else if (accountType.equalsIgnoreCase("cd")) {
            CertificateOfDeposit cdAccount = new CertificateOfDeposit(accountID, accountAPR, accountCdStartAmount);
            bank.addAccount(cdAccount);
        }
    }

    private void setValuesOfCommand(String[] parsedCommand) {
        accountType = parsedCommand[1];
        accountID = parsedCommand[2];
        accountAPR = Double.parseDouble(parsedCommand[3]);
        if (isCd(accountType)) {
            accountCdStartAmount = Double.parseDouble(parsedCommand[4]);
        }
    }
}
