public class CommandProcessor {
    protected String[] parsedCommand;
    private Bank bank;
    private String command;


    public CommandProcessor(String command, Bank bank) {
        this.command = command;
        this.parsedCommand = command.split(" ", 0);
        this.bank = bank;
    }

    public void process() {
        if (parsedCommand[0].equalsIgnoreCase("create")) {
            new CreateProcessor(command, bank);
        } else {
        }

    }

}
