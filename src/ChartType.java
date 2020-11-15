import java.util.HashMap;
import java.util.Map;

public enum ChartType {
    SPREAD("Spreadsheet"),
    BAR("BarChart"),
    PIE("PieChart ");

    private String chartType;

    private static class Holder {
        static Map<String, ChartType> MAP = new HashMap<>();
    }

    ChartType(String chartType) {

        this.chartType = chartType;
        Holder.MAP.put(chartType, this);
    }

    public String getChartType() {
        return chartType;
    }

    public static ChartType toChartType(String chartType) {
        return Holder.MAP.get(chartType);
    }
}
