After ensuring that the [[prerequisites]] and [[examples|installing examples]] are installed, change to the directory containing the project of examples. Inside this project you will find the ```BasicExample.groovy``` example.  First, navigate to the parent directory of the `BasicExample.groovy` example:  

````
>> cd psl-example/src/main/java/edu/umd/cs/example  
````     
  
Here, you will find `BasicExample.groovy`.  This file provides an example of using the Groovy PSL syntax for defining predicates and rules, loading predicate data, running basic inferences, and learning rule weights. 

# Model: Creation and Configuration

The first portion of a PSL program creates a model and defines configuration parameters for that model.

We create a ConfigBundle which loads properties from the file: ```/src/main/resources/psl.properties```

```
ConfigManager cm = ConfigManager.getManager()
ConfigBundle config = cm.getBundle("basic-example")
```

Now, we create a ```DataStore``` to enable database functionality for our PSL program, and provide the specified configuration parameters.

```
def defaultPath = System.getProperty("java.io.tmpdir")
String dbpath = config.getString("dbpath", defaultPath + File.separator + "basic-example")
DataStore data = new RDBMSDataStore(new H2DatabaseDriver(Type.Disk, dbpath, true), config)
```

Finally, with our ```DataStore``` created, we can create a PSL model:

```
PSLModel m = new PSLModel(this, data)
```

# Model: Predicates, Functions, Rules, and Constraints

 'BasicExample.groovy' defines 4 simple predicates:
### Predicates
- **Network**
```
m.add predicate: "Network", types: [ArgumentType.UniqueID, ArgumentType.UniqueID]
```

- **Name**

```
m.add predicate: "Name", types: [ArgumentType.UniqueID, ArgumentType.String]
```
- **Knows**

```
m.add predicate: "Knows", types: [ArgumentType.UniqueID, ArgumentType.UniqueID]
```
- **SamePerson**

```
m.add predicate: "SamePerson", types: [ArgumentType.UniqueID, ArgumentType.UniqueID]
```

See [[defining predicate types]] for more information on defining predicate types.   


### Functions

In addition, ``BasicExample.groovy`` defines the function:
- **SameName**

```
m.add function: "SameName" , implementation: new LevenshteinSimilarity()
```

See [[defining functions]] for more information on defining functions. For the purposes of our example, **SameName** is a function that maps a pair of entities to 1 if their names are identical and 0 otherwise. 

Next, we define ground terms (constants) to refer to two distinct social networks:

```
GroundTerm snA = data.getUniqueID(1)
GroundTerm snB = data.getUniqueID(2)
```

In addition to the above predicates and function, we also define the following rules which are written both with pseudo code and their corresponding PSL syntax below.  For information on writing rules using PSL's syntax please see [[writing rules]].      
### Rules
- **IF** ( ```Network(A, snA)``` **AND** ```Network(B, snB)``` **AND** ```Name(A,X)``` **AND** ```Name(B,Y)``` **AND** ```SameName(X,Y)``` ) **THEN** ```SamePerson(A,B)```

```
m.add rule : ( Network(A, snA) & Network(B, snB) & Name(A,X) & Name(B,Y)
	& SameName(X,Y) ) >> SamePerson(A,B),  weight : 5
```

Similarly, another rule we might define utilizes the knowledge of the SamePerson and Knows predicates to infer other values of the SamePerson predicate:

- **IF** ( ```Network(A, snA)``` **AND** ```Network(B, snB)``` **AND** ```SamePerson(A,B)``` **AND** ```Knows(A, Friend1)``` **AND** ```Knows(B, Friend2)``` ) **THEN** ```SamePerson(Friend1, Friend2)```
```
m.add rule : ( Network(A, snA) & Network(B, snB) & SamePerson(A,B) & Knows(A, Friend1)
	& Knows(B, Friend2) ) >> SamePerson(Friend1, Friend2) , weight : 3.2
```

For more information on defining rules see [[defining rules]].

### Constraints

After we define our rules, we then define constraints for our model. 

```
m.add PredicateConstraint.PartialFunctional, on : SamePerson
m.add PredicateConstraint.PartialInverseFunctional, on : SamePerson
m.add PredicateConstraint.Symmetric, on : SamePerson
```
In this case, we restrict that each person can be aligned to at most one other person in the other social network. To do so, we define two partial functional constraints where the latter is on the inverse. We also say that samePerson must be symmetric, i.e., ```samePerson(p1, p2) == samePerson(p2, p1)```.

Finally, we can also define a prior, which incorporates our assumption about predicate values into the model. For example, the prior,

```
m.add rule: ~SamePerson(A,B), weight: 1
```

suggests that the model should assume that two people are not generally the same person. 

# Model: Data Loading

In order to load data, we must define a partition. We define the ```evidencePartition``` to store all of our knowledge about our predicates into the database.

```
def evidencePartition = data.getPartition("evidencePartition");
```

We can insert data into the data by manually inserting values, or loading from file. 

To manually insert data, we define an inserter for the specified partition, and insert accordingly.

```
def insert = data.getInserter(Name, evidencePartition);

/* Social Network A */
insert.insert(1, "John Braker");
insert.insert(2, "Mr. Jack Ressing");
insert.insert(3, "Peter Larry Smith");
insert.insert(4, "Tim Barosso");
insert.insert(5, "Jessica Pannillo");
insert.insert(6, "Peter Smithsonian");
insert.insert(7, "Miranda Parker");

/* Social Network B */
insert.insert(11, "Johny Braker");
insert.insert(12, "Jack Ressing");
insert.insert(13, "PL S.");
insert.insert(14, "Tim Barosso");
insert.insert(15, "J. Panelo");
insert.insert(16, "Gustav Heinrich Gans");
insert.insert(17, "Otto v. Lautern");
```

To load data from a file, you simply call the ```InserterUtils``` class to load delimited data. Here we show how to load data for the ```Network``` and ```Knows``` predicates from delimited text files, ```sn_network.txt``` and ```sn_knows.txt```.

```
def dir = 'data'+java.io.File.separator+'sn'+java.io.File.separator;

insert = data.getInserter(Network, evidencePartition)
InserterUtils.loadDelimitedData(insert, dir+"sn_network.txt");

insert = data.getInserter(Knows, evidencePartition)
InserterUtils.loadDelimitedData(insert, dir+"sn_knows.txt");
```


# Model: Inference


Now that we have set up our model and data loading, we are ready to enable inference to predict the unknown values of our predicates. 

### Database Preparation
We start by defining a second partition, ```targetPartition```, that holds the target values for which we want to predict. We then setup a database that takes in 3 arguments: 
- readPartition -- a partition that stores your ground knowledge
- toClose -- a set which indicates which predicates you want to close. Closing a predicate treats all of its atoms as observed and prevents prediction of those atom values
- writePartition -- a partition that stores the knowledge you predict

The syntax for this procedure is simple:
```
def targetPartition = data.getPartition("targetPartition");
Database db = data.getDatabase(targetPartition, [Network, Name, Knows] as Set, evidencePartition);
```
In order to make predictions, however, we must specify which atoms we want to predict (i.e., we must add such atoms to our targetPartition. 

For this example, we add all combinations of user pairs by considering the UniqueIDs used by our data (as assigned in the Data Loading section above). 
```
Set<GroundTerm> usersA = new HashSet<GroundTerm>();
Set<GroundTerm> usersB = new HashSet<GroundTerm>();
for (int i = 1; i < 8; i++)
	usersA.add(data.getUniqueID(i));
for (int i = 11; i < 18; i++)
	usersB.add(data.getUniqueID(i));

Map<Variable, Set<GroundTerm>> popMap = new HashMap<Variable, Set<GroundTerm>>();
popMap.put(new Variable("UserA"), usersA)
popMap.put(new Variable("UserB"), usersB)

DatabasePopulator dbPop = new DatabasePopulator(db);
dbPop.populate((SamePerson(UserA, UserB)).getFormula(), popMap);
dbPop.populate((SamePerson(UserB, UserA)).getFormula(), popMap);
```

### Running Inference

Now that our database is prepared, we can run inference simply with the following call:

```
MPEInference inferenceApp = new MPEInference(m, db, config);
inferenceApp.mpeInference();
inferenceApp.close();
```

To view how our inference app performed, we print the results of our predictions by printing all atomic values of ```SamePerson``` in our database:

```
println "Inference results with hand-defined weights:"
DecimalFormat formatter = new DecimalFormat("${symbol_pound}.${symbol_pound}${symbol_pound}");
for (GroundAtom atom : Queries.getAllAtoms(db, SamePerson))
	println atom.toString() + "${symbol_escape}t" + formatter.format(atom.getValue());
```

# Model: Weight Learning

When we defined our model, we specified a predefined ```weight``` for each rule. It may be the case that we would rather learn an optimal weight for each rule. In order to do so, we must provide evidence data from which we can learn.

In our example, evidence would be the 'true' alignment of our social networks, which we can load into another partition.

```
Partition trueDataPartition = data.getPartition("trueDataPartition");
insert = data.getInserter(SamePerson, trueDataPartition)
InserterUtils.loadDelimitedDataTruth(insert, dir + "sn_align.txt");
```

where ```sn_align.txt``` stores delimited data with truth values (e.g., 1,11,1.0 which says that the value of the atom ```SamePerson(1,11)=1.0```). 

Once we have evidence available, we can run weight learning with a few short calls. First, we open a database with our true data as the readPartition, and specify which predicates possess values that are fully observed (in this case, only ```SamePerson```). 
```
Database trueDataDB = data.getDatabase(trueDataPartition, [samePerson] as Set);
```

Then we call weight learning as follows:
```
MaxLikelihoodMPE weightLearning = new MaxLikelihoodMPE(m, db, trueDataDB, config);
weightLearning.learn();
weightLearning.close();
```

To see how our learning method did, we can view our weights by printing the model:

```
println "Learned model:"
println m
```

# Model: Evaluation

To test out our learned weights, we want to follow the process of data loading and populating again to load in a new example.

```
//Data Loading
Partition evidencePartition2 = data.getPartition("evidencePartition2");

insert = data.getInserter(Network, evidencePartition2)
InserterUtils.loadDelimitedData(insert, dir+"sn2_network.txt");

insert = data.getInserter(Name, evidencePartition2);
InserterUtils.loadDelimitedData(insert, dir+"sn2_names.txt");

insert = data.getInserter(Knows, evidencePartition2);
InserterUtils.loadDelimitedData(insert, dir+"sn2_knows.txt");

//Populating
def targetPartition2 = data.getPartition("targetPartition2");
Database db2 = data.getDatabase(targetPartition2, [Network, Name, Knows] as Set, evidencePartition2);

usersA.clear();
for (int i = 21; i < 28; i++)
	usersA.add(data.getUniqueID(i));
usersB.clear();
for (int i = 31; i < 38; i++)
	usersB.add(data.getUniqueID(i));

dbPop = new DatabasePopulator(db2);
dbPop.populate((SamePerson(UserA, UserB)).getFormula(), popMap);
dbPop.populate((SamePerson(UserB, UserA)).getFormula(), popMap);
```

And then, we run inference and print our results:

```
inferenceApp = new MPEInference(m, db2, config);
result = inferenceApp.mpeInference();
inferenceApp.close();

println "Inference results on second social network with learned weights:"
for (GroundAtom atom : Queries.getAllAtoms(db2, SamePerson))
	println atom.toString() + "${symbol_escape}t" + formatter.format(atom.getValue());
```