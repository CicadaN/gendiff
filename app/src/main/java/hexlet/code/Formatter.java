package hexlet.code;

import java.util.Map;

public class Formatter {
    public static String format(Map<String, Object> diff, String format) {
        if ("stylish".equals(format)) {
            return stylish(diff);
        }
        throw new IllegalArgumentException("Unsupported format: " + format);
    }

    private static String stylish(Map<String, Object> diff) {
        StringBuilder result = new StringBuilder("{\n");

        for (Map.Entry<String, Object> entry : diff.entrySet()) {
            String key = entry.getKey();
            DiffEntry diffEntry = (DiffEntry) entry.getValue();

            switch (diffEntry.getType()) {
                case ADDED:
                    result.append("  + ").append(key).append(": ").append(toString(diffEntry.getNewValue())).append("\n");
                    break;
                case REMOVED:
                    result.append("  - ").append(key).append(": ").append(toString(diffEntry.getOldValue())).append("\n");
                    break;
                case CHANGED:
                    result.append("  - ").append(key).append(": ").append(toString(diffEntry.getOldValue())).append("\n");
                    result.append("  + ").append(key).append(": ").append(toString(diffEntry.getNewValue())).append("\n");
                    break;
                case UNCHANGED:
                    result.append("    ").append(key).append(": ").append(toString(diffEntry.getOldValue())).append("\n");
                    break;
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