package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

/**
 * Represents a scalar type.
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
public class HahaScalarType extends HahaType {

    private HahaScalarType() {
        super();
    }

    /**
     * Reads the tokens comprising a scalar type from the given token stream and returns the parsed result.
     *
     * @param tokenStream   the token stream to read from
     * @return              the parsed result
     */
    public static HahaScalarType parse(TokenStream tokenStream) throws ParseException {

        // Expect a type token only.
        Token buffer = tokenStream.readExpecting(TokenType.TYPE);

        // Create and return scalar type.
        HahaScalarType output = new HahaScalarType();
        output.baseType = HahaBaseTypeHelper.parse(buffer.getText());
        return output;
    }
}
