package com.sauljohnson.humoresque.parser.model;

import com.sauljohnson.humoresque.parser.ParseException;
import com.sauljohnson.humoresque.parser.Token;
import com.sauljohnson.humoresque.parser.TokenStream;
import com.sauljohnson.humoresque.parser.TokenType;

/**
 * Represents a skip.
 *
 * @since 23/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
public class Skip implements ProgramComponent {

    private Token[] tokens;

    /**
     * Initializes a new instance of a skip.
     */
    private Skip() {
        tokens = new Token[] {};
    }

    /**
     * Gets the tokens comprising this skip.
     *
     * @return  the tokens.
     */
    public Token[] getTokens() {
        return tokens;
    }

    /**
     * @inheritDoc
     */
    public ProgramComponentType getProgramComponentType() {
        return ProgramComponentType.SKIP;
    }

    /**
     * Reads the tokens comprising a skip from the given token stream and returns the parsed result.
     *
     * @param tokenStream       the token stream to read from
     * @return                  the parsed result
     * @throws ParseException   if parsing fails
     */
    public static Skip parse(TokenStream tokenStream) throws ParseException {

        // TODO: This is very minimal, because no translation language currently requires this structure.

        // Create and return skip.
        Skip output = new Skip();
        output.tokens = tokenStream.readPast(TokenType.SKIP); // Read in skip.
        tokenStream.readExpecting(TokenType.PUNCTUATOR); // Discard punctuator.
        return output;
    }
}
