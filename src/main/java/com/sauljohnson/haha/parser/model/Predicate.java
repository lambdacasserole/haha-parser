package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

/**
 * Represents a predicate.
 *
 * @since 18/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class Predicate {

    private Token[] tokens;

    /**
     * Initializes a new instance of a predicate.
     */
    private Predicate() {
        tokens = new Token[] {};
    }

    /**
     * Gets the tokens comprising this predicate.
     *
     * @return  the tokens.
     */
    public Token[] getTokens() {
        return tokens;
    }

    /**
     * Reads the tokens comprising a predicate from the given token stream and returns the parsed result.
     *
     * @param tokenStream   the token stream to read from
     * @return              the parsed result
     */
    public static Predicate parse(TokenStream tokenStream) {

        // TODO: This is very minimal, because no translation language currently requires this structure.

        // Create and return predicate.
        Predicate output = new Predicate();
        output.tokens = tokenStream.readUntil(TokenType.PUNCTUATOR);
        return output;
    }
}
