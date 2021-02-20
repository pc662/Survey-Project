public class CreateValidation extends Validation {


    public CreateValidation(String string, Bank bank) {
        super(string, bank);
    }

    @Override
    public boolean validate() {
        return validateCommand();
    }

    @Override
    public boolean validateCommand() {
        if (isCreateCommand()) {
            if (checkLength()) {
                return false;
            }
            command = splitString[0];
            return validateAccountType();
        } else {
            return false;
        }
    }


    @Override
    public boolean validateAccountType() {
        if (isAccountType()) {
            if (isNotCDVerify()) {
                return false;
            }
            accountType = splitString[1];
            return validateID();
        } else {
            return false;
        }
    }

    @Override
    public boolean validateID() {
        if (isID()) {
            return isDuplicateOrNot();
        } else {
            return false;
        }
    }

    private boolean isDuplicateOrNot() {
        if (!bank.getStoredAccounts().containsKey(splitString[2])) {
            ID = splitString[2];
            return validateAPR();
        } else {
            return false;
        }
    }

    private boolean validateAPR() {
        return checkValueAsDouble();
    }

    private boolean checkValueAsDouble() {
        try {
            double apr = Double.parseDouble(splitString[3]);
            return verifyCDAmount(apr);

        } catch (Exception e) {
            return false;
        }
    }

    private boolean verifyCDAmount(double apr) {
        if (apr > 10.00 || apr < 0.00) {
            return false;
        } else {
            APR = apr;
            if (ifCD()) {
                return validateCDAmount();
            }
            return true;
        }
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
                cdMoney = cdAmount;
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
