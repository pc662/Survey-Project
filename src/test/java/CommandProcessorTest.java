import org.junit.jupiter.api.Test;

public class CommandProcessorTest {
    String checkingType = "checking";

    @Test
    void create_checking() {
        Bank bank = new Bank();
        checkingAccountCommand = "create checking 01234567 1.0";
        CommandProcessor commandProcessor = new CommandProcessor(command, checkingAccountCommand);
        assert (bank.getAccount().getAccountType(),checkingType);
    }
}
