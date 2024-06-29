package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseJson(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        byte[] fileContent = Files.readAllBytes(Paths.get(content));
        return mapper.readValue(fileContent, Map.class);
    }

    public static Map<String, Object> parseYaml(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        byte[] fileContent = Files.readAllBytes(Paths.get(content));
        return mapper.readValue(fileContent, Map.class);
    }

    public static Map<String, Object> getParse(String filepath) throws IOException {
        String extension = "";
        int i = filepath.lastIndexOf('.');
        if (i > 0) {
            extension = filepath.substring(i + 1);
        }
        if (extension.equals("json")) {
            return parseJson(filepath);
        } else if (extension.equals("yaml") || extension.equals("yml")) {
            return parseYaml(filepath);
        } else {
            throw new IllegalArgumentException("Unsupported format: " + extension);
        }
    }

}
