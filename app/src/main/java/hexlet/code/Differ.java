package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    public static String getData(String filepath1, String filepath2) throws IOException {
        Map<String, Object> map1 = Parser.getParse(filepath1);
        Map<String, Object> map2 = Parser.getParse(filepath2);

        Map<String, Object> sortedMap1 = new TreeMap<>(map1);
        Map<String, Object> sortedMap2 = new TreeMap<>(map2);

        StringBuilder result = new StringBuilder("{\n");

        for (String key : sortedMap1.keySet()) {
            Object value1 = sortedMap1.get(key);
            Object value2 = sortedMap2.get(key);

            if (!sortedMap2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(value1).append("\n");
            } else if (value1 == null || value2 == null || !value1.equals(value2)) {
                result.append("  - ").append(key).append(": ").append(value1).append("\n");
                result.append("  + ").append(key).append(": ").append(value2).append("\n");
            } else {
                result.append("    ").append(key).append(": ").append(value1).append("\n");
            }
        }

        for (String key : sortedMap2.keySet()) {
            if (!sortedMap1.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(sortedMap2.get(key)).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }
}