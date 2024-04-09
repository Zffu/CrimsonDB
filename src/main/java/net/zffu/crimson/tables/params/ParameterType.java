package net.zffu.crimson.tables.params;

public enum ParameterType {

    ANY,
    STRING,
    NUMBER;

    public static ParameterType get(int index) {
        try {
            return values()[index];
        } catch (Exception e) {
            return null;
        }
    }

}
