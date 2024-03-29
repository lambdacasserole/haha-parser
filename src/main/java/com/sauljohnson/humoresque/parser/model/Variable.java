package com.sauljohnson.humoresque.parser.model;

import com.sauljohnson.humoresque.parser.ParseException;
import com.sauljohnson.humoresque.parser.TokenStream;
import com.sauljohnson.humoresque.parser.TokenType;

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
     * @param tokenStream       the token stream to read from
     * @return                  the parsed result
     * @throws ParseException   if parsing fails
     */
    public static Variable parse(TokenStream tokenStream) throws ParseException {

        // Extract identifier.
        String identifier = tokenStream.readExpecting(TokenType.IDENTIFIER).getText();

        // Discard colon.
        tokenStream.readExpecting(TokenType.COLON);

        // Extract type.
        HahaType type = HahaType.parse(tokenStream);

        // Discard punctuator.
        tokenStream.readExpecting(TokenType.PUNCTUATOR);

        // Create and return variable.
        Variable output = new Variable();
        output.identifier = identifier;
        output.type = type;
        return output;
    }
}
