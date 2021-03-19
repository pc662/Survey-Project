package banking;

public class WithdrawProcessor extends CommandProcessor {

    double money;

    public WithdrawProcessor(Bank bank) {
        super(bank);
    }

    public void process(String[] parsedCommand) {
        money = Double.parseDouble(parsedCommand[2]);
        bank.getAccount(parsedCommand[1]).withdraw(money);
    }
}
