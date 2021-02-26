public class CreateProcessor extends CommandProcessor {

    private String accountType;
    private String accountID;
    private double accountAPR;
    private double accountCdStartAmount;

    CreateProcessor(String command, Bank bank) {
        super(command, bank);
        accountType = parsedCommand[1];
        accountID = parsedCommand[2];
        accountAPR = Double.parseDouble(parsedCommand[3]);
        if (isCd(accountType)) {
            accountCdStartAmount = Double.parseDouble(parsedCommand[4]);
        }
    }

    private boolean isCd(String accountType) {
        if (accountType.equalsIgnoreCase("cd")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void process() {
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
}
