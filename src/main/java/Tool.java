import java.util.HashMap;
import java.util.Map;

/**
 * Represents a tool available for rental.
 */
public class Tool {

    private static final Map<String, Tool> TOOLS = new HashMap<String, Tool>();

    static {
        // Initialize tool instances and populate the TOOLS map
        TOOLS.put("LADW", new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false));
        TOOLS.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true));
        TOOLS.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false));
        TOOLS.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false));
    }

    public String code;
    public String type;
    public String brand;
    private double dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    /**
     * Private constructor to create a Tool object.
     *
     * @param code          The unique code for the tool.
     * @param type          The type or category of the tool.
     * @param brand         The brand or manufacturer of the tool.
     * @param dailyCharge   The daily rental charge for the tool.
     * @param weekdayCharge Whether the tool incurs rental charges on weekdays.
     * @param weekendCharge Whether the tool incurs rental charges on weekends.
     * @param holidayCharge Whether the tool incurs rental charges on holidays.
     */
    private Tool(String code, String type, String brand, double dailyCharge, boolean weekdayCharge,
                 boolean weekendCharge, boolean holidayCharge) {
        this.code = code;
        this.type = type;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    /**
     * Retrieves a Tool object based on its unique code.
     *
     * @param code The code of the tool to retrieve.
     * @return The Tool object corresponding to the code, or null if not found.
     */
    public static Tool getToolByCode(String code) {
        return TOOLS.get(code);
    }

    /**
     * Retrieves the daily rental charge for the tool.
     *
     * @return The daily rental charge.
     */
    public double getDailyCharge() {
        return dailyCharge;
    }

    /**
     * Checks if the tool incurs rental charges on weekdays.
     *
     * @return true if the tool incurs rental charges on weekdays, false otherwise.
     */
    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    /**
     * Checks if the tool incurs rental charges on weekends.
     *
     * @return true if the tool incurs rental charges on weekends, false otherwise.
     */
    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    /**
     * Checks if the tool incurs rental charges on holidays.
     *
     * @return true if the tool incurs rental charges on holidays, false otherwise.
     */
    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    /**
     * Returns a string representation of the Tool object.
     *
     * @return String representation of the Tool object.
     */
    @Override
    public String toString() {
        return String.format("Tool code: %s\nTool type: %s\nBrand: %s\n", code, type, brand);
    }
}