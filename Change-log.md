Version 1.1.1 (https://github.com/linqs/psl/tree/1.1.1)

* Improved examples, which demonstrate database population for non-lazy inference and learning
* Support for learning negative weights (limited to inference methods for discrete MRFs that support negative weights)
* Bug fixes

Version 1.1 (https://github.com/linqs/psl/tree/1.1)

* An improved Groovy interface. Try the new examples via https://github.com/linqs/psl/wiki/Installing-examples to learn the new interface.
* New, improved psl-core architecture
* Much faster inference based on the alternating direction method of multipliers (ADMM).
* Improved max-likelihood weight learning
* New max-pseudolikelihood and large-margin weight learning
* Many bug fixes and minor improvements.

Version 1.0.2 (https://github.com/linqs/psl/tree/1.0.2)

* Fixed bugs in HomogeneousIPM and MOSEK add-on caused by bug in parallel colt when using selections from large, sparse matrices.
* Fixed bug when learning weights of programs which contain set functions.
* Reduced memory footprint of HomogeneousIPM and matrices produced by ConicProgram.

Version 1.0.1 (https://github.com/linqs/psl/tree/1.0.1)

* Fixed bug in optimization program when the same atom was used more than once in a ground rule or constraint.
* Added release profile to parent POM for better packaging.
* Minor changes to archetypes.

Version 1.0 (https://github.com/linqs/psl/tree/1.0)