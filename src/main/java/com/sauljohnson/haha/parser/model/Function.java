package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
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
    public static Function parse(TokenStream tokenStream) throws ParseException {

        // Information to capture from the token stream.
        String identifier;
        List<Precondition> preconditionsList = new LinkedList<Precondition>();
        List<Postcondition> postconditionsList = new LinkedList<Postcondition>();
        List<Argument> argumentsList = new LinkedList<Argument>();
        List<Variable> variablesList = new LinkedList<Variable>();
        List<Token[]> statementsList = new LinkedList<Token[]>();
        HahaType returnType;

        // Read in function keyword.
        tokenStream.readExpecting(TokenType.FUNCTION);

        // Now comes the identifier.
        identifier = tokenStream.readExpecting(TokenType.IDENTIFIER).getText();

        // Start of argument list.
        tokenStream.readExpecting(TokenType.OPEN_PARENTHESIS);

        // Consume any arguments.
        while (tokenStream.peek().getType() == TokenType.IDENTIFIER) {
            Argument argument = Argument.parse(tokenStream);
            argumentsList.add(argument);
            if (tokenStream.peek().getType() != TokenType.CLOSE_PARENTHESIS) {
                tokenStream.readExpecting(TokenType.COMMA); // Discard comma if not at end of arguments.
            }
        }

        // Closing parenthesis and colon before return type.
        tokenStream.readExpecting(TokenType.CLOSE_PARENTHESIS);
        tokenStream.readExpecting(TokenType.COLON);

        // Parse return type.
        returnType = HahaType.parse(tokenStream);

        // Read preconditions, postconditions and variables.
        TokenType[] permittedTokenTypes = new TokenType[] {
                TokenType.PRECONDITION,
                TokenType.POSTCONDITION,
                TokenType.VAR,
                TokenType.BLOCK_BEGIN};
        Token buffer;
        while ((buffer = tokenStream.peekExpectingOneOf(permittedTokenTypes)).getType() != TokenType.BLOCK_BEGIN) {
            switch(buffer.getType()) {
                case PRECONDITION:
                    preconditionsList.add(Precondition.parse(tokenStream));
                    break;
                case POSTCONDITION:
                    postconditionsList.add(Postcondition.parse(tokenStream));
                    break;
                case VAR:
                    tokenStream.readExpecting(TokenType.VAR); // Discard keyword.
                    while (tokenStream.peek().getType() == TokenType.IDENTIFIER) {
                        variablesList.add(Variable.parse(tokenStream));
                    }
                    break;
            }
        }

        // We should now be at a block begin.
        tokenStream.readExpecting(TokenType.BLOCK_BEGIN);

        // Read in function body, being sensitive to blocks.
        int level = 0; // Track nesting level.
        do {
            Token[] statementBuffer = tokenStream.readUntil(TokenType.PUNCTUATOR);
            statementsList.add(statementBuffer);
            if (statementBuffer.length > 0) {
                if (statementBuffer[0].getType() == TokenType.BLOCK_END) {
                    level--;
                } else if (statementBuffer[0].getType() == TokenType.BLOCK_BEGIN) {
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
