package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {

    @Test
    public void firstTest() throws IOException {
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
        String actual = Differ.getData(filePatch1, filePatch2);
        assertEquals(expected.trim().replaceAll("\\s+", " "), actual.trim().replaceAll("\\s+", " "));
    }

    @Test
    public void yamlTest() throws IOException {
        String filePatch1 = "./src/test/resources/testFileYaml1.yaml";
        String filePatch2 = "./src/test/resources/testFileYaml2.yaml";

        String expected = "{\n" +
                "  - default: null\n" +
                "  + default: not_null\n" +
                "    school: hexlet\n" +
                "}\n";
        String actual = Differ.getData(filePatch1, filePatch2);
        assertEquals(expected.trim().replaceAll("\\s+", " "), actual.trim().replaceAll("\\s+", " "));
    }

    @Test
    public void testDoubleStructure() throws IOException {
        String filePatch1 = "./src/test/resources/testFormater1.json";
        String filePatch2 = "./src/test/resources/testFormater2.json";

        String expected = "{\n" +
                "    chars1: [a, b, c]\n" +
                "  - chars2: [d, e, f]\n" +
                "  + chars2: false\n" +
                "  - checked: false\n" +
                "  + checked: true\n" +
                "  - default: null\n" +
                "  + default: [value1, value2]\n" +
                "  - id: 45\n" +
                "  + id: null\n" +
                "  - key1: value1\n" +
                "  + key2: value2\n" +
                "    numbers1: [1, 2, 3, 4]\n" +
                "  - numbers2: [2, 3, 4, 5]\n" +
                "  + numbers2: [22, 33, 44, 55]\n" +
                "  - numbers3: [3, 4, 5]\n" +
                "  + numbers4: [4, 5, 6]\n" +
                "  + obj1: {nestedKey=value, isNested=true}\n" +
                "  - setting1: Some value\n" +
                "  + setting1: Another value\n" +
                "  - setting2: 200\n" +
                "  + setting2: 300\n" +
                "  - setting3: true\n" +
                "  + setting3: none\n" +
                "}\n";
        String actual = Differ.getData(filePatch1, filePatch2);
        assertEquals(expected.trim().replaceAll("\\s+", " "), actual.trim().replaceAll("\\s+", " "));
    }

//    @Test
//    public void unsupportedFormat() throws IOException {
//        String filePatch1 = "./src/test/resources/testFileYaml1.txt";
//        String filePatch2 = "./src/test/resources/testFileYaml2.txt";
//
//
//    }
}

