# Changelog

This file describes the evolution of the *JKaHyPar* library.

Note that versions are numbered using the `BREAKING.FEATURE.FIX` scheme.

## Version 0.2.0 (July 2022)

+ Allows to create (unweighted) hyperedges using lists in addition to arrays.
+ Bundles [version 1.3.0](https://github.com/kahypar/kahypar/releases/tag/1.3.0)
  of the native library of *KaHyPar* with the *JKaHyPar* module.
+ Makes sure to compile *KaHyPar* in `RELEASE` mode.
+ Provides the native library of *KaHyPar* for Apple Silicon Macs.
+ Uses [version 1.79.0](https://www.boost.org/doc/libs/1_79_0/) of *Boost* for
  all platforms.

## Version 0.1.0 (August 2020)

+ Hypergraphs can be built from source code using `HypergraphBuilder`.
+ Hypergraphs can be read from a file using `HypergraphParser`.
+ Hypergraphs can be partitioned into blocks using the `KahyparPartitioner`
  provided by a `KahyparContext`.
+ Partitioning is delegated to the native library using *Java Native Access*.
