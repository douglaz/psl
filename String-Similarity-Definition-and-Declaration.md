A customized similarity function can be created by implementing the `AttributeSimilarityFunction` interface in a Groovy file. It must return a value in [0,1]. For example:

`class MyStringSimilarity implements AttributeSimilarityFunction` <br/>
`{`<br/>
&nbsp;&nbsp;`@Override` <br/>
&nbsp;&nbsp;`public double similarity(String a, String b) { return a.equals(b)?1.0:0.0; }` <br/>
`}` <br/>

A function comparing the similarity between two entities or text can then be declared as follows:

`m.add function: <functionName> , <arg1>: <EntityOfText>, <arg2>: <EntityOrText>, implementation: new <SimilarityFunction>()`

* `<functionName>` is the name of the function, e.g., `"sameName"`.
* `<arg1>` and `<arg2>` are the identifiers for the function's arguments, e.g., `name1` and `name2`.
* `<SimilarityFunction>` is the name of the class implementing the `AttributeSimilarityFunction` interface, e.g., `MyStringSimilarity`.

A function can be used in the same manner as a predicate in rules.