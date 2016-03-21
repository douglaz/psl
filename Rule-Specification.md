The `add` method of `PSLModel` (`model` below) is used to specify a rule.

`model.add rule : ( B1(V1,V2) & B2(V3,V4) & ... & B5(V5,V6) ) >> H(V1,V3,V6), weight : <weight>`

* `B1,B2,...B5,` and `H` are predicate symbols
* `V1,V2,...,V6` are arguments of the predicates. Variables are in upper case. To specify constants as arguments, the `is` operator is used, e.g., `( B1(V1,V2) & V1.is("constant1") ) >> H(V1,V2)`. A literal can be negated if all its arguments appear in non-negated literals.
* `&` is the logical and operator, and `>>` is the implication operator
* `<weight>` is a real-number that is the weight of the rule. If the weight is to be learned from data, then the specified weight is ignored. Otherwise, it is used during inference.
* To specify infinite weight(i.e. a hard rule) use `constraint : true` instead of `weight : <weight>`.



 