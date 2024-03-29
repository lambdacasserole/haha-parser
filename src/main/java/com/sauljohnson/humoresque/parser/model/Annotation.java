package com.sauljohnson.humoresque.parser.model;

import com.sauljohnson.humoresque.parser.ParseException;
import com.sauljohnson.humoresque.parser.Token;
import com.sauljohnson.humoresque.parser.TokenStream;
import com.sauljohnson.humoresque.parser.TokenType;

/**
 * Represents an annotation.
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class Annotation implements ProgramComponent {

    private Token[] tokens;

    /**
     * Initializes a new instance of an annotation.
     */
    private Annotation() {
        tokens = new Token[] {};
    }

    /**
     * Gets the tokens comprising this annotation.
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
        return ProgramComponentType.ANNOTATION;
    }

    /**
     * Reads the tokens comprising an annotation from the given token stream and returns the parsed result.
     *
     * @param tokenStream       the token stream to read from
     * @return                  the parsed result
     * @throws ParseException   if parsing fails
     */
    public static Annotation parse(TokenStream tokenStream) throws ParseException {

        // TODO: This is very minimal, because no translation language currently requires this structure.

        // Create and return annotation.
        Annotation output = new Annotation();
        output.tokens = tokenStream.readPast(TokenType.CLOSE_BRACE); // Read to closing brace.
        tokenStream.readExpecting(TokenType.PUNCTUATOR); // Discard punctuator.
        return output;
    }
}
