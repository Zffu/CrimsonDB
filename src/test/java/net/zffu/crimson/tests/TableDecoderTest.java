package net.zffu.crimson.tests;

import net.zffu.crimson.format.FormattingException;
import net.zffu.crimson.format.table.TableDecoder;
import net.zffu.crimson.tables.CrimsonTable;
import net.zffu.crimson.tables.params.ParameterType;

public class TableDecoderTest {

    public static void main(String[] args) {
        TableDecoder decoder = new TableDecoder();
        CrimsonTable table = null;
        try {
            table = decoder.decode("2,1;123!Idk");
        } catch (FormattingException e) {
            e.printStackTrace();
        }
        for(ParameterType parameter : table.getParameters()) {
            System.out.println(parameter);
        }
    }

}
