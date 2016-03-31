The `add` method of a PSLModel `model` (below) is used to specify a rule.

`m.add rule: ~<predicateName>, weight: <weight>;`

We assume that most of the groundings of `<predicateName>` is false, and hence `~<predicateName>` has positive weight.
