package net.zffu.crimson;

import net.zffu.crimson.format.FormattingException;
import net.zffu.crimson.format.table.TableDecoder;
import net.zffu.crimson.format.table.TableEncoder;
import net.zffu.crimson.tables.CrimsonTable;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A Database that is powered by CrimsonDB.
 * @author Zffu
 */
public class CrimsonDatabase {

    /**
     * The core folder of the database.
     */
    private File databaseFolder;

    /**
     * The tables of the Crimson Database.
     */
    private HashMap<String, CrimsonTable> tables;

    public CrimsonDatabase(File databaseFolder) throws IOException {
        if(!databaseFolder.exists()) {
            databaseFolder.mkdir();
        }
        if(!databaseFolder.isDirectory()) throw new IOException("The Crimson Database folder provided is not a directory!");
        this.databaseFolder = databaseFolder;
        this.tables = new HashMap<>();
    }

    /**
     * Loads the database's tables.
     * @throws IOException
     * @throws FormattingException
     */
    public void loadTables() throws IOException, FormattingException {
        TableDecoder decoder = new TableDecoder();
        for(File file : this.databaseFolder.listFiles()) {
            if(!file.getName().endsWith(".crimson")) continue;
            String tableName = file.getName().replace(".crimson", "");

            FileInputStream inputStream = new FileInputStream(file);
            CrimsonTable table = decoder.decode(IOUtils.toString(inputStream));

            table.setName(tableName);

            this.tables.put(tableName, table);
        }
    }

    /**
     * Loads the database's table but also limits the amount
     * @throws IOException
     * @throws FormattingException
     */
    public void loadTables(int limit) throws IOException, FormattingException {
        TableDecoder decoder = new TableDecoder();
        int index = 0;
        for(File file : this.databaseFolder.listFiles()) {
            if(index >= limit) return;
            if(!file.getName().endsWith(".crimson")) continue;
            String tableName = file.getName().replace(".crimson", "");
            FileInputStream inputStream = new FileInputStream(file);
            CrimsonTable table = decoder.decode(IOUtils.toString(inputStream));

            table.setName(tableName);
            this.tables.put(tableName, table);

            index++;
        }
    }

    /**
     * Saves the tables.
     */
    public void saveTables() throws IOException, FormattingException {
        TableEncoder encoder = new TableEncoder();
        for(CrimsonTable table : tables.values()) {
            File file = new File(this.databaseFolder, table.getName() + ".crimson");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(encoder.encode(table));
            writer.close();
        }
    }

    public CrimsonTable getOrCreateTable(String tableName) {
        if(this.tables.containsKey(tableName)) {
            return this.tables.get(tableName);
        }
        CrimsonTable table = new CrimsonTable(tableName);
        this.tables.put(tableName, table);
        return table;
    }


}
