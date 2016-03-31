The `add` method of PSLModel `model` (below) is used to specify a rule.

`model.add Prior.Simple, on : <predicateName>, weight: <weight>;`

`~<predicateName>, weight: <weight>;`

We assume that most of the groundings of `<predicateName>` is false, and hence `~<predicateName>` has positive weight.
