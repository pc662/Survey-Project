public abstract class Validation {

    String[] splitString;
    Bank bank;

    public Validation(String string, Bank bank) {
        splitString = string.split(" ", 0);
        this.bank = bank;
    }

    public abstract boolean validate();

    public abstract boolean validateID();

    public abstract boolean validateAccountType();

}
