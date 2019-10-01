package com.sauljohnson.haha.parser.model;

/**
 * Static helper class for converting between strings and HAHA base types.
 *
 * @since 19/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class HahaBaseTypeHelper {

    /**
     * Converts a HAHA base type, represented as a string, to a member of the {@link HahaBaseType} enum.
     *
     * @param rawType   the type as a string
     * @return          the enum member
     */
    public static HahaBaseType parse(String rawType) {

        // Parse type.
        HahaBaseType type = HahaBaseType.UNKNOWN;
        if (rawType.equals("BOOLEAN")) {
            type = HahaBaseType.BOOLEAN;
        } else if (rawType.equals("Z")) {
            type = HahaBaseType.Z;
        } else if (rawType.equals("INT")) {
            type = HahaBaseType.INT;
        }
        return type;
    }
}
