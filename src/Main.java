import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observer;

public class Main {

    private static final ApplicationData applicationData = new ApplicationData(new LinkedHashMap<>());

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
                    item = input[1];
                    value = Double.valueOf(input[2]);
                    applicationData.addData(item , value);
                    break;
                case ADD_CHART:
                    Observer observer = checkChartType(input[1]);
                    if(null == observer){
                        System.out.println("Input Error");
                        line = reader.readLine();
                        continue;
                    }else {
                        applicationData.addObserver(observer);
                    }
                    break;
                case CHANGE:
                    System.out.println(input[1] +" change " + input[2] +" " +input[3]);
                    item = input[2];
                    value = Double.valueOf(input[3]);
                    applicationData.changeData(item , value);
                    break;
            }

            line = reader.readLine();
        }

    }

    private static Observer checkChartType(String charTypeStr) {

        ChartType chartType = ChartType.toChartType(charTypeStr);

        if (null == chartType) {
            System.out.println("Input Error");
            return null;
        }

        Observer observer;

        switch (chartType) {
            case SPREAD:
                observer = new SpreadSheet(applicationData.getData());
                break;
            case BAR:
                observer = new BarChart(applicationData.getData());
                break;
            case PIE:
                observer = new PieChart(applicationData.getData());
                break;
            default:
                return null;
        }

        return observer;

    }

}
