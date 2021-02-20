public class Validation {

    String[] splitString;

    public Validation(String string) {
        splitString = string.split(" ", 0);
    }

    public boolean validate() {
        return validateCommand();
    }

    private boolean validateCommand() {
        if (splitString[0].equals("Create") || splitString[0].equals("create")) {
            return true;
        } else {
            return false;
        }
    }


}
