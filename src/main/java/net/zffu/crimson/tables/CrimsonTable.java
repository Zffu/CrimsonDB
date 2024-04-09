package net.zffu.crimson.tables;

import net.zffu.crimson.exception.DatabaseModificationException;
import net.zffu.crimson.tables.params.ParameterType;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A Table of an Crimson Database.
 */
public class CrimsonTable {

    /**
     * The primary key type of the table.
     */
    private ParameterType primaryKey;

    /**
     * The parameters of the table
     */
    private List<ParameterType> parameters;

    /**
     * The entries of the table.
     */
    private HashMap<Object, Object[]> entries = new HashMap<>();

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
     * Adds a parameter to the table.
     * @param parameter
     */
    @Deprecated
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
     * Sets the primary key type.
     * @param type
     */
    @Deprecated
    public void setPrimaryKeyType(ParameterType type) {
        this.primaryKey = type;
    }

    /**
     * Uses the provided template of primary key type and parameters only if those data are not set.
     * @param keyType
     * @param types
     */
    public void useTemplateIfEmpty(ParameterType keyType, ParameterType... types) {
        if(primaryKey == null) this.primaryKey = keyType;
        if(this.parameters.isEmpty()) this.parameters.addAll(Arrays.asList(types));
    }

    /**
     * Adds an entry into the table.
     * @param key
     * @param params
     */
    public void addEntry(Object key, Object... params) throws DatabaseModificationException {
        if(params.length != this.parameters.size()) throw new DatabaseModificationException("Error while adding entry to table: The parameters do not match");
        this.entries.put(key, params);
    }

    public HashMap<Object, Object[]> getEntries() {
        return this.entries;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
