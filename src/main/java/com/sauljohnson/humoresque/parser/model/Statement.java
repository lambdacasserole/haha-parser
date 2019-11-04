package com.sauljohnson.humoresque.parser.model;

import com.sauljohnson.humoresque.parser.ParseException;
import com.sauljohnson.humoresque.parser.Token;
import com.sauljohnson.humoresque.parser.TokenStream;
import com.sauljohnson.humoresque.parser.TokenType;

/**
 * Represents an abstract statement.
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused", "WeakerAccess"}) // API class.
public abstract class Statement implements ProgramComponent {

    private StatementType statementType;

    /**
     * Abstract constructor for a new instance of a statement.
     *
     * @param statementType the statement type
     */
    protected Statement(StatementType statementType) {
        this.statementType = statementType;
    }

    /**
     * Gets the type of this statement.
     *
     * @return  the type of this statement
     */
    public StatementType getStatementType() {
        return statementType;
    }

    /**
     * @inheritDoc
     */
    public ProgramComponentType getProgramComponentType() {
        return ProgramComponentType.STATEMENT;
    }

    /**
     * Reads the tokens comprising a statement from the given token stream and returns the parsed result.
     *
     * @param tokenStream       the token stream to read from
     * @return                  the parsed result
     * @throws ParseException   if parsing fails
     */
    public static Statement parse(TokenStream tokenStream) throws ParseException {

        // Support all kinds of program statements.
        TokenType[] permittedTokenTypes = new TokenType[] {
                TokenType.IDENTIFIER,
                TokenType.BLOCK_BEGIN,
                TokenType.IF,
                TokenType.WHILE};

        // Parse based on peek.
        Token buffer = tokenStream.peekExpectingOneOf(permittedTokenTypes);
        Statement output = null;
        switch(buffer.getType()) {
            case IDENTIFIER:
                output = Assignment.parse(tokenStream); // An identifier means an assignment.
                break;
            case BLOCK_BEGIN:
                output = Block.parse(tokenStream); // A block begin means a block.
                break;
            case IF:
                output = Conditional.parse(tokenStream); // An if means a conditional.
                break;
            case WHILE:
                output = Loop.parse(tokenStream); // A while means a loop.
                break;
        }
        return output;
    }
}
