package net.zffu.crimson.tests;

import net.zffu.crimson.exception.DatabaseModificationException;
import net.zffu.crimson.format.FormattingException;
import net.zffu.crimson.format.table.TableEncoder;
import net.zffu.crimson.tables.CrimsonTable;
import net.zffu.crimson.tables.params.ParameterType;

public class TableEncoderTest {

    public static void main(String[] args) {
        CrimsonTable table = new CrimsonTable("myTestTable");
        table.setPrimaryKeyType(ParameterType.STRING);
        table.addParameter(ParameterType.INTEGER);
        table.addParameter(ParameterType.STRING);

        try {
            table.addEntry("Hello", 123, "Idk");
        } catch (DatabaseModificationException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(new TableEncoder().encode(table));
        } catch (FormattingException e) {
            e.printStackTrace();
        }

    }

}
