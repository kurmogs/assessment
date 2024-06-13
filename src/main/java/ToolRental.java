import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public static RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, String checkoutDate) throws Exception {
        if (rentalDays < 1) throw new Exception("Rental day count must be 1 or greater.");
        if (discountPercent < 0 || discountPercent > 100) throw new Exception("Discount percent must be between 0 and 100.");

        Tool tool = Tool.getToolByCode(toolCode);
        LocalDate checkoutLocalDate = LocalDate.parse(checkoutDate, DATE_FORMAT);
        LocalDate dueDate = checkoutLocalDate.plusDays(rentalDays);

        int chargeDays = calculateChargeDays(checkoutLocalDate, dueDate, tool);
        double preDiscountCharge = chargeDays * tool.getDailyCharge();
        double discountAmount = (preDiscountCharge * discountPercent) / 100;
        double finalCharge = preDiscountCharge - discountAmount;

        return new RentalAgreement(tool, rentalDays, checkoutLocalDate, dueDate, chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge);
    }

    private static int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, Tool tool) {
        int chargeDays = 0;
        for (LocalDate date = checkoutDate.plusDays(1); !date.isAfter(dueDate); date = date.plusDays(1)) {
            if (isChargeableDay(date, tool)) chargeDays++;
        }
        return chargeDays;
    }

    private static boolean isChargeableDay(LocalDate date, Tool tool) {
        if (isHoliday(date)) return tool.isHolidayCharge();
        if (isWeekend(date)) return tool.isWeekendCharge();
        return tool.isWeekdayCharge();
    }

    private static boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    private static boolean isHoliday(LocalDate date) {
        int year = date.getYear();
        LocalDate independenceDay = LocalDate.of(year, 7, 4);
        if (independenceDay.getDayOfWeek().getValue() == 6) independenceDay = independenceDay.minusDays(1);
        else if (independenceDay.getDayOfWeek().getValue() == 7) independenceDay = independenceDay.plusDays(1);

        LocalDate laborDay = LocalDate.of(year, 9, 1);
        while (laborDay.getDayOfWeek().getValue() != 1) laborDay = laborDay.plusDays(1);

        return date.equals(independenceDay) || date.equals(laborDay);
    }
}