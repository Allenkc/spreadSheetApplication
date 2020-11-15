import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;

public class Main {

    private static final ApplicationData applicationData = new ApplicationData(new LinkedHashMap<>());
    private static boolean spreadSheetLocked = false;
    private static boolean barChartLocked = false;
    private static boolean pieChartLocked = false;


    public static void main(String[] args) throws Exception {
        if (null == args[0]) {
            System.out.println("Input Error");
            return;
        }

        String fileName = args[0];
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        while (line != null) {
            String[] input =
                    line.split("\\s+");
            CommandType command = CommandType.toCommandType(input[0]);
            if (null == command) {
                System.out.println("Input Error");
                line = reader.readLine();
                continue;
            }

            String item;
            double value;
            switch (command) {
                case DATA:
                    if (input.length < 3) {
                        System.out.println("Input Error");
                        break;
                    }
                    item = input[1];
                    try {

                        value = Double.valueOf(input[2]);
                        applicationData.addData(item, value);

                    } catch (NumberFormatException ex) {
                        System.out.println("Input Error");
                        break;
                    }

                    break;
                case ADD_CHART:
                    IObserver observer = checkChartType(input[1]);
                    if (null == observer) {
                        break;
                    } else {
                        applicationData.registerObserver(observer);
                    }
                    break;
                case CHANGE:
                    ChartType chartType = ChartType.toChartType(input[1]);

                    if (null == chartType) {
                        System.out.println("Input Error");
                        break;
                    }
                    System.out.println(input[1] + " change " + input[2] + " " + input[3] + ".");
                    item = input[2];
                    value = Double.valueOf(input[3]);
                    applicationData.changeData(item, value);
                    break;
            }

            line = reader.readLine();
        }

    }

    private static IObserver checkChartType(String charTypeStr) {

        ChartType chartType = ChartType.toChartType(charTypeStr);

        if (null == chartType) {
            System.out.println("Input Error");
            return null;
        }

        IObserver observer;

        switch (chartType) {
            case SPREAD:
                if(spreadSheetLocked){
                    // TODO sheet數超過 是否要印出?
                    observer = null;
                    break;
                }else {
                    observer = new SpreadSheet(applicationData.getData());
                    spreadSheetLocked = true;
                }
                break;
            case BAR:
                if(barChartLocked){
                    // TODO sheet數超過 是否要印出?
                    observer = null;
                    break;
                }else {
                    observer = new BarChart(applicationData.getData());
                    barChartLocked = true;
                }
                break;
            case PIE:
                if(pieChartLocked){
                    // TODO sheet數超過 是否要印出?
                    observer = null;
                    break;
                }else {
                    observer = new PieChart(applicationData.getData());
                    pieChartLocked = true;
                }
                break;
            default:
                return null;
        }

        return observer;

    }

}
