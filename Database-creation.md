To read in the truth values of ground atoms from text files, a `DataStore` object is required. 

`DataStore data = new RelationalDataStore(pslModel, entityid : 'string');`<br/>
`data.setup db : DatabaseDriver.H2, type : memory; //in-memory database` <br/>
`//data.setup db : DatabaseDriver.H2;  //persistent database`<br/>

In the code snippet above, the `RelationalDataStore` constructor takes a PSLModel object as its first argument. `entityid : 'string'` in the second argument indicates that the arguments of ground atoms can be any text value. If the second argument is omitted, all arguments of ground atoms must be integers (corresponding to IDs of the arguments). To store the database contents in RAM, use the `type : memory` expression. To store its contents on disk, simply omit the expression. 

After a `DataStore` is created, we can read in the truth values of ground atoms from text files as follows:

`insert = data.getInserter(<predicateName>)` <br/>
`insert.loadFromFile(<fileName>)`

`<predicateName>` is the name of the predicate whose ground atoms are to be read,  and `<fileName>` is the name of the file containing its ground atoms' truth values. If `<predicateName>` is of type `PredicateTypes.BooleanTruth`, then the file must contain tab-delimited rows, with each row corresponding to the arguments of true ground atoms. (The closed-world assumption is made, i.e., atoms not appearing in the file are assumed false.) If `<predicateName>` is of type `PredicateTypes.SoftTruth`, then the `insert.loadFromFileWithTruth` method must be used instead of `insert.loadFromFile`, and the last value of each row in the file must be a truth value in the range [0,1]. The default minimum truth value is 0.1. This can be changed by using `PSLModel`'s `setDefaultActivationParameter(double)` method.

By default, the ground atoms and their truth values are read into partition 1 of `DataStore`. The query predicates whose values are to be inferred should be read into another partition by a specifying partition ID as an argument: `data.getInserter(<predicateName>, <partionID>)`

The following code snippet shows how to read in `BooleanTruth` and `SoftTruth` evidence ground atoms and `SoftTruth` query atoms.

`for (Predicate p : [<predicateName1>, <predicateName2>, ...]) //BooleanTruth evidence predicates` <br/>
`{`<br/>
&nbsp;&nbsp;`insert = data.getInserter(p);`<br/>
&nbsp;&nbsp;`insert.loadFromFile(p.getName()+".txt");//<predicateName> atoms are stored in <predicateName>.txt`<br/>
`}`<br/>

`for (Predicate p2 : [<predName1>, <predName2>,...]) //SoftTruth evidencepredicate` <br/>
`{`<br/>
&nbsp;&nbsp;`insert = data.getInserter(p2);`<br/>
&nbsp;&nbsp;`insert.loadFromFileWithTruth(p2.getName()+".txt");//note use of loadFromFileWithTruth`<br/> 
`}<br/>

`for (Predicate q : [<queryPred1>,<queryPred2>,...]) //SoftTruth query predicate` <br/>
`{`<br/>   
&nbsp;&nbsp;`insert = data.getInserter(q,2); //Partition 2 used to store query predicate ground atoms` <br/>
&nbsp;&nbsp;`insert.loadFromFileWithTruth(q.getName()+".txt"); //note use of loadFromFileWithTruth`<br/> 
`}`<br/>

* Note: For the latest **version(1.2.1)** use `loadDelimitedData()` and `loadDelimitedDataTruth()` instead of `loadFromFile()` and `loadFromFileWithTruth()` respectively.







