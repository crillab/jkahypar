# JKaHyPar

[![Build Status](https://travis-ci.org/crillab/jkahypar.svg?branch=master)](https://travis-ci.org/crillab/jkahypar)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=crillab%3Ajkahypar&metric=alert_status)](https://sonarcloud.io/dashboard?id=crillab%3Ajkahypar)

## Description

This project provides a Java binding for the [KaHyPar](https://kahypar.org/)
hypergraph partitioning framework.

This library consists of a Java module named `fr.univartois.cril.jkahypar`,
which provides an object-oriented interface for building and partitioning
hypergraphs.
Under the hood, [Java Native Access](https://github.com/java-native-access/jna)
is used for invoking the native implementation provided by KaHyPar
(currently, only **Linux** and **macOS** are supported).

## Requirements

To use JKaHyPar, you need to have [Boost](https://www.boost.org) installed
on your computer.
If you want to use the bundled shared library of KaHyPar, make sure to install
the version **1.79.0** of Boost, which has been used to compile this library.

If you use an unsupported OS or a different version of Boost, you may need to
recompile the shared library, and rebuild JKaHyPar.

## Adding JKaHyPar as a Dependency to your Project

You may declare JKaHyPar as a dependency of your project using the following
(for Maven-based projects):

```xml
<dependency>
    <groupId>fr.cril</groupId>
    <artifactId>jkahypar</artifactId>
    <version>0.2.0</version>
    <type>module</type>
</dependency>
```

## Building the Library

This library is developed with [Gradle](https://gradle.org/).
You are not required to install it, since the `gradlew` script can do the
job on its own.

To see how to build the native shared library, go [here](native-kahypar).
Go [there](java-wrapper) to see how to build the Java library.

## Using the Library

To use JKaHyPar, you need to declare its module `fr.univartois.cril.jkahypar`
as a requirement in your `module-info.java`, and put its JAR on the
*modulepath*.

You may also need to put this JAR on the *classpath* when using it within
another application, so as to allow to dynamically load the native library it
contains (JNA does not look into the *modulepath* for such libraries).
A possible alternative consists in specifying manually the location of the
library (e.g., from [this directory](java-wrapper/src/main/resources/)), but
this approach is much less convenient in practice.

Once your path is properly set, you can use JKaHyPar to create hypergraphs
and compute their partitions.
The following section describes how to use these various features.

### Creating a Hypergraph

The `HypergraphBuilder` allows to easily create your hypergraphs, which may
have weights on their hyperedges or vertices, or both.

The following example shows how to create the hypergraph on page 12 in the
[hMetis manual](http://glaros.dtc.umn.edu/gkhome/fetch/sw/hmetis/manual.pdf).

```java
int nbVertices = 7;
int nbHyperedges = 4;

var hypergraph = createHypergraph(nbVertices, nbHyperedges)
        .withHyperedge(joining(1, 2).withWeight(2))
        .withHyperedge(joining(1, 7, 5, 6).withWeight(3))
        .withHyperedge(joining(5, 6, 4).withWeight(8))
        .withHyperedge(joining(2, 3, 4).withWeight(7))
        .withVertexWeight(1, 5)
        .withVertexWeight(2, 1)
        .withVertexWeight(3, 8)
        .withVertexWeight(4, 7)
        .withVertexWeight(5, 3)
        .withVertexWeight(6, 9)
        .withVertexWeight(7, 3)
        .build();

// Do something with hypergraph.
```

Note that the methods `createHypergraph` and `joining` are statically imported
from the classes `HypergraphBuilder` and `UnweightedHyperedge`, respectively.

You may also read a graph from a file written using the hMetis format (page 11
of [its manual](http://glaros.dtc.umn.edu/gkhome/fetch/sw/hmetis/manual.pdf)).

```java
try (var parser = new HypergraphParser(inputStream)) {
    parser.parse();
    var hypergraph = parser.getHypergraph();

    // Do something with hypergraph.
}
```

### Manipulating a Hypergraph

The `Hypergraph` interface defines a wide variety of methods to deal with the
hypergraph you have built (or read).
You may get its String representation (in the hMetis format mentioned above),
its vertices and their weights, or its hyperedges.

You may also get its internal representation.
For efficiency reasons, note that the hypergraph does **not** keep the
reference to the hyperedge objects used to build it.
Actually, the same representation as hMetis and KaHyPar is used.
As such, the hyperedges you can retrieve from the hypergraph are *views*
of its internal representation, and instantiating them has a cost, so think
twice before using them!

### Partitioning the Hypergraph

The main purpose of this library is to compute a partition of a hypergraph.
To do so, you need to write something similar to the following example.

```java
try (var context = new KahyparContext()) {
    // Configure the context using one of KaHyPar's predefined configurations.
    context.configureFrom("/path/to/configuration.ini");

    // Set the imbalance parameter for the partitioning algorithm.
    // This specifies how much the size of the blocks may be different.
    context.setImbalance(0.03);

    // Set the number of blocks you want in your partition.
    context.setNumberOfBlocks(2);

    // Compute the partition of a hypergraph.
    var hypergraph = createAnAwesomeHypergraph();
    var partitioner = context.createPartitionerFor(hypergraph);
    var partition = partitioner.computePartition();

    // Get the identifier of the block in which a vertex is.
    int blockOf1 = partition.blockOf(1);

    // Get all the blocks of the partition.
    var blocks = partition.getBlocks();

    // Get the value of the objective function for the computed partition:
    // the lower the value of the objective, the better the partition.
    int objective = partition.objectiveValue();

    // Improve the previously computed partition, by applying a certain amount
    // of iterations (5 iterations here).
    partition = partitioner.improvePartition(5);
}
```

Observe that the `KahyparContext` is instantiated in a `try-with-resource`.
You should **always** do so, as this ensures that the memory used by the native
library is properly freed.
If you cannot use a `try-with-resource`, e.g., because you want to reuse the
context, you must make sure that its `close()` method is called when you no
longer need it.
