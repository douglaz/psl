To read in the truth values of ground atoms from text files, a `DataStore` object is required. 

`DataStore data = new RelationalDataStore(pslModel, entityid : 'string');`<br/>
`data.setup db : DatabaseDriver.H2, type : memory; //in-memory database` <br/>
`//data.setup db : DatabaseDriver.H2;  //persistent database`<br/>

In the code snippet above, the `RelationalDataStore` constructor takes a PSLModel object as its first argument. `entityid : 'string'` in the second argument indicates that the arguments of ground atoms can be any text value. Omitting the second argument, all the arguments must be integers (corresponding to IDs of the arguments). To specify that the database must store its contents in RAM, use the `type : memory` expression. To store its contents on disk, simply omit the expression. 

After a `DataStore` is created, we can read in the truth values of ground atoms from text files as follows:

`insert = data.getInserter(<predicateName>)` <br/>
`insert.loadFromFile(<fileName>)`

`<predicateName>` is the name of the predicate whose ground atoms are to be read,  and `<fileName>` is the name of the file containing the ground atoms' truth values. If `<predicateName>` is of type `PredicateTypes.BooleanTruth`, then the file must contain tab-delimited rows, with each row corresponding to the arguments of the true ground atoms. (The closed-world assumption is made, i.e., atoms not appearing in the file are assumed false.) If `<predicateName>` is of type `PredicateTypes.SoftTruth`, then the `insert.loadFromFileWithTruth` method must be used instead of `insert.loadFromFile`, and the last value of each row in the file is the truth value in the range [0,1]. The default minimum value of the truth value is 0.1. To change this, set the `defaultActivationParameter` at the top of edu/umd/cs/psl/ui/ModelUI.java.

By default, the ground atoms and their truth values are read into partition 1 of `DataStore`. The query predicates whose values are to be inferred should be read into another partition by a specifying partition ID as an argument: `data.getInserter(<predicateName>, <partionID>)`

The following code snippet shows how to read `BooleanTruth` 







