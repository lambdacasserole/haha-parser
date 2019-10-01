package com.sauljohnson.haha.parser;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a stream of tokens.
 *
 * @since 19/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
public class TokenStream {

    private Token[] tokens;

    private int position;

    /**
     * Initialises a new token stream.
     *
     * @param tokens    the tokens to load into the stream
     */
    public TokenStream(Token[] tokens) {
        this.tokens = tokens;
        position = 0;
    }

    /**
     * Reads the next token from the token stream and returns it or null if the end has been reached.
     *
     * @return  the next token from the token stream
     */
    public Token read() {
        return position >= tokens.length ? null : tokens[position++];
    }

    /**
     * Returns the next token from the token stream and or null if the end has been reached.
     *
     * @return  the next token from the token stream
     */
    public Token peek() {
        return position >= tokens.length ? null : tokens[position];
    }

    public void discardLeadingPunctuators() {
        while (peek() != null && peek().getType() == TokenType.PUNCTUATOR) {
            read();
        }
    }

    public Token[] readUntilAnyOf(TokenType[] types) {
        // We're going to return an array of tokens, build it here.
        List<Token> tokens = new LinkedList<Token>();

        List<TokenType>  gg = new LinkedList<TokenType>();
        Collections.addAll(gg, types);

        // Read until we hit a punctuator.
        Token buffer;
        do {
            buffer = read();
            tokens.add(buffer);
        } while (!gg.contains(buffer.getType()));

        return tokens.toArray(new Token[] {});
    }

    public Token[] readUntil(TokenType type)  {
        return readUntilAnyOf(new TokenType[] {type});
    }
}
