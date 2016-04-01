The job of a Weight Learning Application is to **use data** to learn the weights of each rule in a PSL model.   

##Syntax
In weight learning we follow the structure below:

```<WeightLearningApplication> weightLearner = new <WeightLearningApplication>(<model>, <targetDatabase>, <groundTruthDatabase>, <config>)```

- ```<model>``` is the model specified by your PSL program. 
- ```<targetDatabase>``` is a database which contains all of the atoms for which you would like to infer values. When you create this database, the target predicate will be open. 
- ```<groundTruthDatabase>``` is a database which contains the known values of the atoms for which you are inferring values in the targetDatabase. When you create this database the predicates should be closed. 
- ```<config>``` is your [[config bundle | Configuration]]. 

A Weight Learning Application is one of the following: 

- ```MaxLikelihoodMPE```
- ```MaxPseudoLikelihood```
- ```MaxMargin```

After weight learning, the learned `PSLModel` can be printed using `println model`.

To see the weight learning code look [[here | https://github.com/linqs/psl/tree/develop/psl-core/src/main/java/edu/umd/cs/psl/application/learning/weight]]. 

##Example

Let's return to our [[basic example | Basic Example]]. In this example we are predicting whether two users in different networks are the same person. That is we want to learn the values of all unobserved ```samePerson(P1,P2)``` atoms. 

To create the targetDatabase we do the following:

```Database targetDatabase = data.getDatabase(targetPartition, [Network, Name, Knows] as Set, evidencePartition);```

Notice that samePerson is not included in the list of closed predicates.

To create the groundTruthDatabase we first load all known ```samePerson ``` values into trueDataPartition. We then create the database as follows:

 ```Database trueDataDB = data.getDatabase(trueDataPartition, [samePerson] as Set);```

Notice that samePerson(), is now included in the list of closed predicates. 
