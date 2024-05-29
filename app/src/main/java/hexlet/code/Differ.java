package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    public static Map<String, Object> parseJson(String filepath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        byte[] FileContent = Files.readAllBytes(Paths.get(filepath));
        return mapper.readValue(FileContent, Map.class);
    }
    public static String getData(String filepath1, String filepath2) throws Exception {
        Map<String, Object> map1 = parseJson(filepath1);
        Map<String, Object> map2 = parseJson(filepath2);

        Map<String, Object> SortedMap1 = new TreeMap<>(map1);
        Map<String, Object> SortedMap2 = new TreeMap<>(map2);


        StringBuilder result = new StringBuilder("{\n");

        for (String key : SortedMap1.keySet()) {
            if (!SortedMap2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(SortedMap1.get(key)).append("\n");
            } else if (!SortedMap1.get(key).equals(SortedMap2.get(key))) {
                result.append("  - ").append(key).append(": ").append(SortedMap1.get(key)).append("\n");
                result.append("  + ").append(key).append(": ").append(SortedMap2.get(key)).append("\n");
            } else {
                result.append("    ").append(key).append(": ").append(SortedMap1.get(key)).append("\n");
            }
        }

        for (String key : SortedMap2.keySet()) {
            if (!SortedMap1.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(SortedMap2.get(key)).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }

}
