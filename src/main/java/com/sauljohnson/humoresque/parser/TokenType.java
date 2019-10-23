package com.sauljohnson.humoresque.parser;

/**
 * Represents a token type.
 *
 * @since 18/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
public enum TokenType {
    PUNCTUATOR,
    AXIOM,
    PREDICATE,
    FUNCTION,
    IDENTIFIER,
    IF,
    ELSE,
    WHILE,
    DO,
    INVARIANT,
    OPEN_PARENTHESIS,
    CLOSE_PARENTHESIS,
    OPEN_BRACE,
    CLOSE_BRACE,
    INTEGER,
    COLON,
    TYPE,
    ASSIGNMENT,
    BLOCK_BEGIN,
    BLOCK_END,
    EQUALITY,
    INEQUALITY,
    LESS_THAN,
    LESS_THAN_OR_EQUAL_TO,
    GREATER_THAN,
    GREATER_THAN_OR_EQUAL_TO,
    PRECONDITION,
    ARRAY,
    OPEN_BRACKET,
    CLOSE_BRACKET,
    IFF,
    POSTCONDITION,
    DISJUNCTION,
    CONJUNCTION,
    NEGATION,
    IMPLICATION,
    BOOLEAN,
    FORALL,
    EXISTS,
    PLUS,
    MINUS,
    DIVIDE,
    MULTIPLY,
    COMMA,
    POWER,
    MODULO,
    THEN,
    WHITESPACE,
    LINE_COMMENT,
    STREAM_COMMENT,
    VAR,
    SKIP,
}
