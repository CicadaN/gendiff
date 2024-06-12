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
        byte[] fileContent = Files.readAllBytes(Paths.get(filepath));
        return mapper.readValue(fileContent, Map.class);
    }
    public static String getData(String filepath1, String filepath2) throws IOException {
        Map<String, Object> map1 = parseJson(filepath1);
        Map<String, Object> map2 = parseJson(filepath2);

        Map<String, Object> sortedMap1 = new TreeMap<>(map1);
        Map<String, Object> sortedMap2 = new TreeMap<>(map2);


        StringBuilder result = new StringBuilder("{\n");

        for (String key : sortedMap1.keySet()) {
            if (!sortedMap2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(sortedMap1.get(key)).append("\n");
            } else if (!sortedMap1.get(key).equals(sortedMap2.get(key))) {
                result.append("  - ").append(key).append(": ").append(sortedMap1.get(key)).append("\n");
                result.append("  + ").append(key).append(": ").append(sortedMap2.get(key)).append("\n");
            } else {
                result.append("    ").append(key).append(": ").append(sortedMap1.get(key)).append("\n");
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
