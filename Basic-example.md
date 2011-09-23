After ensuring that the [[prerequisites]] and [[examples|install examples]] are installed, change to the directory containing the project of examples. Inside this project you will find the ```BasicExample.groovy``` example.  First, navigate to the parent directory of the `BasicExample.groovy` example:  

````
>> cd psl-example/src/main/java/edu/umd/cs/example  
````     
  
Here, you will find `BasicExample.groovy`.  This file provides an example of using the Groovy PSL syntax for defining predicates and rules, loading predicate data, running basic inferences, and learning rule weights.  'BasicExample.groovy' defines 4 simple predicates:
### Predicates

- **name**

```
m.add predicate: "name" , person: Entity, string : Text
```
- **knows**

```
m.add predicate: "knows" , person1: Entity, person2 : Entity
```
- **samePerson**

```
m.add predicate: "samePerson", person1: Entity, person2: Entity, open: true
```

See [[defining predicate types]] for information on defining predicate types.  The **name** and **knows** predicates are both defined to be closed, while **samePerson** is defined to be open.  In addition, BasicExample.groovy defines the function:
### Functions
- **sameName**

```
m.add function: "sameName" , name1: Text, name2: Text, implementation: new LevensteinStringSimilarity()
```

See [[defining functions]] for information on defining functions.  For the purposes of our example, **sameName** is a function that maps a pair of entities to 1 if their names are identical and 0 otherwise.  In addition to the above predicates and function, we also define the following rules which are written both with pseudo code and their corresponding PSL syntax below.  For information on writing rules using PSL's syntax please see [[writing rules]].      
### Rules
- **IF** ( name(A,X) **AND** name(B,Y) **AND** (A != B) **AND** sameName(X,Y) ) **THEN** samePerson(A,B)

```
m.add rule : ( name(A,X) & name(B,Y) & (A ^ B) & sameName(X,Y) ) >> samePerson(A,B),  weight : 5
```
In addition to simple rules, we also specify a "set rule".

- **IF** samePerson(A,B) **AND** (A != B) **THEN ForAll** (x **in** A.knows) **Exists** (y **in** B.knows) **SuchThat** 
 samePerson(x, y) **AND** **ForAll** (x **in** B.knows) **Exists** (y **in** A.knows) **SuchThat** samePerson(x,y)

````
m.add setcomparison: "sameFriends" , using: SetComparison.Equality, on : samePerson
m.add rule :  (samePerson(A,B) & (A ^ B )) >> sameFriends( {A.knows + A.knows(inv) } , {B.knows + B.knows(inv) } ) , weight : 3.2
````
For more information on defining rules see [[defining rules]].

### Inferences/Output
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
