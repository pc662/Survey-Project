package banking;

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
        for (String command : input) {
            if (commandValidator.validate(command)) {
                commandProcessor.process(command);
                commandStorage.checkCommandType(command, bank);
            } else {
                commandStorage.storeInvalidCommand(command);
            }
        }
        return commandStorage.getOutput(bank);
    }


}

