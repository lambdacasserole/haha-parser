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
@SuppressWarnings({"WeakerAccess", "unused"}) // API class.
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
    public Token tryRead() {
        return position >= tokens.length ? null : tokens[position++];
    }

    /**
     * Reads the next token from the token stream and returns it or throws an exception if the end has been reached.
     *
     * @return                  the next token from the token stream
     * @throws ParseException   if the end of the stream has been reached
     */
    public Token read() throws ParseException {

        // Attempt to read next token.
        Token buffer = tryRead();

        // Throw an exception if we're at the end of the stream.
        if (buffer == null) {

            // Get line and column number if we can.
            int line = 0;
            int col = 0;
            if (tokens.length > 0){
                line = tokens[tokens.length - 1].getLine();
                col = tokens[tokens.length - 1].getColumn();
            }

            // Throw exception up the stack.
            throw new ParseException("Parse error, unexpected end of token stream.", line, col);
        }
        return buffer;
    }

    /**
     * Reads the next token from the token stream and returns it or throws an exception if the end has been reached or
     * the token has an unexpected type.
     *
     * @param type              the type of token to expect
     * @return                  the next token from the token stream
     * @throws ParseException   if the end of the stream has been reached
     */
    public Token readExpecting(TokenType type) throws ParseException {

        // Read next token.
        Token buffer = read();

        // Throw an exception if the token type is unexpected.
        if (buffer.getType() != type) {
            throw new ParseException("Parse error, expected token of type " + type.name() + " but got "
                    + buffer.getType().name() + " instead", buffer.getLine(), buffer.getColumn());
        }
        return buffer;
    }

    /**
     * Returns the next token from the token stream and or null if the end has been reached.
     *
     * @return  the next token from the token stream
     */
    public Token peek() {
        return position >= tokens.length ? null : tokens[position];
    }

    public Token[] readUntilAnyOf(TokenType[] types) throws ParseException {
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

    public Token[] readUntil(TokenType type) throws ParseException {
        return readUntilAnyOf(new TokenType[] {type});
    }

    public boolean isTerminal() {
        return peek() == null;
    }
}
