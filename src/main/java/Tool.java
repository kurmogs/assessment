import java.util.HashMap;
import java.util.Map;

public class Tool {

    private static final Map<String, Tool> TOOLS = new HashMap<String, Tool>();

    static {
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

    private Tool(String code, String type, String brand, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.code = code;
        this.type = type;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public static Tool getToolByCode(String code) {
        return TOOLS.get(code);
    }

    public double getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    @Override
    public String toString() {
        return String.format("Tool code: %s\nTool type: %s\nBrand: %s\n", code, type, brand);
    }
}