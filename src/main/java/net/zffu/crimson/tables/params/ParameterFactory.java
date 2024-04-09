package net.zffu.crimson.tables.params;

public class ParameterFactory {

    /**
     * Converts the string stored in the Database into the real Java type.
     * @param original
     * @param type
     * @return
     */
    public static Object convertStringToCorrespondingType(String original, ParameterType type) {
        switch (type) {
            case STRING:
                return original;
            case INTEGER:
                return Integer.parseInt(original);
            case DOUBLE:
                return Double.parseDouble(original);
            case FLOAT:
                return Float.parseFloat(original);
            case LONG:
                return Long.parseLong(original);
            case SHORT:
                return Short.decode(original);
        }
        return null;
    }

}
