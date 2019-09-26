package com.sauljohnson.haha.parser.model;

/**
 * Static helper class for converting between strings and HAHA types.
 *
 * @since 19/09/19
 * @author Saul Johnson <saul.a.johnson@gmail.com>
 */
@SuppressWarnings({"unused"}) // API class.
public class HahaTypeHelper {

    /**
     * Converts a HAHA type, represented as a string, to a member of the {@link HahaType} enum.
     *
     * @param rawType   the type as a string
     * @return          the enum member
     */
    public static HahaType parse(String rawType) {

        // TODO: Support for array types!

        // Parse type.
        HahaType type = HahaType.UNKNOWN;
        if (rawType.equals("BOOLEAN")) {
            type = HahaType.BOOLEAN;
        } else if (rawType.equals("Z")) {
            type = HahaType.Z;
        } else if (rawType.equals("INT")) {
            type = HahaType.INT;
        }
        return type;
    }
}
