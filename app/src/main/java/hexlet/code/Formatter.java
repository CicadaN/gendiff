package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;
import java.util.Map;

public class Formatter {
    public static String format(Map<String, Object> diff, String format) throws Exception {
        return switch (format.toLowerCase()) {
            case "stylish" -> StylishFormatter.format(diff);
            case "plain" -> PlainFormatter.format(diff);
            case "json" -> JsonFormatter.format(diff);
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}
