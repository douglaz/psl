# Getting Started with the Command Line Interface
## Setup
### Verify you have Java installed
Open up a command line terminal and type `java -version` to verify that you have Java installed. Any version of Java will be fine.
If you don't have Java installed, download and install [Java](http://java.com/en/).

### Download the PSL jar file
The PSL jar file "psl-cli-2.0-SNAPSHOT.jar" already contains all the core PSL source code you need to be able to run your PSL programs. You can find this jar file under [Downloads on our PSL webpage](http://psl.umiacs.umd.edu/downloads/)
## Running your first program
Let's first download the files for our example program, run it and see what it does! We will be working from the command line so open up your shell or terminal.
### Download the simple example
You can download the files needed for our simple first example program on our "Downloads" page under [Simple CLI Example Files](http://psl.umiacs.umd.edu/downloads/simple_CLI_example)

This will a create a new "PSLCLIFirstExample" directory in your current directory.

### Run your first PSL program

Change directories to the new "PSLCLIFirstExample" that was created in your current directory in your open command line shell. From there, run the following command:

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

#### Defined a model

#### Loaded data

#### Inferred missing values`

## Writing PSL rules

## Organizing your data 

## Running inference
- saving output

## Learning rule weights
