package com.sauljohnson.haha.parser;

public interface TokenStreamTransformer {
    TokenStream transform(TokenStream stream);
}
