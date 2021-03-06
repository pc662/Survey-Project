import java.util.ArrayList;
import java.util.List;

public class CommandStorage {

    ArrayList<String> invalidCommands = new ArrayList<>();


    public void storeInvalidCommand(String string) {
        invalidCommands.add(string);
    }

    public List<String> getInvalidCommands() {
        return invalidCommands;
    }


}
