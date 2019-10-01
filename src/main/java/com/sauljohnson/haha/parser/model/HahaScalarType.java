package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;

/**
 * Represents an array type.
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
public class HahaScalarType extends HahaType {

    private HahaScalarType() {
        super();
    }

    public static HahaScalarType parse(TokenStream tokenStream) {
        Token tok = tokenStream.read();
        HahaScalarType output = new HahaScalarType();
        output.baseType = HahaBaseTypeHelper.parse(tok.getText());
        return output;
    }
}
