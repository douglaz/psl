The `add` method of PSLModel `model` (below) is used to specify a rule.

`model.add Prior.Simple, on : <predicateName>, weight: <weight>;`

`~<predicateName>, weight: <weight>;`

This is equivalent to adding a unit clause `~<predicateName>` with positive weight `<weight>`. We assume that most of the groundings of `<predicateName>` is false, and hence `~<predicateName>` has positive weight.
