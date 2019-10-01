package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;

/**
 * Represents an array type.
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class HahaArrayType extends HahaType {

    /**
     * Initialises a new instance of an array type.
     */
    private HahaArrayType() {
        super();
        this.isArrayType = true; // Set array type flag in superclass.
    }

    /**
     * Reads the tokens comprising an array type from the given token stream and returns the parsed result.
     *
     * @param tokenStream   the token stream to read from
     * @return              the parsed result
     */
    public static HahaArrayType parse(TokenStream tokenStream) {

        // TODO: We should have an array token, then a square bracket. Check this!
        tokenStream.read();
        tokenStream.read();

        Token typeToken = tokenStream.read(); // TODO: Here's the type. Check this!
        tokenStream.read(); // TODO: Discard closing bracket. Check this!

        // Create and return array type.
        HahaArrayType output = new HahaArrayType();
        output.baseType = HahaBaseTypeHelper.parse(typeToken.getText());
        return output;
    }
}
