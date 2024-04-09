package net.zffu.crimson.format.table;

import net.zffu.crimson.format.Decoder;
import net.zffu.crimson.format.FormattingException;
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
    public CrimsonTable decode(String string) throws FormattingException {
        boolean paramRegistration = true;
        String[] paramParts = string.split(",");

        CrimsonTable table = new CrimsonTable(null);

        try {
            for(String paramPart : paramParts) {
                Parameter parameter = decodeParam(paramPart.toCharArray());
                table.addParameter(parameter);
            }
        } catch (Exception e) {
            throw new FormattingException(e.getMessage());
        }

        return table;
    }

    public Parameter decodeParam(char[] stringPart) throws Exception {
        ParameterType type = null;
        String lengthStash = "";
        for(char c : stringPart) {
            if(type == null) {
                type = ParameterType.get(Integer.parseInt("" + c));
                if(type == null) throw new FormattingException("Error while decoding table parameter: " + c + " is not a valid type index!");
                continue;
            }
            lengthStash += c;
        }
        return new Parameter(type, Integer.parseInt(lengthStash));
    }
}
