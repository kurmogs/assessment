import org.junit.Test;

import static org.junit.Assert.*;


public class ToolRentalTest {

    @Test
    public void testRentalAgreement1() throws Exception {
        RentalAgreement agreement = ToolRental.checkout("JAKR", 5, 101, "09/03/15");
        assertNotNull(agreement);
    }

    @Test
    public void testRentalAgreement2() throws Exception {
        RentalAgreement agreement = ToolRental.checkout("LADW", 3, 10, "07/02/20");
        assertNotNull(agreement);
    }

    @Test
    public void testRentalAgreement3() throws Exception {
        RentalAgreement agreement = ToolRental.checkout("CHNS", 5, 25, "07/02/15");
        assertNotNull(agreement);
    }

    @Test
    public void testRentalAgreement4() throws Exception {
        RentalAgreement agreement = ToolRental.checkout("JAKD", 6, 0, "09/03/15");
        assertNotNull(agreement);
    }

    @Test
    public void testRentalAgreement5() throws Exception {
        RentalAgreement agreement = ToolRental.checkout("JAKR", 9, 0, "07/02/15");
        assertNotNull(agreement);
    }

    @Test
    public void testRentalAgreement6() throws Exception {
        RentalAgreement agreement = ToolRental.checkout("JAKR", 4, 50, "07/02/20");
        assertNotNull(agreement);
    }

    @Test
    public void testInvalidRentalDays() {
        Exception exception = assertThrows(Exception.class, () -> {
            ToolRental.checkout("LADW", 0, 10, "07/02/20");
        });
        assertTrue(exception.getMessage().contains("Rental day count must be 1 or greater."));
    }

    @Test
    public void testInvalidDiscountPercent() {
        Exception exception = assertThrows(Exception.class, () -> {
            ToolRental.checkout("LADW", 3, 101, "07/02/20");
        });
        assertTrue(exception.getMessage().contains("Discount percent must be between 0 and 100."));
    }
}
