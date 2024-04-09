package net.zffu.crimson.tables;

import net.zffu.crimson.tables.params.Parameter;

import java.util.Map;

public class CrimsonTable {

    /**
     * The parameters of the table
     */
    private Map<String, Parameter> parameters;

    /**
     * The table name.
     */
    private String name;

    public CrimsonTable(String name) {
        this.name = name;
    }



}
