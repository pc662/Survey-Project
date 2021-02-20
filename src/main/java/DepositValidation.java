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
            return true;
        }
    }

    @Override
    public boolean validateID() {
        return true;
    }

    @Override
    public boolean validateAccountType() {
        return true;
    }


}
