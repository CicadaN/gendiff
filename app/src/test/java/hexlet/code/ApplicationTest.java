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
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}\n";
        String actual = Differ.getData(filePatch1, filePatch2);
        assertEquals(expected, actual);
    }
}

