# KaHyPar Native Library

This sub-project of JKaHyPar declares as a [submodule](kahypar) the native
implementation of [KaHyPar](https://github.com/SebastianSchlag/kahypar) to
make easier the packaging of JKaHyPar for different platforms.

To build the library, you need to make sure that you get it with all its
dependencies, by executing the following command:

```bash
git submodule update --recursive --remote --init
```

Then, simply run the following command:

```bash
../gradlew dist
```

This will create the `libkahypar` shared library for your OS in the `dist`
directory at the root of the project.
Currently, only **Linux** and **macOS** are supported.
If you use a different OS, you can edit [this configuration file](build.gradle)
and, in the section *System Dependent Configuration*, add the condition
allowing Gradle to identify your OS and create the appropriate shared library.

To add the newly created library to JKaHyPar, run the following command:

```bash
../gradlew buildForJkahypar
```

Now, to package JKaHyPar with this new library, go back to the root directory,
and create the JARs using the following command:

```bash
./gradlew dist
```
