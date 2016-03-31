The PSL software uses concepts from the [[PSL paper | http://arxiv.org/abs/1505.04406]], and introduces new ones for advanced data management and machine learning. On this page, we define the commonly used terms and point out the corresponding classes in the codebase.

## Preliminaries

**Hinge-loss Markov random field:** A factor graph defined over _continuous_ variables in the [0,1] interval with (log) factors that are hinge-loss functions. Many classes in PSL work together to implement the functionality of HL-MRFs, but the class for storing collections of hinge-loss potentials, which define HL-MRFs, is [[GroundRuleStore.java | https://github.com/linqs/psl/blob/master/psl-core/src/main/java/edu/umd/cs/psl/application/groundrulestore/GroundRuleStore.java]].

## Syntax

**PSL Program:** A set of rules, each of which is a template for hinge-loss potentials or hard linear constraints. When grounded over a base of ground atoms, a PSL program induces a HL-MRF conditioned on any specified observations. Implemented in [[Model.java | https://github.com/linqs/psl/blob/master/psl-core/src/main/java/edu/umd/cs/psl/model/Model.java]]

**Rule:**

**Logical rule:**

**Arithmetic rule:**

**Unweighted rule:**

**Weighted rule:**

## Data Management

**Data Store:** An entire data repository, such as a relational database management system (RDBMS). Implemented in [[DataStore.java | https://github.com/linqs/psl/blob/master/psl-core/src/main/java/edu/umd/cs/psl/database/DataStore.java]].

## Machine Learning