package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

import java.util.LinkedList;
import java.util.List;

public class Predicate {

    private Token[] tokens;

    private Predicate() {
        tokens = new Token[] {};
    }

    public static Predicate parse(TokenStream tokenStream) {

        // We're going to return an array of tokens, build it here.
        List<Token> tokens = new LinkedList<Token>();

        //
        Token buffer;
        while ((buffer = tokenStream.read()).getType() != TokenType.PUNCTUATOR) {
            tokens.add(buffer);
        }

        Predicate output = new Predicate();
        output.tokens = tokens.toArray(new Token[] {});
        return output;
    }
}
