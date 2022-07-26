# Java Wrapper for KaHyPar

This sub-project of JKaHyPar implements the Java binding of
[KaHyPar](https://kahypar.org/), based on its
[native implementation](../native-kahypar).

## Building JKaHyPar

To build JKaHyPar from its source code, execute the following command
from the directory of this sub-project:

```bash
../gradlew jkahypar
```

This will create a directory `dist/jars` at the root of the project,
that will contain the jars of JKaHyPar and its dependencies.
It will also produce a gzipped-tarball `dist/jkahypar-*.tgz`, containing
all these jars and the [README file of the root project](../README.md).

## Executing the Tests

If you want to execute JKaHyPar's tests, run the following command from the
directory of this sub-project:

```bash
../gradlew test
```
