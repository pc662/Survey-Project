package banking;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CommandStorage {

    ArrayList<String> invalidCommands = new ArrayList<>();
    LinkedHashMap<String, List<String>> validCommands = new LinkedHashMap<>();
    String[] parsedCommand;


    public void storeInvalidCommand(String string) {
        invalidCommands.add(string);
    }

    public void storeValidCommand(String accountID, String command) {
        validCommands.get(accountID).add(command);
    }

    public List<String> getInvalidCommands() {
        return invalidCommands;
    }

    protected void checkCommandType(String command, Bank bank) {
        parsedCommand = command.split(" ", 0);
        if (parsedCommand[0].equalsIgnoreCase("create")) {
            createHistory(parsedCommand[2]);
        } else if (parsedCommand[0].equalsIgnoreCase("pass")) {
            checkAccount(bank);
        } else if (parsedCommand[0].equalsIgnoreCase("deposit")) {
            storeValidCommand(parsedCommand[1], command);
        } else if (parsedCommand[0].equalsIgnoreCase("withdraw")) {
            storeValidCommand(parsedCommand[1], command);
        } else if (parsedCommand[0].equalsIgnoreCase("transfer")) {
            storeValidCommand(parsedCommand[1], command);
            storeValidCommand(parsedCommand[2], command);
        }
    }


    public void createHistory(String accountID) {
        validCommands.put(accountID, new ArrayList<>());
    }

    public List<String> getOutput(Bank bank) {
        return new Output(validCommands, invalidCommands).output(bank);
    }

    public void checkAccount(Bank bank) {
        checkIfAccountRemoved(bank);
    }

    private void checkIfAccountRemoved(Bank bank) {
        LinkedHashMap<String, List<String>> validCommandsCopy = new LinkedHashMap<>(validCommands);
        for (String accountID : validCommandsCopy.keySet()) {
            if (!bank.getStoredAccounts().containsKey(accountID)) {
                validCommands.remove(accountID);
            }
        }
    }
}
