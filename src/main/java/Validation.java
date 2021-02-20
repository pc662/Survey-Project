public class Validation {

    String[] splitString;

    public Validation(String string) {
        splitString = string.split(" ", 0);
    }

    public boolean validate() {
        return validateCommand();
    }

    private boolean validateCommand() {
        if (isCommand()) {
            return validateAccountType();
        } else {
            return false;
        }
    }


    private boolean validateAccountType() {
        if (isAccountType()) {
            return true;
        } else {
            return false;
        }

    }

    private boolean isCommand() {
        return splitString[0].equalsIgnoreCase("create");
    }

    private boolean isAccountType() {
        return splitString[1].equalsIgnoreCase("CD") || splitString[1].equalsIgnoreCase("checking") || splitString[1].equalsIgnoreCase("saving");
    }


}
