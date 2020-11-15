import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    DATA("data"),
    ADD_CHART("addChart"),
    CHANGE("change");

    private String command;

    private static class Holder {
        static Map<String, CommandType> MAP = new HashMap<>();
    }

    CommandType(String command) {

        this.command = command;
        Holder.MAP.put(command, this);
    }

    public String getCommand() {
        return command;
    }

    public static CommandType toCommandType(String command) {
        return Holder.MAP.get(command);
    }
}
