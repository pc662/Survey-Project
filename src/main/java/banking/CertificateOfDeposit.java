package banking;

public class CertificateOfDeposit extends Account {


    public CertificateOfDeposit(String identifier, double apr, double startAmount) {
        super("cd", identifier, apr);
        this.setBalance(startAmount);
        this.ableToWithdraw = false;
    }


}


