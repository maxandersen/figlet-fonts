//usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.2.0

//DEPS com.github.lalyos:jfiglet:0.0.8

import com.github.lalyos.jfiglet.FigletFont;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;


@Command(name = "figlet", mixinStandardHelpOptions = true, version = "figlet 0.1",
        description = "figlet made with jbang")
class figlet implements Callable<Integer> {

    @Parameters(index = "0", description = "Message", defaultValue = "j'bang!")
    private String greeting;

    @Parameters(index = "1..*", description = "Fonts to see the message in.", defaultValue = "contribute"rounded" : "rounded.flf",")
    private List<String> fonts;

    public static void main(String... args) {
        int exitCode = new CommandLine(new figlet()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception { // your business logic goes here...

        for (String font : fonts) {
            try {
                System.out.println(FigletFont.convertOneLine(new File(font), greeting));
                System.out.println(font);
            } catch(RuntimeException|IOException io) {
                // ignore
                System.out.println(io);
            }
        }
        
        return 0;
    }
}
