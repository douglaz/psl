To perform maximum a posteriori (MAP) inference, use the `mapInference` method of `PSLModel`.

`result = model.mapInference(data.getDatabase(write: <outputPartitionID>, read : <evidencePartitionID>));`

* `data` is a `DataStore` object that is used to store ground atoms.
* `<outputPartitionID>` is an integer ID of the partition that is to be used to store inferred truth values
* `<evidencePartitionID>` is the partitionID storing evidence ground atoms

The inferred truth values can be printed using `result.printAtoms(<predicateName>, true)`, which prints ground atoms of `<predicateName>` with truth values larger than 0.0.
