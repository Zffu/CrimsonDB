# CrimsonBD
CrimsonDB is an attempt at making fast and lightweight local databases in Java.

## Usage
In order to use CrimsonDB you must create a Crimson Database like this:

```java
import net.zffu.crimson.CrimsonDatabase;

CrimsonDatabase database = new CrimsonDatabase(databaseFolder);
```
You must provide a folder to load and save the tables.

Optionally, you can load the existing table data by using 
```java
database.loadTables();
```

To use tables, you can use this:

```java
import net.zffu.crimson.tables.CrimsonTable;
import net.zffu.crimson.tables.params.ParameterType;

CrimsonTable myTable = database.getOrCreateTable("myTable");
myTable.useTemplateIfEmpty(ParameterType.STRING, ParameterType.INTEGER);
myTable.addEntry("Bob", 25);

```

In this example, we get or create a table called myTable then we add the data template which contains string keys and an integer has data parameters. 
We also add an entry with the key "Bob" and the integer 25.

When you want to save all of the table changes, just use the database like this:
```java
database.saveTables();
```