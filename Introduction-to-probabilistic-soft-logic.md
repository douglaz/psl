Probabilistic Soft Logic (PSL) is a general purpose software toolkit for modeling probabilistic and relational domains, and could be used for a wide variety of applications such as link prediction and ontology alignment. PSL combines the strengths of two powerful theories -- <a href="http://www.amazon.com/Logical-Foundations-Artificial-Intelligence-Genesereth/dp/0934613311">logic</a> with its ability to succinctly represent complex phenomena, and <a href="http://pgm.stanford.edu/">probabilistic graphical models</a> with their ability to represent the uncertainty and incompleteness inherent in real-world knowledge. More concretely, PSL uses "soft" logic as its logical component and <a href="http://en.wikipedia.org/wiki/Markov_random_field">Markov networks</a> as its statistical model. 

In "soft" logic, logical constructs need not be strictly false(0) or true(1) but can take on values between 0 and 1 inclusive. Take the following logical formula as an example: `similarNames(X,Y) => sameEntity(X,Y)`, which encodes that if two persons X and Y have similar names, then they are the same entities. The truth value of "similarNames(X,Y)" and that of the entire formula can lie in the range [0,1]. The 'and'(^), 'or'(v) and 'not'(~) operators are defined using Lukasiewicz t-norms.

A ^ B = max{A+B-1,0}
A v B = min{A+B,1}
~A = 1-A

(Note that if the values of A and B are restricted to exactly false and true, then the logical operators work as they are conventionally defined.) PSL provides an interface in the Groovy<link> language to allow users to conveniently encode their knowledge of a particular domain declaratively in soft logic. 

These logical formulas are used as features to a Markov network, and each are associated with a weight in order to statistically model the interactions among them. The weights could either be specified manually or be learned from data with PSL's suite of learning algorithms. In addition, PSL provides sophisticated techniques for learning the weights of the formulas and for inferring the most likely answer to a user's query. The "softening" of the logical formulas allows the inference problem to be cast as a polynomial-time inference problem rather than as a (much more difficult) NP-hard combinatorial one.

For more details on PSL, please refer to the paper <a href="http://fodava.gatech.edu/files/reports/FODAVA-10-17.pdf"> Probabilistic Similarity Logic</a>. 

<To be cleaned up later today>