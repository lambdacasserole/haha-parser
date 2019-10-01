package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

/**
 * Represents an argument.
 *
 * @since 26/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class Argument {

    private String identifier;

    private HahaType type;

    /**
     * Initializes a new instance of an argument.
     */
    private Argument() {
        identifier  = null;
        type = null;
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
    public static Argument parse(TokenStream tokenStream) throws ParseException {

        // Extract identifier.
        String identifier = tokenStream.readExpecting(TokenType.IDENTIFIER).getText();

        // Discard colon.
        tokenStream.readExpecting(TokenType.COLON);

        // Extract type.
        HahaType type = HahaType.parse(tokenStream);

        // Create and return variable.
        Argument output = new Argument();
        output.identifier = identifier;
        output.type = type;
        return output;
    }
}
