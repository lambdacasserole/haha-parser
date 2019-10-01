package com.sauljohnson.haha.parser;

import java.util.LinkedList;

/**
 * A token stream transformer that filters consecutive tokens of the same type.
 *
 * @since 18/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
public class ConsecutiveTokenFilter implements TokenStreamTransformer {

    private TokenType type;

    /**
     * Initialises a new instance of a consecutive token filter.
     *
     * @param type  the type of tokens to filter
     */
    public ConsecutiveTokenFilter(TokenType type) {
        this.type = type;
    }

    /**
     * @inheritDoc
     */
    public TokenStream transform(TokenStream stream) {

        // Build output list.
        LinkedList<Token> output = new LinkedList<Token>();

        // Reduce all consecutive tokens of given type to single instance.
        boolean active = false;
        do {
            Token next = stream.tryRead();
            if (next.getType() == type) {
                if (!active) {
                    output.add(next);
                    active = true;
                }
            }
            else {
                output.add(next);
                active = false;
            }
        } while (stream.tryPeek() != null);

        // Return transformed stream.
        return new TokenStream(output.toArray(new Token[] {}));
    }
}
