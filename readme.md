# CrimsonBD
CrimsonDB is an attempt at making fast and lightweight local databases in Java.

## File Weight Compared to SQLITE
Please keep in mind that those tests were realised with small amounts of data.

| Data to save             | CrimsonDB    | SQLITE       |
|--------------------------|--------------|--------------|
| 9 Parameters in 2 tables | 18 bytes     | 20,480 bytes |

## Limitations
Currently, Crimson is in development and some bugs might occur. Also some features are currently not usable:
- **Lists:** Lists are currently not supported by Crimson, However we will add support for them soon.
- **Characters used in the format:** One of Crimson biggest limitation, you cannot use the character used in the file format right now.


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
database.loadTables(5); // Only loads the first 5 tables, used to not flood memory.
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