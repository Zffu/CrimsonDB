package net.zffu.crimson.format.table;

import net.zffu.crimson.format.Decoder;
import net.zffu.crimson.tables.CrimsonTable;
import net.zffu.crimson.tables.params.Parameter;
import net.zffu.crimson.tables.params.ParameterType;

import java.util.ArrayList;
import java.util.List;

/**
 * File format decoder for CrimsonDB Tables.
 */
public class TableDecoder extends Decoder<CrimsonTable> {

    @Override
    public CrimsonTable decode(String string) {
        boolean paramRegistration = true;
        String[] paramParts = string.split(",");

        CrimsonTable table = new CrimsonTable(null);

        for(String paramPart : paramParts) {
            Parameter parameter = decodeParam(paramPart.toCharArray());
            table.addParameter(parameter);
        }

        return table;
    }

    public Parameter decodeParam(char[] stringPart) {
        ParameterType type = null;
        String lengthStash = "";
        for(char c : stringPart) {
            if(type == null) {
                type = ParameterType.values()[Integer.parseInt("" + c)];
                continue;
            }
            lengthStash += c;
        }
        return new Parameter(type, Integer.parseInt(lengthStash));
    }
}
