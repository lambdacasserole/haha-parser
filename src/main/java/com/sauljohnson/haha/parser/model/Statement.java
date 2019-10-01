package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

public abstract class Statement implements ProgramComponent {
    public static Statement parse(TokenStream tokenStream) throws ParseException {
        // Read preconditions, postconditions and variables.
        TokenType[] permittedTokenTypes = new TokenType[] {
                TokenType.IDENTIFIER,
                TokenType.BLOCK_BEGIN,
                TokenType.IF,
                TokenType.WHILE};
        Token buffer = tokenStream.peekExpectingOneOf(permittedTokenTypes);
        Statement output = null;
        switch(buffer.getType()) {
            case IDENTIFIER:
                output = Assignment.parse(tokenStream);
                break;
            case BLOCK_BEGIN:
                output = Block.parse(tokenStream);
                break;
            case IF:
                output = Conditional.parse(tokenStream);
                break;
            case WHILE:
                output = Loop.parse(tokenStream);
                break;
        }
        return output;
    }
}
