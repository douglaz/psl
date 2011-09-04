To perform weight learning, a `WeightLearningConfiguration` object needs to be first created to set the weight learning parameters.

`WeightLearningConfiguration config = new WeightLearningConfiguration();`

The parameters are:

* `config.setLearningType(WeightLearningConfiguration.Type.LBFGSB);`  //Use L-BFGS-B optimization
* `config.setPointMoveConvergenceThres(1e-5);` //convergence threshold of weight optimization.
* `config.setMaxOptIterations(1000);` //max iterations of weight optimizations
* `config.setParameterPrior(1);` // = 1/variance, where variance is that of the Gaussian prior over the weights.
* `config.setRuleMean(1);` //mean of Gaussian prior over the rule weights
* `config.setUnitRuleMean(1);` //mean of Gaussian of prior rules (i.e., prior on the query predicates)
* `config.setActivationThreshold(1e-10);` //activation threshold of ground atoms (below this threshold, consider the atom to be false). The smaller the value, the larger the number of atoms activated, the larger the memory consumption, the longer inference and weight learning take to run.

To run weight learning use the `learn` method of `PSLModel`:

`model.learn <DataStoreObject>, evidence : <evidencePartitionID>, infered: <queryPredicatePartitionID>, close : [<queryPredicate1>,<queryPredicate2>,...], config: <WeighLearingconfigurationObject>;`

* `<DataStoreObject>` is the `DataStore` object that contains databases of ground atoms and their truth values
* `<evidencePartitionID>` is the ID of the `DataStore` partition storing evidence ground atoms
* `<queryPredicatePartitionID>` is the ID of the `DataStore` partition storing query ground atoms
* `<queryPredicate*>` are the names of query predicates
* `<WeighLearingconfigurationObject>` is the object created to store the weight learning parameters

After weight learning, the learned `PSLModel` can be printed using `println model`.

