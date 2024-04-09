package net.zffu.crimson.tables.params;

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

}
