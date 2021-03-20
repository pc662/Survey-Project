package banking;

public class PassTimeProcessor extends CommandProcessor {

    int months;

    public PassTimeProcessor(Bank bank) {
        super(bank);
    }

    public void process(String[] parsedCommand) {
        setMonth(parsedCommand);
        bank.passTime(months);
    }

    private void setMonth(String[] command) {
        months = Integer.parseInt(command[1]);
    }

}
