package hexlet.code;

import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.getParse(filepath1);
        Map<String, Object> map2 = Parser.getParse(filepath2);

        Map<String, Object> diff = Comparator.generateDiff(map1, map2);

        return Formatter.format(diff, format);
    }
}
