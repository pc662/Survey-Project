public class CertificateOfDeposit extends Account {

    public CertificateOfDeposit(String accountTypeInput, String identifier, double apr, int startAmount) {
        super(accountTypeInput, identifier, apr);
        this.setBalance(startAmount);
    }
}
