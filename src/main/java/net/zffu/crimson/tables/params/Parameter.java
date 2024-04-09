package net.zffu.crimson.tables.params;

import net.zffu.crimson.format.FormattingException;

/**
 * Represents a parameter of a table.
 */
public class Parameter {

    private ParameterType type;
    private int parameterLength;

    public Parameter(ParameterType type, int length) {
        this.type = type;
        this.parameterLength = length;
    }

    public ParameterType getType() {
        return this.type;
    }

    public int getLength() {
        return this.parameterLength;
    }

}
