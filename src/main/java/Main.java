import com.sauljohnson.haha.parser.HahaTokenizer;
import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.Tokenizer;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String str = FileUtils.readFileToString(new File(""));
            Tokenizer ss= new HahaTokenizer();
            Token[] ssg = ss.tokenize(str);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
