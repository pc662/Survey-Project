package banking;

public class PassTimeValidation extends Validation {
    public PassTimeValidation(Bank bank) {
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
        if (stringLength != 2) {
            return false;
        } else {
            try {
                Integer.parseInt(splitString[1]);
                return validateMonth();
            } catch (Exception e) {
                return false;
            }
        }
    }

    public boolean validateMonth() {
        month = Integer.parseInt(splitString[1]);
        if (month < 2) {
            return false;
        } else if (month > 60) {
            return false;
        } else {
            return true;
        }
    }
}
