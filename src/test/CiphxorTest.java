import org.junit.Assert;
import org.junit.Test;
import java.io.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class CiphxorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    static boolean equals(File input1, File input2) throws IOException {
        BufferedReader readerInput1 = new BufferedReader(new FileReader(input1));
        BufferedReader readerInput2 = new BufferedReader(new FileReader(input2));

        String s1 = readerInput1.readLine();
        String s2 = readerInput2.readLine();

        while (true)
        {
            if (s1 == null && s2 == null)
                break;
            else if ((!s1.equals(s2)) || ((s1 == null && s2 != null) || (s2 == null && s1 != null)))
                return false;
            else {
                s1 = readerInput1.readLine();
                s2 = readerInput2.readLine();
            }
        }
        return true;
    }

    @Test
    public void encryption() {
        try {
            //1
            expectedException.expect(IllegalArgumentException.class); //проверка на шестнадцатиричную систему счисления
            expectedException.expectMessage("This is not a hexadecimal system");
            Ciphxor.encryption("hac1", "input.txt", "output.txt");
            //2
            Ciphxor.encryption("ab0945efffcba", "input2.txt", "output2.txt"); //шифруем файл
            Assert.assertTrue(CiphxorTest.equals(new File("output2.txt"), new File ("test1.txt"))); //проверяем, правильно ли зашифровал
            Ciphxor.encryption("ab0945efffcba", "output2.txt", "input1.txt"); //расшифровываем файл этим же ключом
            Assert.assertTrue(CiphxorTest.equals(new File("input2.txt"), new File ("input1.txt"))); //сравниваем полученный результат

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
