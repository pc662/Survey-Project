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
                parsedCommand = command.split(" ", 0);
                checkCreate(parsedCommand[0]);
            } else {
                commandStorage.storeInvalidCommand(command);
            }
        }
        return commandStorage.getInvalidCommands();
    }

    private void checkCreate(String command) {
        if (command.equalsIgnoreCase("create")) {
            commandStorage.createHistory();
        } else if (command.equalsIgnoreCase("deposit")) {
            commandStorage.storeValidCommand();
        }
    }
}
}
