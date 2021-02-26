public class Validation {

    String[] splitString;
    int stringLength;
    Bank bank;
    String command;
    String accountType;
    String ID;
    double APR;
    double cdMoney;
    String originalString;


    public Validation(String string, Bank bank) {
        originalString = string;
        splitString = string.split(" ", 0);
        stringLength = splitString.length;
        this.bank = bank;
    }

    public boolean validate() {
        if (splitString[0].equalsIgnoreCase("create")) {
            return new CreateValidation(originalString, bank).validate();
        } else if (splitString[0].equalsIgnoreCase("deposit")) {
            return new DepositValidation(originalString, bank).validate();
        } else {
            return false;
        }
    }


}
