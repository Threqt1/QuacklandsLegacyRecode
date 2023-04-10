package utils;

public class ASCII {
    public static final String CLEAR_CODE = "\033[H\033[2J";
    public static final String DEFAULT_COLOR = "255;255;255";

    public static String colorize(String input, String color) {
        return "\033[38;2;" + color + "m" + input + "\033[0m";
    }
}
