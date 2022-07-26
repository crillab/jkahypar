# KaHyPar Native Library

This sub-project of JKaHyPar declares as a [submodule](kahypar) the native
implementation of [KaHyPar](https://github.com/kahypar/kahypar) to make
easier the packaging of JKaHyPar for different platforms.

To build the library, you need to get all KaHyPar's dependencies on your
computer.
First, you need to install [Boost](https://www.boost.org) at least 1.69.
Then, execute the following command from within this directory:

```bash
git submodule update --recursive --remote --init
```

Finally, simply run the following command:

```bash
../gradlew dist
```

This will create the `libkahypar` shared library for your OS and architecture
in the `dist` directory at the root of the project.
Currently, only **Linux** and **macOS** are supported.
If you use a different OS, you can edit [this configuration file](build.gradle)
and, in the section *System Dependent Configuration*, add the condition
allowing Gradle to identify your OS and to create the appropriate shared
library.

To add the newly created library to JKaHyPar, run the following command:

```bash
../gradlew buildForJkahypar
```

Now, to package JKaHyPar with this new library, go back to the root directory,
and build JKaHyPar using the following command:

```bash
./gradlew jkahypar
```
