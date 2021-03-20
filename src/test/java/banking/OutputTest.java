package banking;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class OutputTest {

    LinkedHashMap<String, List<String>> validCommands;
    ArrayList<String> invalidCommands;

    List<String> correctCommands1 = new ArrayList<>();
    List<String> correctCommands2 = new ArrayList<>();

    @BeforeEach
    void setUp() {
        correctCommands1.add("withdraw 01234567 100");
        correctCommands1.add("withdraw 01234567 200");
        correctCommands1.add("transfer 01234567 12345678 300");
        correctCommands1.add("deposit 01234567 500");

        correctCommands2.add("withdraw 12345678 900");
        correctCommands2.add("withdraw 12345678 1000");
        correctCommands2.add("transfer 01234567 12345678 300");
        correctCommands2.add("deposit 12345678 1000");
    }


}
