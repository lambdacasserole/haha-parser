package com.sauljohnson.humoresque.parser.model;

import com.sauljohnson.humoresque.parser.ParseException;
import com.sauljohnson.humoresque.parser.Token;
import com.sauljohnson.humoresque.parser.TokenStream;
import com.sauljohnson.humoresque.parser.TokenType;

/**
 * Represents a precondition.
 *
 * @since 18/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
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
     * @param tokenStream       the token stream to read from
     * @return                  the parsed result
     * @throws ParseException   if parsing fails
     */
    public static Precondition parse(TokenStream tokenStream) throws ParseException {

        // TODO: This is very minimal, because no translation language currently requires this structure.

        // Create and return precondition.
        Precondition output = new Precondition();
        output.tokens = tokenStream.readUpTo(TokenType.PUNCTUATOR);
        tokenStream.readExpecting(TokenType.PUNCTUATOR); // Discard punctuator.
        return output;
    }
}
