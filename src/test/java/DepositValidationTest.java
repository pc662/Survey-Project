public class DepositValidationTest {

    CreateValidation validation;
    Bank bank = new Bank();
    Checking checking = new Checking("01234567", 0.01);
    Saving saving = new Saving("01234567", 0.01);


    /*@Test
    void command_validate_correct_deposit() {
        validation = verify("Deposit 01234567 1000");
        assertTrue(validation.validate());
    }

    private CreateValidation verify(String s) {
        return new DepositValidation(s, bank);
    }*/

}
