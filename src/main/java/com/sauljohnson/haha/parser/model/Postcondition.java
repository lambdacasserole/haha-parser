package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

/**
 * Represents a postcondition.
 *
 * @since 18/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class Postcondition {

    private Token[] tokens;

    /**
     * Initializes a new instance of a postcondition.
     */
    private Postcondition() {
        tokens = new Token[] {};
    }

    /**
     * Gets the tokens comprising this postcondition.
     *
     * @return  the tokens.
     */
    public Token[] getTokens() {
        return tokens;
    }

    /**
     * Reads the tokens comprising a postcondition from the given token stream and returns the parsed result.
     *
     * @param tokenStream   the token stream to read from
     * @return              the parsed result
     */
    public static Postcondition parse(TokenStream tokenStream) throws ParseException {

        // TODO: This is very minimal, because no translation language currently requires this structure.

        // Create and return postcondition.
        Postcondition output = new Postcondition();
        output.tokens = tokenStream.readUntil(TokenType.PUNCTUATOR);
        return output;
    }
}
