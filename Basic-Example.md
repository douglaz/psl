After ensuring that the [[prerequisites]] and [[examples|installing examples]] are installed, change to the directory containing the project of examples. Inside this project you will find the ```BasicExample.groovy``` example.  First, navigate to the parent directory of the `BasicExample.groovy` example:  

````
>> cd psl-example/src/main/java/edu/umd/cs/example  
````     
  
Here, you will find `BasicExample.groovy`.  This file provides an example of using the Groovy PSL syntax for defining predicates and rules, loading predicate data, running basic inferences, and learning rule weights. 

# Model: Predicates, Functions, and Constraints

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

In addition, ``BasicExample.groovy`` defines the function:
### Functions
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
- **IF** ( Network(A, snA) **AND** Network(B, snB) **AND** Name(A,X) **AND** Name(B,Y) **AND** SameName(X,Y) ) **THEN** SamePerson(A,B)

```
m.add rule : ( Network(A, snA) & Network(B, snB) & Name(A,X) & Name(B,Y)
	& SameName(X,Y) ) >> SamePerson(A,B),  weight : 5
```

Similarly, another rule we might define utilizes the knowledge of the SamePerson and Knows predicates to infer other values of the SamePerson predicate:

- **IF** ( Network(A, snA) **AND** Network(B, snB) **AND** SamePerson(A,B) **AND** Knows(A, Friend1) **AND** Knows(B, Friend2) ) **THEN** SamePerson(Friend1, Friend2)
```
m.add rule : ( Network(A, snA) & Network(B, snB) & SamePerson(A,B) & Knows(A, Friend1)
	& Knows(B, Friend2) ) >> SamePerson(Friend1, Friend2) , weight : 3.2
```

For more information on defining rules see [[defining rules]].

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

###
When you are ready, [[run | running a program]] BasicExample.groovy.  You should see some output that looks like:

````
Model Evidence:
PartialFunctional on samePerson(Entity, Entity)
PartialInverseFunctional on samePerson(Entity, Entity)
{( knows(A, tv1)[tv1] u knows(tv2, A)[tv2])} =={}(samePerson(Entity, Entity)) {( knows(B, tv3)[tv3] u knows(tv4, B)[tv4])} =: sameFriends__1(Entity, Entity)[]
DataCertainty
5.0 : ( ( ( ( name(A, X) ^ name(B, Y) ) ^ #NonSymmetric(A, B) ) ^ sameName(X, Y) ) ^ !( samePerson(A, B) ) )
Prior weight on samePerson(Entity, Entity) : 1.0
3.2 : ( ( samePerson(A, B) ^ #NonSymmetric(A, B) ) ^ !( sameFriends__1(A, B) ) )

--- Atoms: 
samePerson(5, 8) V=[0.12]
samePerson(2, 3) V=[0.12]
samePerson(2, 5) V=[0.13]
samePerson(4, 9) V=[0.21]
samePerson(12, 15) V=[0.17]
samePerson(4, 11) V=[0.13]
samePerson(2, 4) V=[0.12]
samePerson(8, 16) V=[0.15]
samePerson(14, 16) V=[0.1]
samePerson(14, 17) V=[0.13]
samePerson(12, 16) V=[0.2]
samePerson(8, 13) V=[0.18]
samePerson(2, 12) V=[0.23]
samePerson(8, 15) V=[0.12]
samePerson(8, 12) V=[0.18]
samePerson(8, 14) V=[0.17]
samePerson(14, 15) V=[0.15]
samePerson(2, 8) V=[0.12]
samePerson(2, 9) V=[0.15]
samePerson(4, 5) V=[0.17]
samePerson(13, 11) V=[0.31]
samePerson(15, 16) V=[0.1]
samePerson(15, 17) V=[0.19]
samePerson(5, 9) V=[0.14]
samePerson(1, 5) V=[0.13]
samePerson(9, 15) V=[0.21]
samePerson(5, 11) V=[0.12]
samePerson(3, 8) V=[0.38]
samePerson(9, 17) V=[0.17]
samePerson(5, 12) V=[0.23]
samePerson(1, 12) V=[0.13]
samePerson(1, 3) V=[0.11]
samePerson(3, 13) V=[0.23]
samePerson(1, 9) V=[0.13]
samePerson(11, 16) V=[0.1]
samePerson(3, 9) V=[0.1]
samePerson(13, 15) V=[0.11]
samePerson(11, 14) V=[0.17]
samePerson(13, 17) V=[0.13]
samePerson(1, 11) V=[0.13]
samePerson(11, 17) V=[0.17]
samePerson(1, 2) V=[0.12]
samePerson(9, 11) V=[0.2]
samePerson(5, 14) V=[0.22]
samePerson(11, 15) V=[0.16]
samePerson(9, 14) V=[0.18]
samePerson(3, 4) V=[0.16]
samePerson(3, 5) V=[0.12]
# Atoms: 48
Model Evidence:
PartialFunctional on samePerson(Entity, Entity)
PartialInverseFunctional on samePerson(Entity, Entity)
{( knows(A, tv1)[tv1] u knows(tv2, A)[tv2])} =={}(samePerson(Entity, Entity)) {( knows(B, tv3)[tv3] u knows(tv4, B)[tv4])} =: sameFriends__1(Entity, Entity)[]
DataCertainty
0.10961525576133078 : ( ( ( ( name(A, X) ^ name(B, Y) ) ^ #NonSymmetric(A, B) ) ^ sameName(X, Y) ) ^ !( samePerson(A, B) ) )
Prior weight on samePerson(Entity, Entity) : 0.1290623182788934
0.0 : ( ( samePerson(A, B) ^ #NonSymmetric(A, B) ) ^ !( sameFriends__1(A, B) ) )

--- Atoms: 
samePerson(3, 8) V=[0.21]
# Atoms: 1
````

The first important thing to notice from our output is the set of **samePerson** grounded atoms along with their corresponding soft values.  These groundings and soft values were obtained by loading a priori values into the **names** and **knows** closed predicates and running inference on our defined rules.

The next thing to notice is the learned rule weights that follow the **samePerson** groundings.  Here, we loaded data into the **samePerson** predicate and then learned the optimal weights of our rules.  For more information on running inferences in PSL see [[running inferences]].

Now that you've gone through your first example perhaps you'd like to try [[more examples | Example-Programs]].  Otherwise, you can return to [[getting started | Getting-started]] or visit the [[FAQ | FAQ]] page.