package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

/**
 * Represents a variable.
 *
 * @since 19/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class Variable {

    private String identifier;

    private HahaType type;

    /**
     * Initializes a new instance of a variable.
     */
    private Variable() {
        identifier  = null;
        type = HahaType.UNKNOWN;
    }

    /**
     * Gets the variable identifier.
     *
     * @return  the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gets the variable type.
     *
     * @return  the type
     */
    public HahaType getType() {
        return type;
    }

    /**
     * Reads the tokens comprising a variable from the given token stream and returns the parsed result.
     *
     * @param tokenStream   the token stream to read from
     * @return              the parsed result
     */
    public static Variable parse(TokenStream tokenStream) {

        // Read program statement.
        Token[] statement = tokenStream.readUntil(TokenType.PUNCTUATOR);

        // TODO: Statement should be 3 tokens long exactly with identifier, colon and type. Punctuator? Arrays?

        // Extract identifier.
        String identifier = statement[0].getText();

        // Extract type.
        // TODO: Array types!
        HahaType type = HahaTypeHelper.parse(statement[2].getText());

        // Create and return variable.
        Variable output = new Variable();
        output.identifier  = identifier;
        output.type = type;
        return output;
    }
}
