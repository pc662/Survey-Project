public class Validation {

    String[] splitString;

    public Validation(String string) {
        splitString = string.split(" ", 0);
    }

    public boolean validate() {
        if (splitString[0].equals("Create") || splitString[0].equals("create")) {
            return true;
        } else {
            return false;
        }

    }

}
