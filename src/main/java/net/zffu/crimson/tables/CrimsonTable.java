package net.zffu.crimson.tables;

import net.zffu.crimson.tables.params.Parameter;

import java.io.File;
import java.util.List;
import java.util.Map;

public class CrimsonTable {

    /**
     * The parameters of the table
     */
    private List<Parameter> parameters;

    /**
     * The table name.
     */
    private String name;

    /**
     * Currently CrimsonDB relies on different files for each table, so this is the table file.
     */
    private File file;

    public CrimsonTable(String name) {
        this.name = name;
    }

    public void addParameter(Parameter parameter) {
        this.parameters.add(parameter);
    }



}
