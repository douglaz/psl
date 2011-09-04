To read in the truth values of ground atoms from text files, a `DataStore` object is required. 

`DataStore data = new RelationalDataStore(pslModel, entityid : 'string');`<br/>
`data.setup db : DatabaseDriver.H2, type : memory; //in-memory database` <br/>
`//data.setup db : DatabaseDriver.H2;  //persistent database`<br/>

In the code snippet above, the `RelationalDataStore` constructor takes a PSLModel object as its first argument. `entityid : 'string'` in the second argument indicates that the arguments of ground atoms can be any text value. Omitting the second argument, all the arguments must be integers (corresponding to IDs of the arguments). To specify that the database must store its contents in RAM, use the `type : memory` expression. To store its contents on disk, simply omit the expression. 

After a `DataStore` is created, we proceed