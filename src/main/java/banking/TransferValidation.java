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
        if (isValidTransferAmount()) {
            if (validateWithdrawID()) {
                return validateDepositID();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    private boolean validateWithdrawID() {
        return new Validation(bank).validate("withdraw " + accountToWithdrawID + " " + transferAmount);
    }

    private boolean validateDepositID() {
        return new Validation(bank).validate("deposit " + accountToDepositID + " " + transferAmount);
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
