public class CertificateOfDeposit extends Account {

    public CertificateOfDeposit(String identifier, double apr, int startAmount) {
        super("CD", identifier, apr);
        this.setBalance(startAmount);
    }
}


