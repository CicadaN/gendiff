package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello World!");
    }
    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
}
