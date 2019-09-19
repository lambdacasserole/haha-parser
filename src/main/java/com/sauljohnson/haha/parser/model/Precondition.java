package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

/**
 * Represents a precondition.
 *
 * @since 18/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"WeakerAccess", "unused"}) // API class.
public class Precondition {

    private Token[] tokens;

    /**
     * Initializes a new instance of a precondition.
     */
    private Precondition() {
        tokens = new Token[] {};
    }

    /**
     * Gets the tokens comprising this precondition.
     *
     * @return  the tokens.
     */
    public Token[] getTokens() {
        return tokens;
    }

    /**
     * Reads the tokens comprising a precondition from the given token stream and returns the parsed result.
     *
     * @param tokenStream   the token stream to read from
     * @return              the parsed result
     */
    public static Precondition parse(TokenStream tokenStream) {

        // TODO: This is very minimal, because no translation language currently requires this structure.

        // Create and return precondition.
        Precondition output = new Precondition();
        output.tokens = tokenStream.readUntil(TokenType.PUNCTUATOR);
        return output;
    }
}
