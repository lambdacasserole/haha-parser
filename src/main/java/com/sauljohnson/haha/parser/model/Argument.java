package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

/**
 * Represents an argument.
 *
 * @since 26/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
public class Argument {

    private String identifier;

    private HahaType type;

    /**
     * Initializes a new instance of an argument.
     */
    private Argument() {
        identifier  = null;
        type = HahaType.UNKNOWN;
    }

    /**
     * Gets the argument identifier.
     *
     * @return  the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gets the argument type.
     *
     * @return  the type
     */
    public HahaType getType() {
        return type;
    }

    /**
     * Reads the tokens comprising an argument from the given token stream and returns the parsed result.
     *
     * @param tokenStream   the token stream to read from
     * @return              the parsed result
     */
    public static Argument parse(TokenStream tokenStream) {

        // Read until comma or closing parenthesis.
        Token[] tokens = tokenStream.readUntilAnyOf(new TokenType[] {TokenType.COMMA, TokenType.CLOSE_PARENTHESIS});

        // TODO: Tokens should be 3 tokens long exactly with identifier, colon and type. Punctuator? Arrays?

        // Extract identifier.
        String identifier = tokens[0].getText();

        // Extract type.
        // TODO: Array types!
        HahaType type = HahaTypeHelper.parse(tokens[2].getText());

        // Create and return variable.
        Argument output = new Argument();
        output.identifier = identifier;
        output.type = type;
        return output;
    }
}
