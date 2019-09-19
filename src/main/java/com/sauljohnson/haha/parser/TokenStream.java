package com.sauljohnson.haha.parser;

import java.util.LinkedList;
import java.util.List;

public class TokenStream {
    private Token[] tokens;

    private int position;

    public TokenStream(Token[] tokens) {
        this.tokens = tokens;
        position = 0;
    }

    public Token read() {
        return position >= tokens.length ? null : tokens[position++];
    }

    public Token peek() {
        return position >= tokens.length ? null : tokens[position];
    }

    public void discardLeadingPunctuators() {
        while (peek() != null && peek().getType() == TokenType.PUNCTUATOR) {
            read();
        }
    }

    public Token[] readUntil(TokenType type)  {
        // We're going to return an array of tokens, build it here.
        List<Token> tokens = new LinkedList<Token>();

        // Read until we hit a punctuator.
        Token buffer;
        while ((buffer = read()).getType() != type) {
            tokens.add(buffer);
        }

        return tokens.toArray(new Token[] {});
    }
}
