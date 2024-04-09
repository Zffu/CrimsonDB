package net.zffu.crimson.format.table;

import net.zffu.crimson.format.Encoder;
import net.zffu.crimson.format.FormattingException;
import net.zffu.crimson.tables.CrimsonTable;
import net.zffu.crimson.tables.params.ParameterType;

import java.util.List;
import java.util.Map;

public class TableEncoder extends Encoder<CrimsonTable> {

    @Override
    public String encode(CrimsonTable table) throws FormattingException {
        return encodeParameters(table) + ";" + encodeEntries(table);
    }

    public String encodeParameters(CrimsonTable table) {
        String s = "" + table.getPrimaryKeyType().index;
        for(ParameterType type : table.getParameters()) {
            s += ",";
            s += type.index;
        }
        return s;
    }

    public String encodeEntries(CrimsonTable table) {
        String s = "";
        boolean b = false;
        for(Map.Entry<Object, Object[]> entry : table.getEntries().entrySet()) {
            if(b) s += ",";
            s += entry.getKey().toString();
            for(Object o : entry.getValue()) {
                s += "!";
                s += o.toString();
            }
        }
        return s;
    }

}
