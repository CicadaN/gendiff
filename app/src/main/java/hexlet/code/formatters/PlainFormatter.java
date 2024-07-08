package hexlet.code.formatters;

import hexlet.code.DiffEntry;

import java.util.Map;
import java.util.List;
import java.util.StringJoiner;

public class PlainFormatter {
    public static String format(Map<String, Object> diff) {
        StringJoiner result = new StringJoiner("\n");

        for (Map.Entry<String, Object> entry : diff.entrySet()) {
            String key = entry.getKey();
            DiffEntry diffEntry = (DiffEntry) entry.getValue();

            switch (diffEntry.getType()) {
                case DiffEntry.ADDED:
                    result.add("Property '" + key + "' was added with value: "
                            + formatValue(diffEntry.getNewValue()));
                    break;
                case DiffEntry.REMOVED:
                    result.add("Property '" + key + "' was removed");
                    break;
                case DiffEntry.CHANGED:
                    result.add("Property '" + key + "' was updated. From "
                            + formatValue(diffEntry.getOldValue()) + " to "
                            + formatValue(diffEntry.getNewValue()));
                    break;
                case DiffEntry.UNCHANGED:
                    break;
                default:
                    throw new RuntimeException("Unknown status: " + diffEntry.getType());
            }
        }

        return result.toString();
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        return value.toString();
    }
}
