import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a rental agreement for a tool.
 */
class RentalAgreement {

    private Tool tool;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private int chargeDays;
    private double preDiscountCharge;
    private int discountPercent;
    private double discountAmount;
    private double finalCharge;

    /**
     * Constructs a new RentalAgreement object.
     *
     * @param tool              The tool being rented.
     * @param rentalDays        Number of days the tool is rented for.
     * @param checkoutDate      The date the tool is checked out.
     * @param dueDate           The due date for returning the tool.
     * @param chargeDays        Number of days the tool is charged for.
     * @param preDiscountCharge Total charge before any discounts.
     * @param discountPercent   Percentage discount applied.
     * @param discountAmount    Amount of discount applied.
     * @param finalCharge       Final charge after applying discounts.
     */
    public RentalAgreement(Tool tool, int rentalDays, LocalDate checkoutDate, LocalDate dueDate, int chargeDays,
                           double preDiscountCharge, int discountPercent, double discountAmount, double finalCharge) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    /**
     * Returns a string representation of the RentalAgreement object.
     *
     * @return String representation of the RentalAgreement.
     */
    @Override
    public String toString() {
        return String.format(
                "Tool code: %s\nTool type: %s\nBrand: %s\nRental days: %d\nCheckout date: %s\nDue date: %s\nDaily rental charge: $%.2f\nCharge days: %d\nPre-discount charge: $%.2f\nDiscount percent: %d%%\nDiscount amount: $%.2f\nFinal charge: $%.2f\n",
                tool.code, tool.type, tool.brand, rentalDays,
                checkoutDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")),
                dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")),
                tool.getDailyCharge(), chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge);
    }
}