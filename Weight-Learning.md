The job of a Weight Learning Application is to **use data** to learn the weights of each rule in a PSL model.   

In weight learning we follow the structure below:

```<WeightLearningApplication weightLearner> = new <WeightLearningApplication>(<model>, <targetDatabase>, <groundTruthDatabase>, <config>)```

- ```<model>``` is the model specified by your PSL program. 
- ```<targetDatabase>``` is a database which contains all of the atoms for which you would like to infer values. When you create this database, the target atoms will be open. 
- ```<groundTruthDatabase>``` is a database which contains the known values of the atoms for which you are inferring values in the targetDatabase.
- ```<config>``` is your [[config bundle | Configuration]]. 

A Weight Learning Application is one of the following: 

- ```MaxLikelihoodMPE```
- ```MaxPseudoLikelihood```
- ```MaxMargin```

After weight learning, the learned `PSLModel` can be printed using `println model`.

To see the weight learning code look [[here | https://github.com/linqs/psl/tree/develop/psl-core/src/main/java/edu/umd/cs/psl/application/learning/weight]]. 

