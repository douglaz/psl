To declare a predicate with set semantics, use the `add` method of a PSLModel (`model` below).

`model.add setcomparison: <nameOfSetPredicate> , using: new <setComparisonType>, on : <nameOfTargetPredicate>;`

* `<nameOfSetPredicate>` is the name of a set predicate
* `<setComparisonType>` takes one of three values: `AggregateSetEquality()`, `AggregateSetCrossEquality()`, and `EvidSetMin(n)`. Their semantics will be described in the example below.
* `<nameOfBinaryTargetPredicate>` is the predicate whose arguments are populated by the arguments of the set predicate.

We shall illustrate the use of a set predicate in the context of the following rule: if two papers `P1` and `P2` have the same set of authors, then they are likely the same paper.

`model.add setcomparison: "sameAuthorSet" , using: new AggregateSetEquality(), on : sameAuthor;`

`( sameAuthorSet({P1.authorOf(inv)},{P2.authorOf(inv)}) ) >> samePaper(P1,P2)`

A set predicate is a binary predicate (i.e., it takes two arguments). Each of its argument is a set of constants. In the example, the first set argument is provided by `{ P1.authorOf(inv) }`. This evaluates to the set of authors `{A}` such that `authorOf(A, P1)` is true. The `(inv)` indicates that we should use the first argument of `authorOf` in creating the set argument. If we want the the second argument, we can omit `(inv)`. PSL sums the values of all `sameAuthor(A,A')` that can be formed from the cross-product of the constants in `{A}` and `{A'}`. 

Next this sum is normalized to fall within [0,1] depending on `<setComparisonType>`. 
When `AggregateSetEquality()` is used, the sum is divided by the minimum cardinality of `{A}` and `{A'}`. Intuitively, this assumes a 1-to-1 mapping between `{A}` and `{A'}`. When `AggregateCrossEquality()` is used, the sum is divided by the product of the cardinaliies of `{A}` and `{A'}`, assuming a many-to-many mapping. When `EvidMinSet(n)` is used, this sum is divided by `n`. Intuitively, this represents a "minimum" construct -- the set predicate evaluates to 1.0 if the sum is at least `n`. With `EvidMinSet`, the values of `<nameOfTargetPredicate>` must be provided as evidence.




