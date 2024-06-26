package hexlet.code;

import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedHashMap;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.getParse(filepath1);
        Map<String, Object> map2 = Parser.getParse(filepath2);

        Map<String, Object> diff = generateDiff(map1, map2);

        return Formatter.format(diff, format);
    }

    private static Map<String, Object> generateDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> diff = new LinkedHashMap<>();
        TreeMap<String, Object> allKeys = new TreeMap<>(map1);
        allKeys.putAll(map2);

        for (String key : allKeys.keySet()) {
            if (!map1.containsKey(key)) {
                diff.put(key, new DiffEntry(DiffEntry.ADDED, null, map2.get(key)));
            } else if (!map2.containsKey(key)) {
                diff.put(key, new DiffEntry(DiffEntry.REMOVED, map1.get(key), null));
            } else if (!isEqual(map1.get(key), map2.get(key))) {
                diff.put(key, new DiffEntry(DiffEntry.CHANGED, map1.get(key), map2.get(key)));
            } else {
                diff.put(key, new DiffEntry(DiffEntry.UNCHANGED, map1.get(key), map2.get(key)));
            }
        }

        return diff;
    }

    private static boolean isEqual(Object value1, Object value2) {
        if (value1 == null) {
            return value2 == null;
        }
        return value1.equals(value2);
    }
}
