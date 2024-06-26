package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffEntry;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonFormatter {
    public static String format(Map<String, Object> diff) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        LinkedHashMap<String, Object> formattedDiff = new LinkedHashMap<>();

        for (Map.Entry<String, Object> entry : diff.entrySet()) {
            String key = entry.getKey();
            DiffEntry diffEntry = (DiffEntry) entry.getValue();

            LinkedHashMap<String, Object> entryMap = new LinkedHashMap<>();
            entryMap.put("type", diffEntry.getType());

            switch (diffEntry.getType()) {
                case DiffEntry.ADDED -> entryMap.put("value", diffEntry.getNewValue());
                case DiffEntry.REMOVED -> entryMap.put("value", diffEntry.getOldValue());
                case DiffEntry.CHANGED -> {
                    entryMap.put("oldValue", diffEntry.getOldValue());
                    entryMap.put("newValue", diffEntry.getNewValue());
                }
                case DiffEntry.UNCHANGED -> entryMap.put("value", diffEntry.getOldValue());
                default -> throw new IllegalArgumentException("Unknown diff type: " + diffEntry.getType());
            }

            formattedDiff.put(key, entryMap);
        }

        return objectMapper.writeValueAsString(formattedDiff);
    }
}
