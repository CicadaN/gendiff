package hexlet.code.formatters;

import hexlet.code.DiffEntry;

import java.util.Map;

public class StylishFormatter {
    public static String format(Map<String, Object> diff) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map.Entry<String, Object> entry : diff.entrySet()) {
            String key = entry.getKey();
            DiffEntry diffEntry = (DiffEntry) entry.getValue();

            switch (diffEntry.getType()) {
                case DiffEntry.ADDED:
                    result.append("  + ").append(key).append(": ").
                            append(toString(diffEntry.getNewValue())).append("\n");
                    break;
                case DiffEntry.REMOVED:
                    result.append("  - ").append(key).append(": ").
                            append(toString(diffEntry.getOldValue())).append("\n");
                    break;
                case DiffEntry.CHANGED:
                    result.append("  - ").append(key).append(": ").
                            append(toString(diffEntry.getOldValue())).append("\n");
                    result.append("  + ").append(key).append(": ").
                            append(toString(diffEntry.getNewValue())).append("\n");
                    break;
                case DiffEntry.UNCHANGED:
                    result.append("    ").append(key).append(": ").
                            append(toString(diffEntry.getOldValue())).append("\n");
                    break;
                default:
                    throw new RuntimeException("Unknown status: " + diffEntry.getType());
            }
        }

        result.append("}");
        return result.toString();
    }

    private static String toString(Object value) {
        if (value == null) {
            return "null";
        }
        return value.toString();
    }
}
