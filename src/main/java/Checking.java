public class Checking extends Account {

    public Checking(String identifier, double apr) {
        super("checking", identifier, apr);
    }

    @Override
    public boolean isTypeValid(String id, String depositAmount) {

    }
}
