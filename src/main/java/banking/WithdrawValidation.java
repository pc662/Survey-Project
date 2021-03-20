package banking;

public class WithdrawValidation extends Validation {
    public WithdrawValidation(Bank bank) {
        super(bank);
    }

    @Override
    public boolean validate(String command) {
        originalString = command;
        splitString = command.split(" ", 0);
        stringLength = splitString.length;
        return validateCommand();
    }

    private boolean validateCommand() {
        if (stringLength != 3) {
            return false;
        } else {
            return checkIfDouble();
        }
    }

    private boolean checkIfDouble() {
        try {
            money = Double.parseDouble(splitString[2]);
            return validateID();
        } catch (Exception e) {
            return false;
        }
    }


    private boolean validateID() {
        if (bank.getStoredAccounts().containsKey(splitString[1])) {
            return validateAccountType();
        } else {
            return false;
        }
    }

    private boolean validateAccountType() {
        ID = splitString[1];
        if (isSaving()) {
            return validateSaving();
        } else if (isChecking()) {
            return validateChecking();
        } else if (isCd()) {
            return validateCD();
        } else {
            return false;
        }
    }

    private boolean validateSaving() {
        if (bank.getAccount(ID).isAbleToWithdraw()) {
            return validateSavingsAmount();
        } else {
            return false;
        }
    }

    private boolean validateSavingsAmount() {
        return (!(money > 1000)) && (!(money < 0));
    }

    private boolean validateChecking() {
        return validateCheckingAmount();
    }

    private boolean validateCheckingAmount() {
        return (!(money > 400)) && (!(money < 0));
    }

    private boolean validateCD() {
        if (bank.getAccount(ID).isAbleToWithdraw()) {
            return validateCDAmount();
        } else {
            return false;
        }
    }

    private boolean validateCDAmount() {
        return money > bank.getAccount(ID).getBalance();
    }

    private boolean isCd() {
        return bank.getAccount(ID).getAccountType().equalsIgnoreCase("cd");
    }

    private boolean isChecking() {
        return bank.getAccount(ID).getAccountType().equalsIgnoreCase("checking");
    }

    private boolean isSaving() {
        return bank.getAccount(ID).getAccountType().equalsIgnoreCase("savings");
    }


}
