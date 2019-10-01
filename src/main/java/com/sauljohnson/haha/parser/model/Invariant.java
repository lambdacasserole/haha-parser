package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

/**
 * Represents a loop invariant.
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class Invariant {

    private Token[] tokens;

    /**
     * Initializes a new instance of a loop invariant.
     */
    private Invariant() {
        tokens = new Token[] {};
    }

    /**
     * Gets the tokens comprising this loop invariant.
     *
     * @return  the tokens.
     */
    public Token[] getTokens() {
        return tokens;
    }

    /**
     * Reads the tokens comprising a loop invariant from the given token stream and returns the parsed result.
     *
     * @param tokenStream   the token stream to read from
     * @return              the parsed result
     */
    public static Invariant parse(TokenStream tokenStream) throws ParseException {

        // TODO: This is very minimal, because no translation language currently requires this structure.

        // Create and return postcondition.
        Invariant output = new Invariant();
        output.tokens = tokenStream.readPast(TokenType.PUNCTUATOR);
        return output;
    }
}
