package net.zffu.crimson.tests;

import net.zffu.crimson.CrimsonDatabase;
import net.zffu.crimson.format.FormattingException;
import net.zffu.crimson.tables.CrimsonTable;
import net.zffu.crimson.tables.params.ParameterType;

import java.io.File;
import java.io.IOException;

public class CrimsonDBTest {

    public static void main(String[] args) {
        try {
            CrimsonDatabase database = new CrimsonDatabase(new File(System.getProperty("user.dir"), "db"));
            database.loadTables();
            CrimsonTable table = database.getOrCreateTable("testTable");

            System.out.println(table.getEntries().keySet());

            table.useTemplateIfEmpty(ParameterType.STRING, ParameterType.INTEGER);
            //table.addEntry("test",69);

            //database.saveTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
