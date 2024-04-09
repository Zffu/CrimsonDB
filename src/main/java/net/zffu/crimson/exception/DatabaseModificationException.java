package net.zffu.crimson.exception;

/**
 * Exception happening when something is wrong while modifying a database related object.
 */
public class DatabaseModificationException extends Exception {

    public DatabaseModificationException(String msg) {
        super(msg);
    }

}
