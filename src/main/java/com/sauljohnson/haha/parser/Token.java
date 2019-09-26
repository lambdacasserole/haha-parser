package com.sauljohnson.haha.parser;

/**
 * Represents a source code token.
 *
 * @since 19/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
public class Token {

    private String text;

    private TokenType type;

    /**
     * Initializes a new instance of a token.
     *
     * @param text  the text comprising the token
     * @param type  the type of token this is
     */
    public Token(String text, TokenType type) {
        this.text = text;
        this.type = type;
    }

    /**
     * Gets the text comprising this token.
     *
     * @return  the text comprising this token
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the type of token this is
     * @return  the type of token this is
     */
    public TokenType getType() {
        return type;
    }
}
