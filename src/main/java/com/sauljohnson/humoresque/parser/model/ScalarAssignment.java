package com.sauljohnson.humoresque.parser.model;

import com.sauljohnson.humoresque.parser.ParseException;
import com.sauljohnson.humoresque.parser.Token;
import com.sauljohnson.humoresque.parser.TokenStream;
import com.sauljohnson.humoresque.parser.TokenType;

/**
 * Represents an assignment to a scalar value.
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class ScalarAssignment extends Assignment {

    /**
     * Initialises a new instance of an assignment to a scalar value.
     */
    private ScalarAssignment() {
        super();
    }

    /**
     * Reads the tokens comprising a scalar assignment from the given token stream and returns the parsed result.
     *
     * @param identifier        the identifier of the variable being assigned to
     * @param tokenStream       the token stream to read from
     * @return                  the parsed result
     * @throws ParseException   if parsing fails
     */
    public static ScalarAssignment parse(String identifier, TokenStream tokenStream) throws ParseException {

        // Identifier has already been read by superclass!

        // Assignment operator next.
        tokenStream.readExpecting(TokenType.ASSIGNMENT);

        // Read the rest of the expression.
        Token[] expression = tokenStream.readUpTo(TokenType.PUNCTUATOR);

        // Discard punctuator.
        tokenStream.readExpecting(TokenType.PUNCTUATOR);

        // Create and return assignment.
        ScalarAssignment output = new ScalarAssignment();
        output.identifier = identifier;
        output.expression = expression;
        return output;
    }
}
