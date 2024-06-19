import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * ToolRental class handles the checkout process and calculates rental
 * agreements for tools.
 */
public class ToolRental {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yy");

    public static void main(String[] args) {
        // Sample usage
        try {
            RentalAgreement agreement = checkout("LADW", 3, 10, "07/02/20");
            System.out.println(agreement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks out a tool for rental and calculates the rental agreement.
     *
     * @param toolCode        The code of the tool to be rented.
     * @param rentalDays      The number of days for which the tool is rented.
     * @param discountPercent The discount percentage to be applied.
     * @param checkoutDate    The date when the tool is checked out (formatted as
     *                        "MM/dd/yy").
     * @return The RentalAgreement object detailing the rental agreement.
     * @throws Exception if rentalDays is less than 1 or discountPercent is not
     *                   between 0 and 100.
     */
    public static RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, String checkoutDate)
            throws Exception {
        if (rentalDays < 1)
            throw new Exception("Rental day count must be 1 or greater.");
        if (discountPercent < 0 || discountPercent > 100)
            throw new Exception("Discount percent must be between 0 and 100.");

        Tool tool = Tool.getToolByCode(toolCode);
        LocalDate checkoutLocalDate = LocalDate.parse(checkoutDate, DATE_FORMAT);
        LocalDate dueDate = checkoutLocalDate.plusDays(rentalDays);

        int chargeDays = calculateChargeDays(checkoutLocalDate, dueDate, tool);
        double preDiscountCharge = chargeDays * tool.getDailyCharge();
        double discountAmount = (preDiscountCharge * discountPercent) / 100;
        double finalCharge = preDiscountCharge - discountAmount;

        return new RentalAgreement(tool, rentalDays, checkoutLocalDate, dueDate, chargeDays, preDiscountCharge,
                discountPercent, discountAmount, finalCharge);
    }

    /**
     * Calculates the number of chargeable days between checkoutDate and dueDate for
     * the given tool.
     *
     * @param checkoutDate The date when the tool is checked out.
     * @param dueDate      The due date for returning the tool.
     * @param tool         The tool being rented.
     * @return The number of chargeable days.
     */
    private static int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, Tool tool) {
        int chargeDays = 0;
        for (LocalDate date = checkoutDate.plusDays(1); !date.isAfter(dueDate); date = date.plusDays(1)) {
            if (isChargeableDay(date, tool))
                chargeDays++;
        }
        return chargeDays;
    }

    /**
     * Checks if the given date is a chargeable day for the given tool.
     *
     * @param date The date to check.
     * @param tool The tool being rented.
     * @return true if the date is a chargeable day, false otherwise.
     */
    private static boolean isChargeableDay(LocalDate date, Tool tool) {
        if (isHoliday(date))
            return tool.isHolidayCharge();
        if (isWeekend(date))
            return tool.isWeekendCharge();
        return tool.isWeekdayCharge();
    }

    /**
     * Checks if the given date is a weekend day (Saturday or Sunday).
     *
     * @param date The date to check.
     * @return true if the date is a weekend day, false otherwise.
     */
    private static boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    /**
     * Checks if the given date is a holiday (Independence Day or Labor Day).
     *
     * @param date The date to check.
     * @return true if the date is a holiday, false otherwise.
     */
    private static boolean isHoliday(LocalDate date) {
        int year = date.getYear();
        LocalDate independenceDay = LocalDate.of(year, 7, 4);
        if (independenceDay.getDayOfWeek().getValue() == 6) {
            independenceDay = independenceDay.minusDays(1); // Move to Friday if Saturday
        } else if (independenceDay.getDayOfWeek().getValue() == 7) {
            independenceDay = independenceDay.plusDays(1); // Move to Monday if Sunday
        }

        LocalDate laborDay = LocalDate.of(year, 9, 1);
        // Move to the first Monday of September
        laborDay = laborDay.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

        return date.equals(independenceDay) || date.equals(laborDay);
    }
}