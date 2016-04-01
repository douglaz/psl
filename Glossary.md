The PSL software uses concepts from the [[PSL paper | http://arxiv.org/abs/1505.04406]], and introduces new ones for advanced data management and machine learning. On this page, we define the commonly used terms and point out the corresponding classes in the codebase.

## Preliminaries

**Hinge-loss Markov random field:** A factor graph defined over _continuous_ variables in the [0,1] interval with (log) factors that are hinge-loss functions. Many classes in PSL work together to implement the functionality of HL-MRFs, but the class for storing collections of hinge-loss potentials, which define HL-MRFs, is [[GroundRuleStore.java | https://github.com/linqs/psl/blob/master/psl-core/src/main/java/edu/umd/cs/psl/application/groundrulestore/GroundRuleStore.java]].

**Ground atom:** A logical relationship corresponding to a random variable in a HL-MRF. For example, `Friends("Steve", "Jay")` is an alias for a specific random variable. Implemented in [[GroundAtom.java | https://github.com/linqs/psl/blob/master/psl-core/src/main/java/edu/umd/cs/psl/model/atom/GroundAtom.java]]

**Random variable atom:** A ground atom that 

## Syntax

**PSL Program:** A set of rules, each of which is a template for hinge-loss potentials or hard linear constraints. When grounded over a base of ground atoms, a PSL program induces a HL-MRF conditioned on any specified observations. Implemented in [[Model.java | https://github.com/linqs/psl/blob/master/psl-core/src/main/java/edu/umd/cs/psl/model/Model.java]]

**Rule:**

**Logical rule:**

**Arithmetic rule:**

**Unweighted rule:**

**Weighted rule:**

## Data Management

**Data Store:** An entire data repository, such as a relational database management system (RDBMS). Implemented in [[DataStore.java | https://github.com/linqs/psl/blob/master/psl-core/src/main/java/edu/umd/cs/psl/database/DataStore.java]].

**Partition:** A logical division of ground atoms in a data store. Implemented in [[Partition.java | https://github.com/linqs/psl/blob/master/psl-core/src/main/java/edu/umd/cs/psl/database/Partition.java]].

**Database:** A logical view of a data store, constructed by specifying a write partition and one or more read partitions of a data store. Implemented in [[Database.java | https://github.com/linqs/psl/blob/master/psl-core/src/main/java/edu/umd/cs/psl/database/Database.java]].

**Open Predicate:** A predicate whose atoms can be unobserved.The only time a ground atom will be loaded as a random variable atom is when it is stored in the database's write partition and its predicate is _not_ specified as closed. Otherwise it will be loaded as an observed atom. Whether a predicate is open or closed is specific to each database.

**Closed Predicate:** A predicate whose atoms are always observed. The only time a ground atom will be loaded as a random variable atom is when it is stored in the database's write partition and its predicate is _not_ specified as closed. Otherwise it will be loaded as an observed atom. Whether a predicate is open or closed is specific to each database.

## Machine Learning