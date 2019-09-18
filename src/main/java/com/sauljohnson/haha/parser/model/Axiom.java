package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

import java.util.LinkedList;
import java.util.List;

public class Axiom {

    private Token[] tokens;

    private Axiom() {
        tokens = new Token[] {};
    }

    public static Axiom parse(TokenStream tokenStream) {

        // We're going to return an array of tokens, build it here.
        List<Token> tokens = new LinkedList<Token>();

        //
        Token buffer;
        while ((buffer = tokenStream.read()).getType() != TokenType.PUNCTUATOR) {
            tokens.add(buffer);
        }

        Axiom output = new Axiom();
        output.tokens = tokens.toArray(new Token[] {});
        return output;
    }
}
