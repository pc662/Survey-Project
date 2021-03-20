package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandStorage {

    ArrayList<String> invalidCommands = new ArrayList<>();
    Map<String, List<String>> validCommands;


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
}
