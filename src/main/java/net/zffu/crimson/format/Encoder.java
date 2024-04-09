package net.zffu.crimson.format;

/**
 * An encoder for something to get encoded to the optimized string format.
 * @param <T>
 */
public abstract class Encoder<T> {

    public abstract String encode(T t) throws FormattingException;

}
