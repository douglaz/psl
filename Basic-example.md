After [[building and installing| building-psl]] the PSL software we are now ready to run an example PSL program.  PSL examples are found in the psl-examples package.  Inside this package you will find the ```BasicExample.groovy``` example.  First, navigate to the parent directory of the `BasicExample.groovy` example:  
  

````
>> cd <local path to psl-examples>/src/main/java/edu/umd/cs/psl/example  
````     
  
Here, you will find `BasicExample.groov`y.  This file provides an example of using the groovy PSL syntax for defining predicates and rules, loading predicate data, running basic inferences, and learning rule weights.  BasicExample.groovy defines 4 predicates:

- **name**
- **knows**
- **samePerson**

See [[defining predicate types]] for information on defining predicate types.  The **name** and **knows** predicates are both defined to be closed, while **samePerson** is defined to be open.  In addition, BasicExample.groovy defines the function:

- **sameName**

See [[defining functions]] for information on defining functions.  For the purposes of our example, **sameName** is a function that simply a function that maps a pair of entities to 1 if their  are identical and 0 otherwise.  In addition to the above predicates and function, we also define the following rules which are written in pseudo code.  For information on writing rules using PSL's syntax please see [[writing rules]].      
     
````
if ( name(A,X) AND name(B,Y) AND (A != B) AND sameName(X,Y) ) THEN samePerson(A,B)
````

When you are ready, [[run]] BasicExample.groovy.  You should see some output that looks like:

````

````

  

