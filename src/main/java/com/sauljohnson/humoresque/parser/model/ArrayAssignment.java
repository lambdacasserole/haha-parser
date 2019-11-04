package com.sauljohnson.humoresque.parser.model;

import com.sauljohnson.humoresque.parser.ParseException;
import com.sauljohnson.humoresque.parser.Token;
import com.sauljohnson.humoresque.parser.TokenStream;
import com.sauljohnson.humoresque.parser.TokenType;

/**
 * Represents an assignment to an array index.
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class ArrayAssignment extends Assignment {

    private Token[] index;

    /**
     * Initialises a new instance of an assignment to an array index.
     */
    private ArrayAssignment() {
        super(true);
        index = new Token[] {};
    }

    /**
     * Gets the array index being assigned to.
     *
     * @return  the array index
     */
    public Token[] getIndex() {
        return index;
    }

    /**
     * Reads the tokens comprising an array assignment from the given token stream and returns the parsed result.
     *
     * @param identifier        the identifier of the variable being assigned to
     * @param tokenStream       the token stream to read from
     * @return                  the parsed result
     * @throws ParseException   if parsing fails
     */
    public static ArrayAssignment parse(String identifier, TokenStream tokenStream) throws ParseException {

        // Identifier has already been read by superclass!

        // Read array index.
        tokenStream.readExpecting(TokenType.OPEN_BRACKET);
        Token[] index = tokenStream.readUpTo(TokenType.CLOSE_BRACKET);
        tokenStream.readExpecting(TokenType.CLOSE_BRACKET);

        // Assignment operator next.
        tokenStream.readExpecting(TokenType.ASSIGNMENT);

        // Read the rest of the expression.
        Token[] expression = tokenStream.readUpTo(TokenType.PUNCTUATOR);

        // Discard punctuator.
        tokenStream.readExpecting(TokenType.PUNCTUATOR);

        // Create and return assignment.
        ArrayAssignment output = new ArrayAssignment();
        output.identifier = identifier;
        output.index = index;
        output.expression = expression;
        return output;
    }
}
