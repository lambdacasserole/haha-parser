package com.sauljohnson.humoresque.parser.model;

import com.sauljohnson.humoresque.parser.ParseException;
import com.sauljohnson.humoresque.parser.Token;
import com.sauljohnson.humoresque.parser.TokenStream;
import com.sauljohnson.humoresque.parser.TokenType;

/**
 * Represents an abstract type.
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"WeakerAccess", "unused"}) // API class.
public abstract class HahaType {

    protected HahaBaseType baseType;

    protected boolean isArrayType;

    /**
     * Initialises a new instance of an abstract type.
     */
    protected HahaType() {
        baseType = HahaBaseType.UNKNOWN;
        isArrayType = false;
    }

    /**
     * Gets the base type of this abstract type.
     *
     * @return  the base type
     */
    public HahaBaseType getBaseType() {
        return baseType;
    }

    /**
     * Gets whether or not this is an array type.
     *
     * @return  true if this is an array type, otherwise false
     */
    public boolean isArrayType() {
        return isArrayType;
    }

    /**
     * Reads the tokens comprising an abstract type from the given token stream and returns the parsed result.
     *
     * @param tokenStream       the token stream to read from
     * @return                  the parsed result
     * @throws ParseException   if parsing fails
     */
    public static HahaType parse(TokenStream tokenStream) throws ParseException {

        // Expect an array or scalar type.
        Token buffer = tokenStream.peekExpectingOneOf(new TokenType[] {TokenType.TYPE, TokenType.ARRAY});
        if (buffer.getType() == TokenType.ARRAY) {
            return HahaArrayType.parse(tokenStream); // Parse array type.
        } else {
            return HahaScalarType.parse(tokenStream); // Parse scalar type.
        }
    }
}
