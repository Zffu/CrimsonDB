package net.zffu.crimson.format;

/**
 * An exception that is thrown while decoding or encoding something.
 */
public class FormattingException extends Exception {

    public FormattingException(String msg) {
        super(msg);
    }

}
