package com.sauljohnson.humoresque.parser.model;

/**
 * Represents a skip.
 *
 * @since 23/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
public class Skip implements ProgramComponent {

    /**
     * @inheritDoc
     */
    public ProgramComponentType getType() {
        return ProgramComponentType.SKIP;
    }
}
