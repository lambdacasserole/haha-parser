package com.sauljohnson.haha.parser;

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
        while (peek().getType() == TokenType.PUNCTUATOR) {
            read();
        }
    }
}
