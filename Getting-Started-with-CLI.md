## Setup

PSL requires that you have [[Java installed | Install Java ]]. 

The PSL jar file `psl-cli-2.0-SNAPSHOT.jar` already contains all required PSL libraries that you need to be able to run your PSL programs. You can find a current snapshot of this `.jar` file from our [resources directory](http://linqs.cs.umd.edu/resources/psl-cli-2.0-SNAPSHOT.jar) until we finalize our v2.0 release.
## Running your first program
Let's first download the files for our example program, run it and see what it does! 

In this program, we'll use information about known locations of some people and friendship networks between people to collectively infer where some other people live. This form of inference is called collective classification. We'll first run the program and see the output. We will be working from the command line so open up your shell or terminal.

### Download the simple example
You can download the files needed for our simple first example program from [Simple CLI Example Files](http://linqs.cs.umd.edu/resources/psl-cli-example.zip)
This will a create a new `PSLCLIFirstExample` directory in your current directory.

### Run your first PSL program

Change directories to the new `PSLCLIFirstExample` that was created in your current directory in your open command line shell. From there, run the following command:

`java -jar psl-cli-2.0-SNAPSHOT.jar -infer -model simple_cc.psl -data simple_cc.data`

You should now see output that looks like this: 

```
data:: loading:: ::starting
data:: loading:: ::done
model:: loading:: ::starting
Model:
{10.0} ( KNOWS(P1, P2) & LIVES(P1, L) ) >> LIVES(P2, L) {squared}
{10.0} ( KNOWS(P2, P1) & LIVES(P1, L) ) >> LIVES(P2, L) {squared}
{2.0} ~( LIVES(P, L) ) {squared}

model:: loading:: ::done
operation::infer ::starting
operation::infer inference:: ::starting
operation::infer inference:: ::done
LIVES(Alex, Maryland) = 0.9086212203617681
LIVES(Jay, Maryland) = 1.0
LIVES(Ben, Maryland) = 1.0
LIVES(Steve, Maryland) = 0.9086212203617681
PERSON(Steve) = 1.0
PERSON(Ben) = 1.0
PERSON(Jay) = 1.0
PERSON(Alex) = 1.0
KNOWS(Steve, Ben) = 1.0
KNOWS(Alex, Jay) = 1.0
KNOWS(Steve, Jay) = 1.0
KNOWS(Alex, Ben) = 1.0
LOCATION(Maryland) = 1.0
operation::infer ::done
```

### What did it do?
Now that we've run our first program that performs collective classification to infer where some people live based on some known facts about living locations and friendship links, let's understand the steps that we went through to infer the unknown values: defines the underlying model, provided data to the model and ran inference to classify the unknown values.

#### Defining a Model
A model in PSL is a set of weighted logical rules. 

The model is defined inside a text file with the format `.psl`. We describe the collective location classification model in the file `simple_cc.psl`. Let's have a look at the rules that make up our model: 
```
10: Knows(P1,P2) & Lives(P1,L) -> Lives(P2,L) ^2
10: Knows(P2,P1) & Lives(P1,L) -> Lives(P2,L) ^2
2: ~Lives(P,L) ^2
```
The model is expressing the intuition that people that know one another live in the same location. 
The integer values at the beginning of rules indicate the weight of the rule. Intuitively, this tells us the relative importance of satisfying this rule compared to the other rules.
The `^2` at the end of the rules indicates that the hinge-loss functions based on groundings of these rules are squared, for a smoother tradeoff. For more details on hinge-loss functions and squared potentials, see the publications on our [PSL webpage](http://psl.umiacs.umd.edu). 

#### Loading the Data
Logical rules consist of predicates. The names of the predicates used in our model and possible substitutions of these predicates with actual entities from our network are defined inside the file `simple_cc.data`. Let's have a look:
```
predicates:
  Person/1: closed
  Location/1: closed
  Knows/2: closed
  Lives/2: open

observations:
  Person : 
  - person_obs.txt
  - person_obs2.txt
  Location : location_obs.txt
  Knows : knows_obs.txt
  Lives : lives_obs.txt

targets: 
  Lives : lives_targets.txt

truth: 
  Lives : lives_truth.txt
```
In the `predicate` section, we list all the predicates that will be used in logical rules that define the model. The keyword `open` indicates that we want to infer some substitutions of this predicate while `closed` indicates that this predicate is fully observed. I.e. all substitutions of this predicate have known values and will behave as evidence for inference. 

For our simple example, we fully observe the network of people that know each other and thus, `knows` is a closed predicate. We know living locations for some of the people in the network but wish to infer the others, making `lives` an open predicate.

In the `observations` section, for each predicate for which we have observations, we specify the name of the `.txt` file containing the observations. For example, `knows_obs.txt` and `lives_obs.txt` specifies which people know each other and where some of these people live, respectively.

The `targets` section specifies a `.txt` file that, for each open predicate, lists all substitutions of that predicate that we wish to infer. In `lives_targets.txt`, we specify the people whose location we want to infer based on the `knows` network and the known locations of some of the people.

The `truth` section specifies a `.txt` file that provides a set of ground truth observations for each open predicate. Here, we give the actual values for the `lives` predicate for all the people in the network as training labels. We describe the the general data loading scheme in more detail in the sections below.

#### Inferring the Missing Values

When we run the `java -jar psl-cli-2.0-SNAPSHOT.jar -infer -model simple_cc.psl -data simple_cc.data` command with the `-infer` flag, PSL's inference engine substitutes values from the data files into the logical rules of the collective location classification model and infers whether entities `Steve` and `Alex` live in `Maryland`.

## Writing PSL Rules

To create a PSL model, you should define a set of weighted logical rules in a `.psl` file. Let's go over the basic logical syntax to write rules. Consider this very general rule form:

` w: P(A,B) & Q(B,C) -> R(A,C) ^2  `

The first part of the rule, `w`, is an integer value that specifies the weight of the rule. In this example, `P`, `Q` and `R` are predicates. Logical rules consist of the rule "body" and rule "head." The body of the rule appears before the `->` which denotes logical implication. The body can have one or more predicates conjuncted together with the `&` that denotes logical conjunctions. The head of the rule should be a single predicate. The predicates that appear in the body and head can be any combination of open and closed predicate types.

To see more examples of logically templated models in the command line interface, see the [[Command Line Interface Examples | CLI Examples ]]. For best practices, tips and tricks to design good, semantically meaningful models, see our [[Modeling Tips and Tricks | Modeling Tips and Tricks]]. 

## Organizing your Data 
In a `.data` file, you should first define your `predicates:` as shown in the above example. Use the `open` and `closed` keywords to characterize each predicate. 

An `closed` predicate is a predicate whose values are always observed. For example, the `knows` predicate from the simple example is closed because we fully observe the entire network of people that know one another. On the other hand, an `open` predicate is a predicate where some values may be observed, but some values are missing and thus, need to be inferred.  

As shown above, then create your `observations:`, `targets:` and `truth:` sections that list the names of `.txt` files that specify the observed values for predicates, values you want to infer for open predicates and observed ground truth values for open predicates. 

For all predicates, all possible substitutions should be specified either in the target files or in the observation files. The observations files should contain the known values for all closed predicates and can contain some of the known values for the open predicates. The target files tell PSL which substitutions of the open predicates it needs to infer. Target files cannot be specified for closed predicates as they are fully observed. 

The truth files provide training labels in order learn the weights of the rules directly from data. This is similar to learning the weights of coefficients in a logistic regression model from training data. Weight learning is described below in greater detail.

## Running Inference

Run inference with the general command:

`java -jar psl-cli-2.0-SNAPSHOT.jar -infer -model [name of model file].psl -data [name of data file].data`

When we run inference, the inferred values are outputted to the screen as shown for our example above. If you want to write the outputs to a file and use the inferred values in various ways downstream, you can use:

`java -jar psl-cli-2.0-SNAPSHOT.jar -infer -model [name of model file].psl -data [name of data file].data -output [directory to write output files]`

Values for all predicates will be output as `.csv` files in the specified output directory.

With the inferred values, some downstream tasks that you can perform are:
- if you have a gold standard set of labels, you can evaluate your model by computing standard metrics like accuracy, AUC, F1, etc.
- you may want to use the predicted outputs of PSL as inputs for another model.
- you may want to visualize the predicted values and use the outputs of PSL as inputs to a data visualization program.

## Learning Rule Weights
We see above that in our example, we explicitly stated the weights for each rule. Think of these weights as dictating the relative importance of each rule, just as the weights of logistic regression or SVM features. Instead of explicitly giving the weights, we can also learn the weights from training labels.

To perform weight learning instead of inference, use the command:

`java -jar psl-cli-2.0-SNAPSHOT.jar -learn -model [name of model file].psl -data [name of data file].data`

Running the weight learning command outputs a `.psl` model file with the learned weights and logical rules. You can use this produced model file for running inference with the learned model.

PSL provides gradient-descent based weight learning algorithms that treat the files specified in the `truth:` section of your `.data` file as the training labels. 