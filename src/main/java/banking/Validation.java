package banking;

public class Validation {

    protected Bank bank;
    String[] splitString;
    int stringLength;
    int month;
    String command;
    String accountType;
    String ID;
    double APR;
    double money;
    double cdMoney;
    String originalString;


    public Validation(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String string) {
        originalString = string;
        splitString = string.split(" ", 0);
        stringLength = splitString.length;
        return checkValidationType();
    }

    private boolean checkValidationType() {
        if (splitString[0].equalsIgnoreCase("create")) {
            return new CreateValidation(bank).validate(originalString);
        } else if (splitString[0].equalsIgnoreCase("deposit")) {
            return new DepositValidation(bank).validate(originalString);
        } else if (splitString[0].equalsIgnoreCase("pass")) {
            return new PassTimeValidation(bank).validate(originalString);
        } else if (splitString[0].equalsIgnoreCase("withdraw")) {
            return new WithdrawValidation(bank).validate(originalString);
        } else if (splitString[0].equalsIgnoreCase("transfer")) {
            return new TransferValidation(bank).validate(originalString);
        } else {
            return false;
        }
    }


}
