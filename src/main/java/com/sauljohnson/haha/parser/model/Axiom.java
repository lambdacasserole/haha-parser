package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

/**
 * Represents an axiom.
 *
 * @since 18/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"WeakerAccess", "unused"}) // API class.
public class Axiom {

    private Token[] tokens;

    /**
     * Initializes a new instance of an axiom.
     */
    private Axiom() {
        tokens = new Token[] {};
    }

    /**
     * Gets the tokens comprising this axiom.
     *
     * @return  the tokens
     */
    public Token[] getTokens() {
        return tokens;
    }

    /**
     * Reads the tokens comprising an axiom from the given token stream and returns the parsed result.
     *
     * @param tokenStream   the token stream to read from
     * @return              the parsed result
     */
    public static Axiom parse(TokenStream tokenStream) {

        // TODO: This is very minimal, because no translation language currently requires this structure.

        // Create and return axiom.
        Axiom output = new Axiom();
        output.tokens = tokenStream.readUntil(TokenType.PUNCTUATOR);
        return output;
    }
}
