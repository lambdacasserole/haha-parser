package com.sauljohnson.haha.parser.model;

public class Argument {
    private String identifier;
    private HahaType type;

    public Argument(String identifier, HahaType type) {
        this.identifier = identifier;
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public HahaType getType() {
        return type;
    }


}
