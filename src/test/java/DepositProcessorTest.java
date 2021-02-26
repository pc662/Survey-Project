import org.junit.jupiter.api.Test;

public class DepositProcessorTest {

    String depositZeroDollarsToChecking = "deposit 01234567 0";
    String depositZeroDollarsToSaving = "deposit 12345678 0";
    String depositHundredDollarsToChecking = "deposit 01234567 100";
    String getDepositHundredDollarsToSaving = "deposit 12345678 100";

    double APR = 0.1;

    Checking checking = new Checking("01234567", APR);
    Saving saving = new Saving("12345678", APR);

    @Test
    void deposit_zero_into_checking() {

    }


}
