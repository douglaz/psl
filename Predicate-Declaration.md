Predicates are added to a PSLModel `model` (below) by using its `add` method. <br/>

`model.add predicate: <predicateName>, types: [<argumentTypeOne>,<argumentTypeTwo>]`<br/>

* `<predicateName>` is the name of a predicate in quotes, e.g., `"authorName"`
* `<argumentTypeOne>` is the type of argument accepted by this predicate. Possible types include: `
    * `ConstantType.Double`
    * `ConstantType.Integer`
    * `ConstantType.Long`
    * `ConstantType.String`
    * `ConstantType.Date`   

An example of a declaration of a predicate that represents an author's name is: <br/><br/>
`model.add predicate: "authorName" , types: [ConstantType.String]`

