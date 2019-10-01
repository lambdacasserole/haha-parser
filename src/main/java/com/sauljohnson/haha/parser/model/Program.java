package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.ParseException;
import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;
import com.sauljohnson.haha.parser.TokenType;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a program.
 *
 * @since 18/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class Program {

    private Function[] functions;

    private Axiom[] axioms;

    private Predicate[] predicates;

    /**
     * Initializes a new instance of a program.
     */
    private Program() {
        functions = new Function[] {};
        axioms = new Axiom[] {};
        predicates = new Predicate[] {};
    }

    /**
     * Gets the functions contained within the program.
     *
     * @return  the functions
     */
    public Function[] getFunctions() {
        return functions;
    }

    /**
     * Gets the axioms contained within the program.
     *
     * @return  the functions
     */
    public Axiom[] getAxioms() {
        return axioms;
    }

    /**
     * Gets the predicates contained within the program.
     *
     * @return  the functions
     */
    public Predicate[] getPredicates() {
        return predicates;
    }

    /**
     * Reads the tokens comprising a program from the given token stream and returns the parsed result.
     *
     * @param tokenStream   the token stream to read from
     * @return              the parsed result
     */
    public static Program parse(TokenStream tokenStream) throws ParseException {

        // Prepare lists to hold top-level constructs.
        List<Function> functionsList = new LinkedList<Function>();
        List<Axiom> axiomsList = new LinkedList<Axiom>();
        List<Predicate> predicatesList = new LinkedList<Predicate>();

        // We need to get rid of any leading punctuators (newlines).
        tokenStream.discardLeading(TokenType.PUNCTUATOR);

        // Read in any top-level elements.
        while (!tokenStream.isTerminal()) {
            TokenType[] permittedTokenTypes = new TokenType[] {
                    TokenType.FUNCTION,
                    TokenType.AXIOM,
                    TokenType.PREDICATE};
            Token buffer = tokenStream.peekExpectingOneOf(permittedTokenTypes);
            switch(buffer.getType()) {
                case FUNCTION:
                    functionsList.add(Function.parse(tokenStream));
                    break;
                case AXIOM:
                    axiomsList.add(Axiom.parse(tokenStream));
                    break;
                case PREDICATE:
                    predicatesList.add(Predicate.parse(tokenStream));
                    break;
            }
        }

        // Create and return program.
        Program output = new Program();
        output.functions = functionsList.toArray(new Function[] {});
        output.axioms = axiomsList.toArray(new Axiom[] {});
        output.predicates = predicatesList.toArray(new Predicate[] {});
        return output;
    }
}
