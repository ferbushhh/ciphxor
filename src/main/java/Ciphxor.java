import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ciphxor {

    public static void encryption(String encryption, String inputFileName, String outputFileName) throws IOException {
        if (!test(encryption)) throw new IllegalArgumentException("This is not a hexadecimal system");

        BufferedReader reader = new BufferedReader(new FileReader(new File(inputFileName)));

        String name = String.format("%s", outputFileName);
        FileWriter file = new FileWriter(name);

        int st;
        int index = 0;
        int size = encryption.length();

        while ((st = reader.read()) != -1) {
            char chr = encryption.charAt(index);
            int charInt;

            if (chr >= 'a') {
                charInt = (chr - 'a' + 10);
            } else {
                charInt = Integer.parseInt(String.valueOf(chr));
            }

            file.write((char)(st^charInt));

            if (index == size - 1)
                index = 0;
            else index++;
        }
        file.close();
        reader.close();
    }

    public static boolean test(String testString){
        Pattern p = Pattern.compile("([a-f0-9])+");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

}
