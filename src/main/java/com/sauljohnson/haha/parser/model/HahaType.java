package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

public abstract class HahaType {

    protected HahaBaseType baseType;

    protected boolean isArrayType;

    protected HahaType() {
        this.baseType = HahaBaseType.UNKNOWN;
        this.isArrayType = false;
    }

    public HahaBaseType getBaseType() {
        return baseType;
    }

    public boolean isArrayType() {
        return isArrayType;
    }

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
