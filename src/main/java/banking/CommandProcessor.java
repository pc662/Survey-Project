package banking;

public class CommandProcessor {
    protected String[] parsedCommand;
    protected Bank bank;
    private String command;


    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void process(String command) {
        this.command = command;
        this.parsedCommand = command.split(" ", 0);
        if (parsedCommand[0].equalsIgnoreCase("create")) {
            new CreateProcessor(bank).process(parsedCommand);
        } else if (parsedCommand[0].equalsIgnoreCase("deposit")) {
            new DepositProcessor(bank).process(parsedCommand);
        } else if (parsedCommand[0].equalsIgnoreCase("pass")) {
            new PassTimeProcessor(bank).process(parsedCommand);
        } else if (parsedCommand[0].equalsIgnoreCase("withdraw")) {
            new WithdrawProcessor(bank).process(parsedCommand);
        } else if (parsedCommand[0].equalsIgnoreCase("transfer")) {
            new TransferProcessor(bank).process(parsedCommand);
        }

    }

}
