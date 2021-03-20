package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Output {

    LinkedHashMap<String, List<String>> validCommands;
    ArrayList<String> invalidCommands;
    Bank bank;
    List<String> output = new ArrayList<>();

    String accountType;
    String accountID;
    double accountBalance;
    String formattedAccountBalance;
    double accountAPR;
    String formattedAPR;
    String accountState;

    public Output(LinkedHashMap<String, List<String>> validCommandMap, ArrayList<String> invalidCommandList) {
        validCommands = validCommandMap;
        invalidCommands = invalidCommandList;
    }

    public List<String> output(Bank passedBank) {
        bank = passedBank;
        if (!validCommands.isEmpty()) {
            iterateThroughValidCommands();
        }
        output.addAll(invalidCommands);
        return output;
    }

    private void iterateThroughValidCommands() {
        for (String accountIdentifier : validCommands.keySet()) {
            List<String> transactionHistory = formatOutput(accountIdentifier);
            output.addAll(transactionHistory);
        }
    }

    private List<String> formatOutput(String accountIdentifier) {
        List<String> transactionHistory = validCommands.get(accountIdentifier);

        formatAccountType(accountIdentifier);

        formatAccountID(accountIdentifier);

        formatAccountBalanceAndAPR();

        accountState = accountType + " " + accountID + " " + formattedAccountBalance + " " + formattedAPR;
        output.add(accountState);
        return transactionHistory;
    }

    private void formatAccountID(String accountIdentifier) {
        accountID = accountIdentifier;
    }

    private void formatAccountBalanceAndAPR() {
        accountBalance = bank.getAccount(accountID).getBalance();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        formattedAccountBalance = decimalFormat.format(accountBalance);

        accountAPR = bank.getAccount(accountID).getAPR();
        formattedAPR = decimalFormat.format(accountAPR);
    }

    private void formatAccountType(String accountIdentifier) {
        accountType = bank.getAccount(accountIdentifier).getAccountType();
        accountType = accountType.substring(0, 1).toUpperCase() + accountType.substring(1).toLowerCase();
    }
}
