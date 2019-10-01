import com.sauljohnson.haha.parser.*;
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
            TokenStreamTransformer streamTransformer = new ConsecutiveTokenFilter(TokenType.PUNCTUATOR);
            TokenStream ssd = streamTransformer.transform(new TokenStream(ssg));
            Program ppp = Program.parse(streamTransformer.transform(new TokenStream(ssg)));
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TokenizationException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
