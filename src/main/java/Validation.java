public class Validation {

    String[] splitString;
    Bank bank;

    public Validation(String string, Bank bank) {
        splitString = string.split(" ", 0);
        this.bank = bank;
    }

    public boolean validate() {
        return validateCommand();
    }

    private boolean validateCommand() {
        if (isCreateCommand()) {
            return validateAccountType();
        } else {
            return false;
        }
    }


    private boolean validateAccountType() {
        if (isAccountType()) {
            return validateID();
        } else {
            return false;
        }

    }

    private boolean validateID() {
        if (isID()) {
            return isDuplicateOrNot();
        } else {
            return false;
        }
    }

    private boolean isDuplicateOrNot() {
        if (!bank.getStoredAccounts().containsKey(splitString[2])) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isID() {
        return (splitString[2].matches("[0-9]+")) && (splitString[2].length() == 8);
    }

    private boolean isCreateCommand() {
        return splitString[0].equalsIgnoreCase("create");
    }

    private boolean isAccountType() {
        return splitString[1].equalsIgnoreCase("CD") || splitString[1].equalsIgnoreCase("checking") || splitString[1].equalsIgnoreCase("saving");
    }


}
