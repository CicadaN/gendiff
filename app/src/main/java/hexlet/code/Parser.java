package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseJson(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }

    public static Map<String, Object> parseYaml(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(content, Map.class);
    }

    public static Map<String, Object> getParse(String content, String format) throws IOException {
        if (format.equals("json")) {
            return parseJson(content);
        } else if (format.equals("yaml") || format.equals("yml")) {
            return parseYaml(content);
        } else {
            throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }

}
