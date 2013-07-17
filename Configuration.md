Many components of the PSL software have modifiable parameters and options, called properties. Every property has a key, which is a string that should uniquely identify it. These keys are organized into a namespace hierarchy, with each level separated by dots, e.g., `conicreasoner.distribution` is the type of probability distribution used by the `edu.umd.cs.psl.reasoner.ConicReasoner` class. Every property has a type and a default value, which is the value the object will use unless a user overrides it. Every class with properties documents them by declaring their keys as public static final Strings, with Javadoc describing the corresponding property's type and semantics. Another public static final member declares the default value for that property.

### Bundles

Users of the PSL software can specify property values by grouping them into bundles, which are objects that implement the `edu.umd.cs.psl.config.ConfigBundle` interface. Every bundle has a name and a map from property keys to values. A configurable component takes a `ConfigBundle` as an argument in its constructor and queries it with a property key and a default value. If the bundle does not map the key to a value, it returns the provided default.

PSL components also pass their bundles to components that they create, so a user can group their property values into a single bundle, pass it into a component with which they interact, and the values will be used by the entire stack of components. Any properties that don't belong to a particular component will be ignored by that component.

### The psl.properties file

A simple way to specify bundles is in a file named `psl.properties` on the classpath. The standard location for this file is `<project root>/src/main/resources/psl.properties`. Each key-value pair should be specified on its own line with a `<bundle>.<key> = <value>` format. For example,

```properties
# This is an example properties file for PSL.
# 
# Options are specified in a namespace hierarchy, with levels separated by '.'.
# The top levels are called bundles. Use the ConfigManager class to access them.

# This line specifies an enum value
example.conicreasoner.distribution = linear

# This line specifies a factory value (see edu.umd.cs.psl.config.Factory)
example.conicreasoner.conicprogramsolver = edu.umd.cs.psl.optimizer.conic.ipm.HomogeneousIPMFactory

# This line specifies a double value, but in a DIFFERENT bundle!
secondExample.hipm.sigthreshold = 10e-6
```

### The ConfigManager object

The standard way to create bundles is with an instance of the `edu.umd.cs.psl.config.ConfigManager` class.
`ConfigManager` uses the [Singleton pattern](http://en.wikipedia.org/wiki/Singleton_pattern). The `ConfigManager` instance will read `psl.properties` to generate bundles. Then a bundle can be instantiated with the code

```java
ConfigBundle bundle = ConfigManager.getManager().getBundle("example");
```