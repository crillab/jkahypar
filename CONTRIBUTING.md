# Contributing Guidelines

Any contribution to this library is welcome!
There are multiple ways to contribute, as described below.

## Developing JKaHyPar

The JKaHyPar library is developed with the latest versions of
[Gradle](https://gradle.org/) and [Eclipse](https://www.eclipse.org/).
Even though you are not required to install them, as you can rely on the
`gradlew` script to build the library and use a different IDE, we highly
recommend to use these tools, as this project is optimized for being developed
with them.

If you want to contribute, you first need to *fork* this project, and to clone
your fork on your computer, by using a command similar to the following.

```bash
git clone https://gitlab.univ-artois.fr/wallon-phd/softwares/jkahypar.git
```

Once this is done, open a terminal in the `jkahypar` directory and tell Gradle
to generate Eclipse configuration files by executing this command:

```bash
gradle eclipse
```

Once this is done, you just need to open Eclipse and import the JKaHyPar
project.
Do not forget to check the *Search for nested project* box.

## New Features

When using this library, you may miss some features.
You can submit a *feature request* or a *merge request* for them.

Note that new features have to be useful for most of the users, and should not
introduce (too many) breaking changes.

### Submitting a Feature Request

You can submit a feature request by writing an issue tagged with the label
**Feature Request**.
In this issue, try to describe *precisely* what you want, by using examples if
possible.

We will evaluate the feature you ask for, to see whether it is feasible and
useful for the users.
If so, we will consider its implementation.
Otherwise, we will try to find a solution for your particular use case.

### Implementing Features

You may also want to implement the features you miss.
If you want to share your implementation, you will need to *fork* this project
and to submit a *merge request*.

When writing the merge request, try to describe *precisely* what you have
implemented.
If the feature was initially submitted as a feature request, specify also the
corresponding issue using `#X`, where `X` is the number of the issue.

We will only accept merge requests satisfying the following conditions:

+ The feature you implemented is properly documented to help people understand
  how to use it.
+ You have written some tests for your feature, either by completing the
  existing ones, or by providing your owns.
+ No breaking changes are introduced, unless your feature requires them.
+ Your implementation is easily readable, and does not trigger any linter
  warning.

If one of these conditions is not satisfied, you will be asked to apply the
changes required to satisfy it.

Note that we may reject features that we do not consider *useful*.
We are open-minded, and rejections are not definitive: we might finally accept
the feature after discussions, or if many users support it.
However, we advise you to have at most one implemented feature per request
(unless they are dependent on each other), so that we can still accept some of
the features while rejecting others.

## Bug Fixing

While using this library, you may find bugs that you may want to either
*report* or *fix*.

### Reporting a Bug

To report a bug, just submit an issue tagged with the label **Bug**.

Try to describe as precisely as possible the problem you have encountered.
In particular, we need the following information:

+ What is the expected behavior?
+ What is the actual behavior?

If possible, also attach a minimal example so that we can reproduce the
bug you have identified.

### Fixing a Bug

You may also fix the bug yourself, and submit a *merge request* with your fix.

Before writing the fix, ask yourself whether the bug you identified is actually
a bug.
If you are not sure, prefer submitting a bug report first.
Otherwise, we will reject the merge request.

When submitting the request, start by describing the bug you have fixed.
If this bug has already been reported, specify the corresponding issue using
`#X`, where `X` is the number of the issue.
Otherwise, describe in your message the bug you have fixed, as for a bug
report.

The conditions for your merge request to be accepted are the following:

+ Again, the bug you fixed is indeed a bug.
+ Unless unavoidable, no breaking changes are introduced.
+ You have written some non-regression tests for your fix.
+ Your fix is easily readable, and does not trigger any linter warning.

If one of these conditions is not satisfied, you will be asked to apply the
changes required to satisfy it.
