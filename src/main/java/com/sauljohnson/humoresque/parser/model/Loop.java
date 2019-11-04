package com.sauljohnson.humoresque.parser.model;

import com.sauljohnson.humoresque.parser.ParseException;
import com.sauljohnson.humoresque.parser.Token;
import com.sauljohnson.humoresque.parser.TokenStream;
import com.sauljohnson.humoresque.parser.TokenType;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a loop.
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class Loop extends Statement {

    private Token[] predicate;

    private Invariant[] invariants;

    private Statement statement;

    /**
     * Initialises a new instance of a loop.
     */
    private Loop() {
        super(StatementType.LOOP);
        predicate = new Token[] {};
        invariants = new Invariant[] {};
        statement = null;
    }

    /**
     * Gets the tokens that make up the predicate of this loop.
     *
     * @return  the tokens
     */
    public Token[] getPredicate() {
        return predicate;
    }

    /**
     * Gets the invariants attached to this loop.
     *
     * @return  the invariants
     */
    public Invariant[] getInvariants() {
        return invariants;
    }

    /**
     * Gets the statement attached to this loop.
     *
     * @return  the statement
     */
    public Statement getStatement() {
        return statement;
    }

    /**
     * Reads the tokens comprising a loop from the given token stream and returns the parsed result.
     *
     * @param tokenStream       the token stream to read from
     * @return                  the parsed result
     * @throws ParseException   if parsing fails
     */
    public static Loop parse(TokenStream tokenStream) throws ParseException {

        // Discard keyword.
        tokenStream.readExpecting(TokenType.WHILE);

        // Read in predicate.
        Token[] predicate = tokenStream.readUpTo(TokenType.DO);

        // Discard keyword and punctuator.
        tokenStream.readExpecting(new TokenType[] {TokenType.DO, TokenType.PUNCTUATOR});

        // Read in all invariants.
        List<Invariant> invariants = new LinkedList<Invariant>();
        while (tokenStream.peek().getType() == TokenType.INVARIANT) {
            invariants.add(Invariant.parse(tokenStream));
        }

        // Read in statement.
        Statement statement = Statement.parse(tokenStream);

        // Create and return loop.
        Loop output = new Loop();
        output.predicate = predicate;
        output.invariants = invariants.toArray(new Invariant[] {});
        output.statement = statement;
        return output;
    }
}
