package net.zffu.crimson.tables.params;

public enum ParameterType {

    ANY(0),
    STRING(1),
    INTEGER(2),
    DOUBLE(3),
    FLOAT(4),
    SHORT(5),
    LONG(6);

    public static ParameterType get(int index) {
        try {
            return values()[index];
        } catch (Exception e) {
            return null;
        }
    }

    public int index;

    private ParameterType(int index) {
        this.index = index;
    }

}
