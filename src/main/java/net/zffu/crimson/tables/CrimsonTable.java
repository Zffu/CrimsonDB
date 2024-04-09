package net.zffu.crimson.tables;

import net.zffu.crimson.exception.DatabaseModificationException;
import net.zffu.crimson.tables.params.ParameterType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A Table of an Crimson Database.
 */
public class CrimsonTable {

    /**
     * The parameters of the table
     */
    private List<ParameterType> parameters;

    /**
     * The entries of the table.
     */
    private HashMap<String, Object[]> entries = new HashMap<>();

    /**
     * The table name.
     */
    private String name;

    /**
     * Currently CrimsonDB relies on different files for each table, so this is the table file.
     */
    private File file;

    /**
     * Constructs a CrimsonDB Table.
     * @param name
     */
    public CrimsonTable(String name) {
        this.name = name;
        this.parameters = new ArrayList<>();
    }

    /**
     * Adds a parameter to the Table. Do not use
     * @param parameter
     */
    public void addParameter(ParameterType parameter) {
        this.parameters.add(parameter);
    }

    /**
     * Gets a list of the parameters of the table.
     * @return
     */
    public List<ParameterType> getParameters() {
        return this.parameters;
    }

    /**
     * Adds an entry into the table.
     * @param key
     * @param params
     */
    public void addEntry(String key, Object... params) throws DatabaseModificationException {
        if(params.length != this.parameters.size()) throw new DatabaseModificationException("Error while adding entry to table: The parameters do not match");
        this.entries.put(key, params);
    }

}
