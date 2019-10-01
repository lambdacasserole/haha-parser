package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

import java.util.LinkedList;
import java.util.List;

public class Block extends Statement {

    private ProgramComponent[] programComponents;

    private Block() {
        programComponents = new ProgramComponent[] {};
    }

    public ProgramComponent[] getProgramComponents() {
        return programComponents;
    }

    public static Block parse(TokenStream tokenStream) throws ParseException {

        // Build list of program components (statements and annotations).
        List<ProgramComponent> programComponents = new LinkedList<ProgramComponent>();

        // Read block begin token and punctuator.
        tokenStream.readExpecting(new TokenType[] {TokenType.BLOCK_BEGIN, TokenType.PUNCTUATOR});

        // Read until block end.
        while (tokenStream.peek().getType() != TokenType.BLOCK_END) {
            if (tokenStream.peek().getType() == TokenType.OPEN_BRACE) {
                programComponents.add(Annotation.parse(tokenStream)); // An open brace indicates annotation.
            } else {
                programComponents.add(Statement.parse(tokenStream)); // Anything else is a statement.
            }
        }

        // Discard block end token and punctuator.
        tokenStream.readExpecting(new TokenType[] {TokenType.BLOCK_END, TokenType.PUNCTUATOR});

        // Create and return block.
        Block output = new Block();
        output.programComponents = programComponents.toArray(new ProgramComponent[] {});
        return output;
    }
}
