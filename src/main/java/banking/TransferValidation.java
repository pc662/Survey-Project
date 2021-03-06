package banking;

public class TransferValidation extends Validation {
    String accountToWithdrawID;
    String accountToDepositID;
    double transferAmount;

    public TransferValidation(Bank bank) {
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
        if (stringLength != 4) {
            return false;
        } else {
            return isTransferValid();
        }
    }

    private boolean isTransferValid() {
        accountToWithdrawID = splitString[1];
        accountToDepositID = splitString[2];
        if (sameAccounts()) {
            return false;
        } else if (accountsDoNotExist()) {
            return false;
        } else if (eitherAccountsAreCD()) {
            return false;
        } else {
            return validateTransfer();
        }
    }

    private boolean accountsDoNotExist() {
        if (bank.getStoredAccounts().containsKey(accountToWithdrawID)) {
            return !bank.getStoredAccounts().containsKey(accountToDepositID);
        } else {
            return true;
        }
    }

    private boolean sameAccounts() {
        return accountToDepositID.equals(accountToWithdrawID);
    }

    private boolean validateTransfer() {
        if (isValidTransferAmount()) {
            return validateWithdraw();
        } else {
            return false;
        }
    }

    private boolean eitherAccountsAreCD() {
        return bank.getAccount(accountToWithdrawID).getAccountType().equalsIgnoreCase("cd") || bank.getAccount(accountToDepositID).getAccountType().equalsIgnoreCase("cd");
    }

    private boolean validateWithdraw() {
        return validateWithdrawID();
    }


    private boolean validateWithdrawID() {
        return new Validation(bank).validate("withdraw " + accountToWithdrawID + " " + transferAmount);
    }

    private boolean isValidTransferAmount() {
        try {
            transferAmount = Double.parseDouble(splitString[3]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
