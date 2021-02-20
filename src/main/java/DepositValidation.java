public class DepositValidation extends Validation {

    public DepositValidation(String string, Bank bank) {
        super(string, bank);
    }

    @Override
    public boolean validate() {
        return validateCommand();
    }

    @Override
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

    @Override
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
            return true;
        }
    }

    @Override
    public boolean validateAccountType() {
        return true;
    }


}
