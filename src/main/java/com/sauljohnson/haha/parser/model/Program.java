package com.sauljohnson.haha.parser.model;

import com.sauljohnson.haha.parser.Token;
import com.sauljohnson.haha.parser.TokenStream;

import java.util.LinkedList;
import java.util.List;

public class Program {

    private Function[] functions;

    private Axiom[] axioms;

    private Predicate[] predicates;

    private Program() {
        functions = new Function[] {};
        axioms = new Axiom[] {};
        predicates = new Predicate[] {};
    }

    public static Program parse(TokenStream tokenStream) {

        // Prepare lists to hold top-level constructs.
        List<Function> functionsList = new LinkedList<Function>();
        List<Axiom> axiomsList = new LinkedList<Axiom>();
        List<Predicate> predicatesList = new LinkedList<Predicate>();

        // Move past any initial punctuators.
        tokenStream.discardLeadingPunctuators();

        // Read in any top-level elements.
        Token token;
        while ((token = tokenStream.peek()) != null) {
            tokenStream.discardLeadingPunctuators(); // Move past any leftover punctuators.
            switch(token.getType()) {
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
