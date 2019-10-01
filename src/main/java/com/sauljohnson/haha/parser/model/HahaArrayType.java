package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

/**
 * Represents an array type.
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class HahaArrayType extends HahaType {

    /**
     * Initialises a new instance of an array type.
     */
    private HahaArrayType() {
        super();
        this.isArrayType = true; // Set array type flag in superclass.
    }

    /**
     * Reads the tokens comprising an array type from the given token stream and returns the parsed result.
     *
     * @param tokenStream       the token stream to read from
     * @return                  the parsed result
     * @throws ParseException   if parsing fails
     */
    public static HahaArrayType parse(TokenStream tokenStream) throws ParseException {

        // We should have an array token, then a square bracket.
        tokenStream.readExpecting(TokenType.ARRAY);
        tokenStream.readExpecting(TokenType.OPEN_BRACKET);

        // Now comes the type.
        Token typeToken = tokenStream.readExpecting(TokenType.TYPE);

        // Now check the closing bracket.
        tokenStream.readExpecting(TokenType.CLOSE_BRACKET);

        // Create and return array type.
        HahaArrayType output = new HahaArrayType();
        output.baseType = HahaBaseTypeHelper.parse(typeToken.getText());
        return output;
    }
}
