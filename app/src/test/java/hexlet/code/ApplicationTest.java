package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath);
    }
    @BeforeAll
    public static void beforeAll() throws Exception {
        resultStylish = readFixture("result-stylish.txt");
        resultPlain = readFixture("result-plain.txt");
        resultJson = readFixture("result-json.txt");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        // Тестируем вызов метода с каждым из фоматерров, а также вызов с форматером по умолчанию
        assertEquals(Differ.generate(filePath1, filePath2, "stylish"), resultStylish);
        assertEquals(Differ.generate(filePath1, filePath2, "json"), resultJson);
        assertEquals(Differ.generate(filePath1, filePath2, "plain"), resultPlain);
        assertEquals(Differ.generate(filePath1, filePath2), resultStylish);
    }

//    @Test
//    public void testJsonStylish() throws Exception {
//        String filePath1 = getFixturePath("file1.json").toString();
//        String filePath2 = getFixturePath("file2.json").toString();
//
//        String actual = Differ.generate(filePath1, filePath2, "stylish");
//        assertEquals(resultStylish, actual);
//    }
//
//    @Test
//    public void testJsonPlain() throws Exception {
//        String filePath1 = getFixturePath("file1.json").toString();
//        String filePath2 = getFixturePath("file2.json").toString();
//
//        String actual = Differ.generate(filePath1, filePath2, "plain");
//        assertEquals(resultPlain, actual);
//    }
//
//    @Test
//    public void testJsonJson() throws Exception {
//        String filePath1 = getFixturePath("file1.json").toString();
//        String filePath2 = getFixturePath("file2.json").toString();
//
//        String actual = Differ.generate(filePath1, filePath2, "json");
//        assertEquals(resultJson, actual);
//    }
//
//    @Test
//    public void testYamlStylish() throws Exception {
//        String filePath1 = getFixturePath("file1.yml").toString();
//        String filePath2 = getFixturePath("file2.yml").toString();
//
//        String actual = Differ.generate(filePath1, filePath2, "stylish");
//        assertEquals(resultStylish, actual);
//    }
//
//    @Test
//    public void testYamlPlain() throws Exception {
//        String filePath1 = getFixturePath("file1.yml").toString();
//        String filePath2 = getFixturePath("file2.yml").toString();
//
//        String actual = Differ.generate(filePath1, filePath2, "plain");
//        assertEquals(resultPlain, actual);
//    }
//
//    @Test
//    public void testYamlJson() throws Exception {
//        String filePath1 = getFixturePath("file1.yml").toString();
//        String filePath2 = getFixturePath("file2.yml").toString();
//
//        String actual = Differ.generate(filePath1, filePath2, "json");
//        assertEquals(resultJson, actual);
//    }
//
//    @Test
//    public void testDefaultFormat() throws Exception {
//        String filePath1 = getFixturePath("file1.json").toString();
//        String filePath2 = getFixturePath("file2.json").toString();
//
//        String actual = Differ.generate(filePath1, filePath2);
//        assertEquals(resultStylish, actual);
//    }

}
