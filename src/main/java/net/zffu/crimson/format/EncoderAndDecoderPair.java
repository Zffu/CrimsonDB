package net.zffu.crimson.format;

import net.zffu.crimson.format.old.table.TableDecoder;
import net.zffu.crimson.format.old.table.TableEncoder;
import net.zffu.crimson.tables.CrimsonTable;

/**
 * Allows the users to choose which encoder and decoder to use. Will be used for adding new format while still let the old one work.
 */
public enum EncoderAndDecoderPair {

    OLD(new TableEncoder(), new TableDecoder());

    public Encoder<CrimsonTable> encoder;
    public Decoder<CrimsonTable> decoder;

    private EncoderAndDecoderPair(Encoder<CrimsonTable> tableEncoder, Decoder<CrimsonTable> tableDecoder) {
        this.encoder = tableEncoder;
        this.decoder = tableDecoder;
    }

}
