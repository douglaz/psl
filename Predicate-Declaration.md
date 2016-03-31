Predicates are added to a PSLModel `model` (below) by using its `add` method. <br/>

`model.add predicate: <predicateName>, types: [<argumentTypeOne>,...,<argumentTypeX>]`<br/>

* `<predicateName>` is the name of a predicate in quotes, e.g., `"authorName"`
* `<argumentTypeX>` is the type of argument accepted by this predicate. Possible types include: 
    * `ConstantType.Double`
    * `ConstantType.Integer`
    * `ConstantType.Long`
    * `ConstantType.String`
    * `ConstantType.Date`   

An example of a declaration of a predicate that represents an author's name is: 

`model.add predicate: "authorName" , types: [ConstantType.String]`

An example of a predicate that represents a friendship between two people is: 

`model.add predicate: "Friends" , types: [ConstantType.UniqueID,ConstantType.UniqueID]`

To take a look at the relevant code look [[here| https://github.com/linqs/psl/blob/develop/psl-core/src/main/java/edu/umd/cs/psl/model/term/ConstantType.java]]. 

