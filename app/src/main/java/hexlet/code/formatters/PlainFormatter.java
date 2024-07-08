package hexlet.code.formatters;

import hexlet.code.DiffEntry;

import java.util.Map;
import java.util.List;

public class PlainFormatter {
    public static String format(Map<String, Object> diff) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Object> entry : diff.entrySet()) {
            String key = entry.getKey();
            DiffEntry diffEntry = (DiffEntry) entry.getValue();

            switch (diffEntry.getType()) {
                case DiffEntry.ADDED:
                    result.append("Property '").append(key).append("' was added with value: ")
                            .append(formatValue(diffEntry.getNewValue())).append("\n");
                    break;
                case DiffEntry.REMOVED:
                    result.append("Property '").append(key).append("' was removed\n");
                    break;
                case DiffEntry.CHANGED:
                    result.append("Property '").append(key).append("' was updated. From ")
                            .append(formatValue(diffEntry.getOldValue())).append(" to ")
                            .append(formatValue(diffEntry.getNewValue())).append("\n");
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
