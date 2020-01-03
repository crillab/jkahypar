# Changelog

This file describes the evolution of the *JKaHyPar* library.

Note that versions are numbered using the `BREAKING.FEATURE.FIX` scheme.

## Version 0.1.0 (April 2020)

+ Hypergraphs can be built from source code using `HypergraphBuilder`.
+ Hypergraphs can be read from a file using `HypergraphParser`.
+ Hypergraphs can be partitioned into blocks using the `KahyparPartitioner`
  provided by a `KahyparContext`.
+ Partitioning is delegated to the native library using *Java Native Access*.
