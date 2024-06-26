package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {

    @Test
    public void firstTest() throws Exception {
        String filePatch1 = "./src/test/resources/file1.json";
        String filePatch2 = "./src/test/resources/file2.json";

        String expected = "{\n"
                + "  - follow: false \n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        String actual = Differ.generate(filePatch1, filePatch2);
        assertEquals(expected.trim().replaceAll("\\s+", " "), actual.trim().replaceAll("\\s+", " "));
    }

    @Test
    public void yamlTest() throws Exception {
        String filePatch1 = "./src/test/resources/testFileYaml1.yaml";
        String filePatch2 = "./src/test/resources/testFileYaml2.yaml";

        String expected = "{\n"
                + "  - default: null\n"
                + "  + default: not_null\n"
                + "    school: hexlet\n"
                + "}\n";
        String actual = Differ.generate(filePatch1, filePatch2);
        assertEquals(expected.trim().replaceAll("\\s+", " "), actual.trim().replaceAll("\\s+", " "));
    }

    @Test
    public void testDoubleStructure() throws Exception {
        String filePatch1 = "./src/test/resources/testFormater1.json";
        String filePatch2 = "./src/test/resources/testFormater2.json";

        String expected = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}\n";
        String actual = Differ.generate(filePatch1, filePatch2);
        assertEquals(expected.trim().replaceAll("\\s+", " "), actual.trim().replaceAll("\\s+", " "));
    }

    @Test
    public void testPlainFormat() throws Exception {
        String filePatch1 = "./src/test/resources/testFormater1.json";
        String filePatch2 = "./src/test/resources/testFormater2.json";
        String formatName = "plain";

        String expected = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'\n";
        String actual = Differ.generate(filePatch1, filePatch2, formatName);
        assertEquals(expected.trim().replaceAll("\\s+", " "), actual.trim().replaceAll("\\s+", " "));
    }

    @Test
    public void testJsonFormat() throws Exception {
        String filePatch1 = "./src/test/resources/file1.json";
        String filePatch2 = "./src/test/resources/file2.json";
        String formatName = "json";

        String expected = "{\"follow\":{\"type\":\"REMOVED\",\"value\":false},\"host\":{\"type\":\"UNCHANGED\",\"value\":\"hexlet.io\"},\"proxy\":{\"type\":\"REMOVED\",\"value\":\"123.234.53.22\"},\"timeout\":{\"type\":\"CHANGED\",\"oldValue\":50,\"newValue\":20},\"verbose\":{\"type\":\"ADDED\",\"value\":true}}";
        String actual = Differ.generate(filePatch1, filePatch2, formatName);

        ObjectMapper objectMapper = new ObjectMapper();
        assertEquals(objectMapper.readTree(expected), objectMapper.readTree(actual));
    }

}
