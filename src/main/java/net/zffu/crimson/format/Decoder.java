package net.zffu.crimson.format;

/**
 * Represents a decoder for something (like Tables) in Crimson that allows to decode from the optimized file format.
 * @param <T> the output object type
 */
public abstract class Decoder<T> {

    public abstract T decode(String string);

}
