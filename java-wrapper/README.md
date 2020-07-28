# Java Wrapper for KaHyPar

This sub-project of JKaHyPar implements the Java binding of
[KaHyPar](https://kahypar.org/), based on its
[native implementation](../native-kahypar).

## Building JKaHyPar

JKaHyPar may be built from sources in two different manners.
First, if you want a modular JAR, including only JKaHyPar and the native
shared library, run the following command from within this directory:

```bash
../gradlew jar
```

If you prefer to get a fat JAR, which also includes JKaHyPar dependencies
(namely, JNA), run the following command:

```bash
../gradlew dist
```

In both cases, the JAR will be put in the `dist` directory at the root of the
project.

## Executing the Tests

If you want to execute JKaHyPar's tests, run the following command:

```bash
../gradlew test
```
