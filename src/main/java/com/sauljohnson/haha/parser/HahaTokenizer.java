package com.sauljohnson.haha.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Represents a service capable of transforming HAHA source code into a token array.
 *
 * @since 19/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
public class HahaTokenizer implements Tokenizer {

    /**
     * Contains token templates for all token types recognized by the tokenizer.
     */
    private static final TokenTemplate[] tokenTemplates = new TokenTemplate[] {
            // Ignore comments.
            new TokenTemplate("//.+", TokenType.LINE_COMMENT, true),
            new TokenTemplate("/\\*([\\s\\S]*?)\\*/", TokenType.STREAM_COMMENT, true),
            // Ignore non-newline whitespace.
            new TokenTemplate("[ \\t]+", TokenType.WHITESPACE, true),
            // LF/CRLF acts as the punctuator.
            new TokenTemplate("(\\r?\\n)+", TokenType.PUNCTUATOR),
            // Keywords come first.
            new TokenTemplate("\\bvar\\b", TokenType.VAR),
            new TokenTemplate("\\bbegin\\b", TokenType.BLOCK_BEGIN),
            new TokenTemplate("\\bend\\b", TokenType.BLOCK_END),
            new TokenTemplate("\\bfunction\\b", TokenType.FUNCTION),
            new TokenTemplate("\\baxiom\\b", TokenType.AXIOM),
            new TokenTemplate("\\bpredicate\\b", TokenType.PREDICATE),
            new TokenTemplate("\\bprecondition\\b", TokenType.PRECONDITION),
            new TokenTemplate("\\bpostcondition\\b", TokenType.POSTCONDITION),
            new TokenTemplate("\\binvariant\\b", TokenType.INVARIANT),
            new TokenTemplate("\\bARRAY\\b", TokenType.ARRAY),
            new TokenTemplate("\\bwhile\\b", TokenType.WHILE),
            new TokenTemplate("\\bdo\\b", TokenType.DO),
            new TokenTemplate("\\bif\\b", TokenType.IF),
            new TokenTemplate("\\bthen\\b", TokenType.THEN),
            new TokenTemplate("\\belse\\b", TokenType.ELSE),
            new TokenTemplate("⊤|\\btrue\\b|⊥|\\bfalse\\b", TokenType.BOOLEAN), // Boolean literals count as keywords.
            new TokenTemplate("\\b(Z|INT|BOOLEAN)\\b", TokenType.TYPE), // Types grouped, but still 'keywords'.
            // Punctuation.
            new TokenTemplate("\\(", TokenType.OPEN_PARENTHESIS),
            new TokenTemplate("\\)", TokenType.CLOSE_PARENTHESIS),
            new TokenTemplate("\\{", TokenType.OPEN_BRACE),
            new TokenTemplate("\\}", TokenType.CLOSE_BRACE),
            new TokenTemplate("\\[", TokenType.OPEN_BRACKET),
            new TokenTemplate("\\]", TokenType.CLOSE_BRACKET),
            new TokenTemplate(":=", TokenType.ASSIGNMENT),
            new TokenTemplate("≠|!=|<>", TokenType.INEQUALITY), // Inequality 3 ways.
            new TokenTemplate("<=|≤", TokenType.LESS_THAN_OR_EQUAL_TO), // Less than or equal to 2 ways.
            new TokenTemplate(">=|≥", TokenType.GREATER_THAN_OR_EQUAL_TO), // Greater than or equal to 2 ways.
            new TokenTemplate("⇔|↔|<\\->", TokenType.IFF), // If and only if 3 ways.
            new TokenTemplate("→|⇒|\\->", TokenType.IMPLICATION), // Implication 3 ways.
            new TokenTemplate("=", TokenType.EQUALITY), // TODO: Greedy for punctuator?
            new TokenTemplate("<", TokenType.LESS_THAN),
            new TokenTemplate(">", TokenType.GREATER_THAN),
            new TokenTemplate("/\\\\|∧|\\band\\b", TokenType.CONJUNCTION), // Conjunction 3 ways.
            new TokenTemplate("\\\\/|∨|\\bor\\b", TokenType.DISJUNCTION), // Disjunction 3 ways.
            new TokenTemplate("¬|\\bnot\\b", TokenType.NEGATION), // Negation 2 ways.
            new TokenTemplate(":", TokenType.COLON),
            new TokenTemplate(",", TokenType.COMMA),
            new TokenTemplate("\\+", TokenType.PLUS),
            new TokenTemplate("\\-", TokenType.MINUS),
            new TokenTemplate("/", TokenType.DIVIDE),
            new TokenTemplate("\\*", TokenType.MULTIPLY),
            new TokenTemplate("\\bmod\\b", TokenType.MODULO),
            new TokenTemplate("\\^", TokenType.POWER),
            new TokenTemplate("∀|\\bforall\\b", TokenType.FORALL),
            new TokenTemplate("∃|\\bexists\\b", TokenType.EXISTS),
            // Free-form identifiers and integers.
            new TokenTemplate("\\b[0-9]+\\b", TokenType.INTEGER),
            new TokenTemplate("\\b[a-zA-Z_]\\w*\\b", TokenType.IDENTIFIER)
    };

    /**
     * Counts the number of newlines in a string.
     *
     * @param source    the string to count the newlines in
     * @return          the number of newlines in the string
     */
    private static int countNewlines(String source) {
        int count = 0;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) == '\n'){
                count++;
            }
        }
        return count;
    }

    /**
     * Gets the current line position in the source.
     *
     * @param source    the complete source
     * @param remaining the source remaining to tokenize
     * @return          the current line position in the source
     */
    private static int getLinePosition(String source, String remaining)
    {
        return countNewlines(source) - countNewlines(remaining) + 1;
    }

    /**
     * Gets the current column position in the source.
     *
     * @param source    the complete source
     * @param remaining the source remaining to tokenize
     * @return          the current column position in the source
     */
    private static int getColumnPosition(String source, String remaining)
    {
        String processed = source.substring(0, source.length() - remaining.length());
        return processed.length() - processed.lastIndexOf('\n');
    }

    /**
     * @inheritDoc
     */
    public Token[] tokenize(String source) throws TokenizationException {

        // We're going to return an array of tokens, build it here.
        List<Token> tokens = new LinkedList<Token>();

        // Tokenize input.
        String remaining = source;
        while (remaining != null && !remaining.equals(""))
        {
            // Try to match each template against start of input.
            boolean matches = false;
            for (TokenTemplate tokenTemplate : tokenTemplates)
            {
                Matcher matcher = tokenTemplate.getPattern().matcher(remaining);
                if (matcher.find() && matcher.start() == 0)
                {
                    // Add token of matching type.
                    if (!tokenTemplate.isIgnored()) {
                        tokens.add(new Token(matcher.group(), tokenTemplate.getType()));
                    }

                    // Trim string from beginning of source.
                    remaining = matcher.replaceFirst("");
                    matches = true;
                    break;
                }
            }

            // Unexpected character encountered.
            if (!matches)
            {
                int line = getLinePosition(source, remaining);
                int column = getColumnPosition(source, remaining);
                char character = remaining.charAt(0);
                throw new TokenizationException("Unexpected character '" + character + "' at line "+ line + " column" +
                        " " + column + ".", line, column);
            }
        }

        return tokens.toArray(new Token[] {});
    }
}
