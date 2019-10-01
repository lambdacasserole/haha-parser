package com.sauljohnson.haha.parser;

import java.util.LinkedList;

public class ConsecutiveTokenFilter implements TokenStreamTransformer {

    private TokenType type;

    public ConsecutiveTokenFilter(TokenType type) {
        this.type = type;
    }

    public TokenStream transform(TokenStream stream) {
        LinkedList<Token> output = new LinkedList<Token>();
        boolean active = false;
        do {
            Token next = stream.read();
            if (next.getType() == type) {
                if (!active) {
                    output.add(next);
                    active = true;
                }
            }
            else {
                output.add(next);
                active = false;
            }
        } while (stream.peek() != null);
        return new TokenStream(output.toArray(new Token[] {}));
    }
}
