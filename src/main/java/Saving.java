public class Saving extends Account {

    public Saving(String identifier, double apr) {
        super("saving", identifier, apr);
    }

    @Override
    public boolean isTypeValid(String id, String depositAmount) {

    }
}
