import com.sauljohnson.haha.parser.HahaTokenizer;
import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.Tokenizer;
import com.sauljohnson.haha.parser.model.Program;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String str = FileUtils.readFileToString(new File(""));
            Tokenizer ss= new HahaTokenizer();
            Token[] ssg = ss.tokenize(str);
            Program ppp = Program.parse(new TokenStream(ssg));
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
