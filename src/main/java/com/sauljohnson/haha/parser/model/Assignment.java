package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

/**
 * Represents an assignment.
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class Assignment extends Statement {

    private String identifier;

    private Token[] expression;

    /**
     * Initialises a new instance of an assignment.
     */
    private Assignment() {
        identifier = null;
        expression = new Token[] {};
    }

    /**
     * Gets the identifier of the variable to which the assignment is happening.
     *
     * @return  the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gets the expression on the right-hand side of the assignment.
     *
     * @return  the expression as an array of tokens
     */
    public Token[] getExpression() {
        return expression;
    }

    /**
     * Reads the tokens comprising an assignment from the given token stream and returns the parsed result.
     *
     * @param tokenStream       the token stream to read from
     * @return                  the parsed result
     * @throws ParseException   if parsing fails
     */
    public static Assignment parse(TokenStream tokenStream) throws ParseException {

        // Read identifier.
        Token id = tokenStream.readExpecting(TokenType.IDENTIFIER);
        String identifier = id.getText();

        // Read the rest of the expression.
        Token[] expression = tokenStream.readUpTo(TokenType.PUNCTUATOR);

        // Discard punctuator.
        tokenStream.readExpecting(TokenType.PUNCTUATOR);

        // Create and return assignment.
        Assignment output = new Assignment();
        output.identifier = identifier;
        output.expression = expression;
        return output;
    }
}
