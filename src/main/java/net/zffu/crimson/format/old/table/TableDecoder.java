package net.zffu.crimson.format.old.table;

import net.zffu.crimson.format.Decoder;
import net.zffu.crimson.format.FormattingException;
import net.zffu.crimson.tables.CrimsonTable;
import net.zffu.crimson.tables.params.ParameterFactory;
import net.zffu.crimson.tables.params.ParameterType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * File format decoder for CrimsonDB Tables.
 */
public class TableDecoder extends Decoder<CrimsonTable> {

    public static char STRING_LIMITER = "'".toCharArray()[0];


    @Override
    public CrimsonTable decode(String string) throws FormattingException {
        String[] metaSeperator = string.split(";");
        String[] paramParts = metaSeperator[0].split(",");

        CrimsonTable table = new CrimsonTable(null);

        try {
            boolean b = false;
            for(String paramPart : paramParts) {
                ParameterType parameter = decodeParam(paramPart);
                if(b) table.addParameter(parameter);
                else {
                    table.setPrimaryKeyType(parameter);
                    b = true;
                }
            }

            decodeEntries(table, metaSeperator[1]);

        } catch (Exception e) {
            throw new FormattingException(e.getMessage());
        }

        return table;
    }

    public void decodeEntries(CrimsonTable table, String stringPart) throws Exception {
        String[] entries = stringPart.split(",");

        for(String entry : entries) {
            String[] parameters = entry.split("!");
            if(parameters.length != (table.getParameters().size() + 1)) throw new FormattingException("Error while decoding entries of table: Entry parameters are not the same as the table's parameters (Table parameters: " + table.getParameters().toString() + "  Entry parameters: " + parameters + ")");

            int pIndex = 0;

            Object[] params = new Object[parameters.length - 1];

            for(String param : parameters) {
                ParameterType parameter = table.getParameter(pIndex);

                if(parameter != ParameterType.STRING && param.matches(".*[a-z].*")) throw new FormattingException("Error while decoding entries of table: Entry parameter " + pIndex + " seems like a string but is not supposed to be one!");

                if(pIndex > 0) params[pIndex - 1] = ParameterFactory.convertStringToCorrespondingType(param, parameter);

                pIndex++;
            }

            table.addEntry(ParameterFactory.convertStringToCorrespondingType(parameters[0], table.getPrimaryKeyType()), params);
        }
    }


    public ParameterType decodeParam(String stringPart) throws Exception {
        ParameterType type = ParameterType.get(Integer.parseInt(stringPart));
        if(type == null) throw new FormattingException("Error while decoding table parameter: " + stringPart + " is not a valid type index!");
        return type;
    }


}
