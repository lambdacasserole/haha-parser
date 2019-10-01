package com.sauljohnson.haha.parser.model;

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

    public static HahaType parse(TokenStream tokenStream) {
        if (tokenStream.peek().getType() == TokenType.ARRAY) {
            return HahaArrayType.parse(tokenStream);
        } else {
            return HahaScalarType.parse(tokenStream);
        }
    }
}
