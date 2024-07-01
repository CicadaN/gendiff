package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> map1 = read(filepath1);
        Map<String, Object> map2 = read(filepath2);

        Map<String, Object> diff = Comparator.generateDiff(map1, map2);

        return Formatter.format(diff, format);
    }

    public static Map<String, Object> read(String filepath) throws IOException {
        String format = getDataFormat(filepath);
        String fileContent = Files.readString(getFullPath(filepath));
        return Parser.getParse(fileContent, format);
    }

    private static Path getFullPath(String filepath) {
        return Paths.get(filepath).toAbsolutePath().normalize();
    }

    public static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
}
