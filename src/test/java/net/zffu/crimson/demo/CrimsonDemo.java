package net.zffu.crimson.demo;

import net.zffu.crimson.CrimsonDatabase;
import net.zffu.crimson.exception.DatabaseModificationException;
import net.zffu.crimson.format.FormattingException;
import net.zffu.crimson.tables.CrimsonTable;
import net.zffu.crimson.tables.params.ParameterType;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * The demo class of CrimsonDB.
 * This demo simulates a shop with a items table and customer table
 */
public class CrimsonDemo {

    public static void main(String[] args) {
        try {
            CrimsonDatabase database = new CrimsonDatabase(new File(System.getProperty("user.dir"), "sampledb"));
            database.loadTables(); // Loads the tables


            CrimsonTable itemsTable = database.getOrCreateTable("items");
            itemsTable.useTemplateIfEmpty(ParameterType.INTEGER, ParameterType.DOUBLE, ParameterType.STRING); // Uses the following structure: Id: ind, price: int, name: string

            itemsTable.addEntry(1, 0.95, "Bread");
            itemsTable.addEntry(2,2.75, "Cake");
            itemsTable.addEntry(3, 1024, "Gold Bar");

            CrimsonTable purchases = database.getOrCreateTable("purchases");

            purchases.useTemplateIfEmpty(ParameterType.STRING, ParameterType.STRING, ParameterType.INTEGER); // Use the following structure: purchaseUUID: uuid, customerName: string, articleId: int

            purchases.addEntry(UUID.randomUUID().toString(), "Bob the buyer", 1);

            database.saveTables();
        } catch (IOException | FormattingException | DatabaseModificationException e) {
            e.printStackTrace();
        }
    }

}
