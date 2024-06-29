package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Comparator {
    static Map<String, Object> generateDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> diff = new LinkedHashMap<>();
        TreeMap<String, Object> allKeys = new TreeMap<>(map1);
        allKeys.putAll(map2);

        for (String key : allKeys.keySet()) {
            if (!map1.containsKey(key)) {
                diff.put(key, new DiffEntry(DiffEntry.ADDED, null, map2.get(key)));
            } else if (!map2.containsKey(key)) {
                diff.put(key, new DiffEntry(DiffEntry.REMOVED, map1.get(key), null));
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                diff.put(key, new DiffEntry(DiffEntry.CHANGED, map1.get(key), map2.get(key)));
            } else {
                diff.put(key, new DiffEntry(DiffEntry.UNCHANGED, map1.get(key), map2.get(key)));
            }
        }

        return diff;
    }


}
