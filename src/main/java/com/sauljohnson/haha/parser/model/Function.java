package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

import java.util.LinkedList;
import java.util.List;

public class Function {

    private String identifier;

    private Precondition[] preconditions;

    private Postcondition[] postconditions;

    private Argument[] arguments;

    private Variable[] variables;

    public static Function parse(TokenStream tokenStream) {

        List<Precondition> preconditionsList = new LinkedList<Precondition>();

        List<Postcondition> postconditionsList = new LinkedList<Postcondition>();

        List<Argument> argumentsList = new LinkedList<Argument>();

        List<Variable> variablesList = new LinkedList<Variable>();

        Token[] signature = tokenStream.readUntil(TokenType.PUNCTUATOR);
        // TODO: Parse signature.

        Token buffer;
        funcLoop:
        while ((buffer = tokenStream.peek()).getType() != TokenType.BLOCK_BEGIN) {
            switch(buffer.getType()) {
                case PRECONDITION:
                    preconditionsList.add(Precondition.parse(tokenStream));
                    break;
                case POSTCONDITION:
                    postconditionsList.add(Postcondition.parse(tokenStream));
                    break;
                case VAR:
                    tokenStream.read(); // Discard keyword.
                    while (tokenStream.peek().getType() == TokenType.IDENTIFIER) {
                        variablesList.add(Variable.parse(tokenStream));
                    }
                    break;
                case BLOCK_BEGIN:
                    // TODO: Parse function body.
                    break funcLoop; // Break out of loop completely.
                default:
                    tokenStream.read();
                    break;
            }
        }

        Function output = new Function();
        output.preconditions = preconditionsList.toArray(new Precondition[] {});
        output.postconditions = postconditionsList.toArray(new Postcondition[] {});
        output.arguments = argumentsList.toArray(new Argument[] {});
        output.variables = variablesList.toArray(new Variable[] {});
        return output;
    }
}
