Predicates are added to a PSLModel (`model` below) by using its `add` method. <br/>

`model.add predicate: <predicateName>, <arg1> : <TextOrEntity>, <arg2> : <TextOrEntity>, type: <BooleanOrSoftTruth>;`<br/>

* `<predicateName>` is the name of a predicate in quotes, e.g., `"authorName"`
* `<arg1>` and `<arg2>` are arguments of `<predicateName>`.
* `<TextOrEntity>` takes one of two keywords `Entity` or `Text`. Intuitively, `Text` represents an attribute of an object, and `Entity` refers to an object.
* `<BooleanOrSoftTruth>` takes two values: `PredicateTypes.BooleanTruth` and `PredicateTypes.SoftTruth`. The former indicates that `<predicateName>` takes one of the two values in {0,1}, and the latter indicates it takes any value in the range [0,1]. A query predicate whose value is to be inferred must have type `PredicateTypes.SoftTruth`.  

An example of a declaration of a predicate that represents an author's name is: <br/><br/>

`model.add predicate: "authorName" , author : Entity, name : Text, type: PredicateTypes.BooleanTruth;`

