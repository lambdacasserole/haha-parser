package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

import java.util.LinkedList;
import java.util.List;

public class Loop extends Statement {

    private Token[] predicate;

    private Invariant[] invariants;

    private Statement statement;

    private Loop() {
        predicate = new Token[] {};
        invariants = new Invariant[] {};
        statement = null;
    }

    public Token[] getPredicate() {
        return predicate;
    }

    public Invariant[] getInvariants() {
        return invariants;
    }

    public Statement getStatement() {
        return statement;
    }

    public static Loop parse(TokenStream tokenStream) throws ParseException {

        tokenStream.readExpecting(TokenType.WHILE);

        Token[] predicate = tokenStream.readUpTo(TokenType.DO);

        tokenStream.readExpecting(new TokenType[] {TokenType.DO, TokenType.PUNCTUATOR});

        List<Invariant> invariants = new LinkedList<Invariant>();
        while (tokenStream.peek().getType() == TokenType.INVARIANT) {
            invariants.add(Invariant.parse(tokenStream));
        }
        Statement statement = Statement.parse(tokenStream);


        // Create and return function.
        Loop output = new Loop();
        output.predicate = predicate;
        output.invariants = invariants.toArray(new Invariant[] {});
        output.statement = statement;
        return output;
    }
}
