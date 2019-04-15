import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.IOException;

public class CiphxorLauncher {

    @Option(name = "-c", forbids = {"-d"}, metaVar = "encryption", usage = "Encryption key")
    private String c;

    @Option(name = "-d", forbids = {"-c"}, metaVar = "decryption", usage = "Decryption key")
    private String d;

    @Argument(index = 0, required = true, metaVar = "InputName", usage = "Input file name")
    private String inputFileName;

    @Option(name = "-o", metaVar = "OutputName", usage = "Output file name")
    private String outputFileName;

    public static void main(String[] args) throws IOException {
        new CiphxorLauncher().launch(args);
    }

    private void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        String encryption;

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Expected arguments: [-c key] [-d key] inputname.txt [-o outputname.txt]");
            parser.printUsage(System.err);
            return;
        }

        if (c.isEmpty())
            encryption = d;
        else
            encryption = c;


        if (outputFileName == null || outputFileName.isEmpty()) {
            outputFileName = inputFileName;
            Ciphxor.encryption(encryption, inputFileName, outputFileName);
        } else
            Ciphxor.encryption(encryption, inputFileName, outputFileName);


    }
}
