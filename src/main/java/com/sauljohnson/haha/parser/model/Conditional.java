package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

public class Conditional extends Statement {

    private Token[] predicate;

    private Statement trueArm;

    private Statement falseArm;

    public static Conditional parse(TokenStream tokenStream) throws ParseException {
        tokenStream.readExpecting(TokenType.IF);
        Token[] predicate = tokenStream.readUpTo(TokenType.THEN);
        tokenStream.readExpecting(new TokenType[] {TokenType.THEN, TokenType.PUNCTUATOR});
        Statement trueArm = Statement.parse(tokenStream);
        Statement falseArm = null;
        if (tokenStream.peek().getType() == TokenType.ELSE) {
            tokenStream.readExpecting(new TokenType[] {TokenType.ELSE, TokenType.PUNCTUATOR});
            falseArm = Statement.parse(tokenStream);
        }

        // Create and return assignment.
        Conditional output = new Conditional();
        output.predicate = predicate;
        output.trueArm = trueArm;
        output.falseArm = falseArm;
        return output;
    }
}


