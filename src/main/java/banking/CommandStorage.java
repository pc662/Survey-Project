package banking;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CommandStorage {

    ArrayList<String> invalidCommands = new ArrayList<>();
    LinkedHashMap<String, List<String>> validCommands = new LinkedHashMap<>();


    public void storeInvalidCommand(String string) {
        invalidCommands.add(string);
    }

    public void storeValidCommand(String accountID, String command) {
        validCommands.get(accountID).add(command);
    }

    public List<String> getInvalidCommands() {
        return invalidCommands;
    }


    public void createHistory(String accountID) {
        validCommands.put(accountID, new ArrayList<>());
    }

    public List<String> getOutput(Bank bank) {
        return new Output(validCommands, invalidCommands).output(bank);
    }
}
