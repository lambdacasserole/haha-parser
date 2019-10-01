package com.sauljohnson.haha.parser.model;

/**
 * Represents a program component (i.e. an annotation or statement).
 *
 * @since 01/10/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
public interface ProgramComponent {

    /**
     * Gets the type of this program component.
     *
     * @return  the type
     */
    ProgramComponentType getType();
}
