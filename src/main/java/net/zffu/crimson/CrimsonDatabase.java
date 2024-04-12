package net.zffu.crimson;

import net.zffu.crimson.format.EncoderAndDecoderPair;
import net.zffu.crimson.format.FormattingException;
import net.zffu.crimson.tables.CrimsonTable;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.HashMap;

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

    private EncoderAndDecoderPair encoderAndDecoderPair;

    public CrimsonDatabase(File databaseFolder) throws IOException {
        if(!databaseFolder.exists()) {
            databaseFolder.mkdir();
        }
        if(!databaseFolder.isDirectory()) throw new IOException("The Crimson Database folder provided is not a directory!");
        this.databaseFolder = databaseFolder;
        this.tables = new HashMap<>();
        this.encoderAndDecoderPair = EncoderAndDecoderPair.OLD;
    }

    public CrimsonDatabase(File databaseFolder, EncoderAndDecoderPair pair) throws IOException {
        if(!databaseFolder.exists()) {
            databaseFolder.mkdir();
        }
        if(!databaseFolder.isDirectory()) throw new IOException("The Crimson Database folder provided is not a directory!");
        this.databaseFolder = databaseFolder;
        this.tables = new HashMap<>();
        this.encoderAndDecoderPair = pair;
    }

    /**
     * Loads the database's tables.
     * @throws IOException
     * @throws FormattingException
     */
    public void loadTables() throws IOException, FormattingException {
        for(File file : this.databaseFolder.listFiles()) {
            if(!file.getName().endsWith(".crimson")) continue;
            String tableName = file.getName().replace(".crimson", "");

            FileInputStream inputStream = new FileInputStream(file);
            CrimsonTable table = this.encoderAndDecoderPair.decoder.decode(IOUtils.toString(inputStream));

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
        int index = 0;
        for(File file : this.databaseFolder.listFiles()) {
            if(index >= limit) return;
            if(!file.getName().endsWith(".crimson")) continue;
            String tableName = file.getName().replace(".crimson", "");
            FileInputStream inputStream = new FileInputStream(file);
            CrimsonTable table = this.encoderAndDecoderPair.decoder.decode(IOUtils.toString(inputStream));

            table.setName(tableName);
            this.tables.put(tableName, table);

            index++;
        }
    }

    /**
     * Saves the tables.
     */
    public void saveTables() throws IOException, FormattingException {
        for(CrimsonTable table : tables.values()) {
            File file = new File(this.databaseFolder, table.getName() + ".crimson");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(this.encoderAndDecoderPair.encoder.encode(table));
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
