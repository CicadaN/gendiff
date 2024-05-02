package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {
    @Option(names = { "-f", "--format" }, description = "output format [default: stylish]", paramLabel = "format")
    File file;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    File file1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    File file2;

    @Override
    public void run() {
        System.out.println("Hello World!");
    }
    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
}
