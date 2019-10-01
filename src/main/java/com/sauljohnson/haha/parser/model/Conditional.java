package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

/**
 * Represents a conditional.
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class Conditional extends Statement {

    private Token[] predicate;

    private Statement trueArm;

    private Statement falseArm;

    /**
     * Initialises a new instance of a conditional.
     */
    private Conditional() {
        predicate = new Token[] {};
        trueArm = null;
        falseArm = null;
    }

    /**
     * Gets the tokens that make up the predicate of this conditional.
     *
     * @return  the tokens
     */
    public Token[] getPredicate() {
        return predicate;
    }

    /**
     * Gets the the statement that forms the true arm of this conditional.
     *
     * @return  the statement
     */
    public Statement getTrueArm() {
        return trueArm;
    }

    /**
     * Gets the the statement that forms the false arm of this conditional.
     *
     * @return  the statement
     */
    public Statement getFalseArm() {
        return falseArm;
    }

    /**
     * Reads the tokens comprising a conditional from the given token stream and returns the parsed result.
     *
     * @param tokenStream       the token stream to read from
     * @return                  the parsed result
     * @throws ParseException   if parsing fails
     */
    public static Conditional parse(TokenStream tokenStream) throws ParseException {

        // Discard leading keyword.
        tokenStream.readExpecting(TokenType.IF);

        // Read in predicate.
        Token[] predicate = tokenStream.readUpTo(TokenType.THEN);

        // Discard keyword and punctuator.
        tokenStream.readExpecting(new TokenType[] {TokenType.THEN, TokenType.PUNCTUATOR});

        // Read in true arm of conditional.
        Statement trueArm = Statement.parse(tokenStream);

        // Read in false arm too if it's present.
        Statement falseArm = null;
        if (tokenStream.peek().getType() == TokenType.ELSE) {
            tokenStream.readExpecting(new TokenType[] {TokenType.ELSE, TokenType.PUNCTUATOR});
            falseArm = Statement.parse(tokenStream);
        }

        // Create and return conditional.
        Conditional output = new Conditional();
        output.predicate = predicate;
        output.trueArm = trueArm;
        output.falseArm = falseArm;
        return output;
    }
}


