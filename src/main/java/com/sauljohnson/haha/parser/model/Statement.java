package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

/**
 * Represents an abstract statement.
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
public abstract class Statement implements ProgramComponent {

    /**
     * @inheritDoc
     */
    public ProgramComponentType getType() {
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
