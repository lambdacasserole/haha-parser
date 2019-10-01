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
     * Returns the next token from the token stream or null if the end has been reached.
     *
     * @return  the next token from the token stream
     */
    public Token tryPeek() {
        return position >= tokens.length ? null : tokens[position];
    }

    /**
     * Returns the next token from the token stream or throws an exception if the end has been reached.
     *
     * @return                  the next token from the token stream
     * @throws ParseException   if the end of the stream has been reached
     */
    public Token peek() throws ParseException {

        // Attempt to peek next token.
        Token buffer = tryPeek();

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
     * Returns the next token from the token stream or throws an exception if the end has been reached or the token has
     * an unexpected type.
     *
     * @param types             the types of token to expect
     * @return                  the next token from the token stream
     * @throws ParseException   if the end of the stream has been reached
     */
    public Token peekExpectingOneOf(TokenType[] types) throws ParseException {

        // Read next token.
        Token buffer = peek();

        // Build allowed token type list.
        List<TokenType> allowedTokenTypes = new LinkedList<TokenType>();
        Collections.addAll(allowedTokenTypes, types);

        // Build list of allowed token type names.
        StringBuilder allowedTypes = new StringBuilder();
        for (TokenType allowedTokenType : allowedTokenTypes) {
            if (allowedTypes.length() > 0) {
                allowedTypes.append(", ");
            }
            allowedTypes.append(allowedTokenType.name());
        }

        // Throw an exception if the token type is unexpected.
        if (!allowedTokenTypes.contains(buffer.getType())) {
            throw new ParseException("Parse error, expected token of type in {" + allowedTypes.toString() + "} but got "
                    + buffer.getType().name() + " instead", buffer.getLine(), buffer.getColumn());
        }
        return buffer;
    }

    /**
     * Returns the next token from the token stream or throws an exception if the end has been reached or the token has
     * an unexpected type.
     *
     * @param type              the type of token to expect
     * @return                  the next token from the token stream
     * @throws ParseException   if the end of the stream has been reached
     */
    public Token peekExpecting(TokenType type) throws ParseException {
        return readExpectingOneOf(new TokenType[] {type});
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

        // Attempt to peek next token and increment position.
        Token buffer = peek();
        position++;

        // Return token in buffer.
        return buffer;
    }

    /**
     * Reads the next token from the token stream and returns it or throws an exception if the end has been reached or
     * the token has an unexpected type.
     *
     * @param types             the type of token to expect
     * @return                  the next token from the token stream
     * @throws ParseException   if the end of the stream has been reached
     */
    public Token readExpectingOneOf(TokenType[] types) throws ParseException {

        // Attempt to peek next token and increment position.
        Token buffer = peekExpectingOneOf(types);
        position++;

        // Return token in buffer.
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
        return readExpectingOneOf(new TokenType[] {type});
    }

    public Token[] readUntilAnyOf(TokenType[] types) throws ParseException {

        // We're going to return an array of tokens, build it here.
        List<Token> tokens = new LinkedList<Token>();

        List<TokenType> stopTokenTypes = new LinkedList<TokenType>();
        Collections.addAll(stopTokenTypes, types);

        // Read until we hit a punctuator.
        Token buffer;
        do {
            buffer = read();
            tokens.add(buffer);
        } while (!stopTokenTypes.contains(buffer.getType()));

        return tokens.toArray(new Token[] {});
    }

    public Token[] readUntil(TokenType type) throws ParseException {
        return readUntilAnyOf(new TokenType[] {type});
    }
}
