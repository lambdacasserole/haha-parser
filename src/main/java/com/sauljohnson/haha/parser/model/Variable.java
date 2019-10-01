package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.TokenStream;

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
        type = null;
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
    public static Variable parse(TokenStream tokenStream) throws ParseException {

        // Extract identifier.
        String identifier = tokenStream.read().getText();

        // Extract type.
        HahaType type = HahaType.parse(tokenStream);

        // Create and return variable.
        Variable output = new Variable();
        output.identifier = identifier;
        output.type = type;
        return output;
    }
}
