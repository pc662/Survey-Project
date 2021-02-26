public class CreateProcessor extends CommandProcessor {

    private String accountType;
    private String accountID;
    private String accountAPR;

    CreateProcessor(String command, Bank bank) {
        super(command, bank);
        accountType = parsedCommand[1].toLowerCase();
        accountID = parsedCommand[2];
        accountAPR = parsedCommand[3];
    }

    @Override
    public void process() {

    }
}
