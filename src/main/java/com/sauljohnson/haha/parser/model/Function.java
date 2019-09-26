package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a function.
 *
 * @since 18/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class Function {

    private String identifier;

    private Precondition[] preconditions;

    private Postcondition[] postconditions;

    private Argument[] arguments;

    private Variable[] variables;

    private Token[][] statements;

    private HahaType returnType;

    /**
     * Gets the function identifier.
     *
     * @return  the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gets any preconditions associated with the function.
     *
     * @return  the identifier
     */
    public Precondition[] getPreconditions() {
        return preconditions;
    }

    /**
     * Gets any postconditions associated with the function.
     *
     * @return  the identifier
     */
    public Postcondition[] getPostconditions() {
        return postconditions;
    }

    /**
     * Gets any arguments to the function.
     *
     * @return  the identifier
     */
    public Argument[] getArguments() {
        return arguments;
    }

    /**
     * Gets any variables used within the function.
     *
     * @return  the identifier
     */
    public Variable[] getVariables() {
        return variables;
    }

    /**
     * Gets any variables used within the function.
     *
     * @return  the identifier
     */
    public Token[][] getStatements() {
        return statements;
    }

    /**
     * Gets the return type of the function.
     *
     * @return  the identifier
     */
    public HahaType getReturnType() {
        return returnType;
    }

    /**
     * Reads the tokens comprising a function from the given token stream and returns the parsed result.
     *
     * @param tokenStream   the token stream to read from
     * @return              the parsed result
     */
    public static Function parse(TokenStream tokenStream) {

        // Information to capture from the token stream.
        String identifier;
        List<Precondition> preconditionsList = new LinkedList<Precondition>();
        List<Postcondition> postconditionsList = new LinkedList<Postcondition>();
        List<Argument> argumentsList = new LinkedList<Argument>();
        List<Variable> variablesList = new LinkedList<Variable>();
        List<Token[]> statementsList = new LinkedList<Token[]>();
        HahaType returnType;

        // Parse signature.
        Token[] signature = tokenStream.readUntil(TokenType.PUNCTUATOR);
        TokenStream signatureStream = new TokenStream(signature); // Open substream for signature.
        Token[] buffer = signatureStream.readUntil(TokenType.OPEN_PARENTHESIS);
        identifier = buffer[1].getText(); // TODO: Hard-coded index.
        while (signatureStream.peek().getType() == TokenType.IDENTIFIER) { // Consume all arguments.
            Argument argument = Argument.parse(signatureStream);
            argumentsList.add(argument);
        }
        // TODO: We should just have a colon and a return type now.
        returnType = HahaTypeHelper.parse(signature[signature.length - 1].getText()); // TODO: Hard-coded index.

        // Read preconditions, postconditions and variables.
        Token next;
        while ((next = tokenStream.peek()).getType() != TokenType.BLOCK_BEGIN) {
            switch(next.getType()) {
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
                default:
                    tokenStream.read();
                    break;
            }
        }

        // TODO: We should now be at a block begin.

        // Read in function body, being sensitive to blocks.
        int level = 0; // Track nesting level.
        do {
            Token[] buf = tokenStream.readUntil(TokenType.PUNCTUATOR);
            statementsList.add(buf);
            if (buf.length > 0) {
                if (buf[0].getType() == TokenType.BLOCK_END) {
                    level--;
                } else if (buf[0].getType() == TokenType.BLOCK_BEGIN) {
                    level++;
                }
            }
        } while (level > 0);

        // Create and return function.
        Function output = new Function();
        output.identifier = identifier;
        output.preconditions = preconditionsList.toArray(new Precondition[] {});
        output.postconditions = postconditionsList.toArray(new Postcondition[] {});
        output.arguments = argumentsList.toArray(new Argument[] {});
        output.variables = variablesList.toArray(new Variable[] {});
        output.statements = statementsList.toArray(new Token[][] {});
        output.returnType = returnType;
        return output;
    }
}
