public abstract class Validation {

    String[] splitString;
    Bank bank;
    String command;
    String accountType;
    String ID;
    double APR;
    double cdMoney;


    public Validation(String string, Bank bank) {
        splitString = string.split(" ", 0);
        this.bank = bank;
    }

    public abstract boolean validate();

    public abstract boolean validateCommand();

    public abstract boolean validateID();

    public abstract boolean validateAccountType();


}
