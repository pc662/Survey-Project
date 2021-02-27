import java.util.List;

public class MasterControl {
    Bank bank;
    Validation commandValidator;
    CommandProcessor commandProcessor;
    CommandStorage commandStorage;

    public MasterControl(Bank bank, Validation commandValidator, CommandProcessor commandProcessor,
                         CommandStorage commandStorage) {
        this.bank = bank;
        this.commandValidator = commandValidator;
        this.commandProcessor = commandProcessor;
        this.commandStorage = commandStorage;
    }


    public List<String> start(List<String> input) {
        commandStorage.storeInvalidCommand(input.get(0));
        return commandStorage.getInvalidCommands();
    }
}
