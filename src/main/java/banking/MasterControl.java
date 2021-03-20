package banking;

import java.util.List;

public class MasterControl {
    Bank bank;
    Validation commandValidator;
    CommandProcessor commandProcessor;
    CommandStorage commandStorage;
    String[] parsedCommand;

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
                checkCommandType(command);
                commandProcessor.process(command);
            } else {
                commandStorage.storeInvalidCommand(command);
            }
        }
        return commandStorage.getOutput();
    }

    private void checkCommandType(String command) {
        parsedCommand = command.split(" ", 0);
        if (parsedCommand[0].equalsIgnoreCase("create")) {
            commandStorage.createHistory(parsedCommand[2]);
        } else if (parsedCommand[0].equalsIgnoreCase("deposit")) {
            commandStorage.storeValidCommand(parsedCommand[1], command);
        } else if (parsedCommand[0].equalsIgnoreCase("withdraw")) {
            commandStorage.storeValidCommand(parsedCommand[1], command);
        } else if (parsedCommand[0].equalsIgnoreCase("transfer")) {
            commandStorage.storeValidCommand(parsedCommand[1], command);
            commandStorage.storeValidCommand(parsedCommand[2], command);
        }
    }
}

