package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

public class Assignment extends Statement {

    private String identifier;

    private Token[] expression;

    private Assignment() {
        identifier = null;
        expression = new Token[] {};
    }

    public String getIdentifier() {
        return identifier;
    }

    public Token[] getExpression() {
        return expression;
    }

    public static Assignment parse(TokenStream tokenStream) throws ParseException {

        // Read identifier.
        Token id = tokenStream.readExpecting(TokenType.IDENTIFIER);
        String identifier = id.getText();

        // Read the rest of the expression.
        Token[] expression = tokenStream.readPast(TokenType.PUNCTUATOR);

        // Create and return assignment.
        Assignment output = new Assignment();
        output.identifier = identifier;
        output.expression = expression;
        return output;
    }
}
