package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format [default: stylish]", defaultValue = "stylish")
    private String format;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String file1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String file2;

    @Override
    public Integer call() throws Exception {
        String diff = Differ.getData(file1, file2, format);
        System.out.println(diff);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}