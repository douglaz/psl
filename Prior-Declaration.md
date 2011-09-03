The add method of PSLModel (model below) is used to specify a rule.

`m.add Prior.Simple, on : <predicateName>, weight: <weight>;`

This is equivalent to adding a unit clause ~<predicateName> with weight <weight>.
