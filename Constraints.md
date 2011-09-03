We can declare constraints on the groundings of a binary predicate as follows:

`model.add PredicateConstraint.<constraintType> , on : <PredicateName>;`

* `<PredicateName>` is the name of the predicate on which the constraint is applied.
* `<constraintType>` can take one of four values: `Functional`, `PartialFunctional`, `InverseFunctional`, and `PartialInverseFunctional`. A `Functional` constraint enforces the condition that for each possible constant c, the values of all groundings of `<PredicateName>(Var,c)` sum to exactly 1. A `PartialFunctional` constraint ensures that the sum is less than or equal to 1. `InverseFunctional` and `PartialInverseFunctional` are similarly defined for the second argument of `<PredicateName>`.
