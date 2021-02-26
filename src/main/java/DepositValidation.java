public class DepositValidation extends Validation {

    public DepositValidation(Bank bank) {
        super(bank);
    }


    @Override
    public boolean validate(String command) {
        originalString = command;
        splitString = command.split(" ", 0);
        stringLength = splitString.length;
        return validateCommand();
    }


    public boolean validateCommand() {
        if (splitString[0].equalsIgnoreCase("deposit")) {
            return checkLength();
        } else {
            return false;
        }
    }

    private boolean checkLength() {
        if (stringLength != 3) {
            return false;
        } else {
            return validateID();
        }
    }


    public boolean validateID() {
        if (isInt(splitString[1])) {
            return isEightDigits();
        } else {
            return false;
        }
    }

    public boolean isInt(String integer) {
        try {
            Integer.parseInt(integer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEightDigits() {
        if (splitString[1].length() != 8) {
            return false;
        } else {
            return validateAccountExists();
        }
    }

    public boolean validateAccountExists() {
        if (bank.getStoredAccounts().containsKey(splitString[1])) {
            return validateAccountType();
        } else {
            return false;
        }
    }


    public boolean validateAccountType() {
        try {
            double depositAmount = Double.parseDouble(splitString[2]);
            return bank.isAccountDepositAmountValid(splitString[1], depositAmount);
        } catch (Exception e) {
            return false;
        }
    }


}
