public class CreateValidation {

    String[] splitString;
    Bank bank;

    public CreateValidation(String string, Bank bank) {
        splitString = string.split(" ", 0);
        this.bank = bank;
    }

    public boolean validate() {
        return validateCommand();
    }

    private boolean validateCommand() {
        if (isCreateCommand()) {
            if (checkLength()) {
                return false;
            }
            return validateAccountType();
        } else {
            return false;
        }
    }


    private boolean validateAccountType() {
        if (isAccountType()) {
            if (isNotCDVerify()) {
                return false;
            }
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

    private boolean validateAPR() {
        return checkValueAsDouble();
    }

    private boolean validateCDAmount() {
        return verifyCDAmount();
    }

    private boolean verifyCDAmount() {
        try {
            double cdAmount = Double.parseDouble(splitString[4]);
            if (cdAmount < 1000 || cdAmount > 10000) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private boolean checkValueAsDouble() {
        try {
            double apr = Double.parseDouble(splitString[3]);
            if (apr > 10.00 || apr < 0.00) {
                return false;
            } else {
                if (ifCD()) {
                    return validateCDAmount();
                }
                return true;
            }

        } catch (Exception e) {
            return false;
        }
    }

    private boolean ifCD() {
        if (splitString[1].equalsIgnoreCase("cd")) {
            return true;
        }
        return false;
    }


    private boolean isNotCDVerify() {
        if (!splitString[1].equalsIgnoreCase("cd")) {
            if (splitString.length >= 5) {
                return true;
            }
        }
        return false;
    }


    private boolean isDuplicateOrNot() {
        if (!bank.getStoredAccounts().containsKey(splitString[2])) {
            return validateAPR();
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
        return splitString[1].equalsIgnoreCase("CD") || splitString[1].equalsIgnoreCase("checking") || splitString[1].equalsIgnoreCase("savings");
    }

    private boolean checkLength() {
        if (splitString.length < 4 || splitString.length > 5) {
            return true;
        }
        return false;
    }


}
