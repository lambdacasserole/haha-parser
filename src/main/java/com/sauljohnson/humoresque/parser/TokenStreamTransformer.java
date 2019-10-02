package com.sauljohnson.humoresque.parser;

/**
 * Represents a service capable of transforming token streams.
 *
 * @since 18/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
public interface TokenStreamTransformer {

    /**
     * Transforms the given token stream.
     *
     * @param stream    the stream to transform
     * @return          the transformed stream
     */
    TokenStream transform(TokenStream stream);
}
