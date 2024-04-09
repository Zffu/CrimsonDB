package net.zffu.crimson.tests;

import net.zffu.crimson.format.table.TableDecoder;
import net.zffu.crimson.tables.CrimsonTable;
import net.zffu.crimson.tables.params.Parameter;

public class TableDecoderTest {

    public static void main(String[] args) {
        TableDecoder decoder = new TableDecoder();
        CrimsonTable table = decoder.decode("15,23");
        for(Parameter parameter : table.getParameters()) {
            System.out.println(parameter.getType() + ": " + parameter.getLength());
        }
    }

}
