package com.sauljohnson.humoresque.parser.model;

import com.sauljohnson.humoresque.parser.ParseException;
import com.sauljohnson.humoresque.parser.Token;
import com.sauljohnson.humoresque.parser.TokenStream;
import com.sauljohnson.humoresque.parser.TokenType;

/**
 * Represents an abstract assignment.
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"WeakerAccess", "unused"}) // API class.
public abstract class Assignment extends Statement {

    protected String identifier;

    protected Token[] expression;

    /**
     * Initialises a new instance of an assignment.
     */
    protected Assignment() {
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

        // Read in identifier.
        String identifier = tokenStream.readExpecting(TokenType.IDENTIFIER).getText();

        // Scalar assignment or array assignment?
        Assignment output = null;
        Token buffer = tokenStream.peekExpectingOneOf(new TokenType[] {TokenType.ASSIGNMENT, TokenType.OPEN_BRACKET});
        switch(buffer.getType()) {
            case ASSIGNMENT:
                output = ScalarAssignment.parse(identifier, tokenStream);
                break;
            case OPEN_BRACKET:
                output = ArrayAssignment.parse(identifier, tokenStream);
                break;
        }
        return output;
    }
}
