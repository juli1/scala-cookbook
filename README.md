# Scala Cookbook and other advices for beginners

This repository has some recipes and other code to reuse when starting with Scala.
It is *NOT* an authoritative resource about how to write good code in Scala: it is
rather a good resource with over-documented code that can help you started with
this new language.


# Scala learning curve

I documented my Scala learning curve on my blog there:

In short:

1. [Scala for the Impatient](http://horstmann.com/scala/index.html) is a very good resource to start but you **really** need more.
For example, it skips most of the important concepts you need to learn about Functional Programming.
Take it to have a taste and quick overview of the syntax and how the language works, nothing more.
2. The best list of articles are the one from [Daniel Westheide](https://danielwestheide.com/) and his suite of blog post [The Neophyte's Guide to Scala](https://danielwestheide.com/scala/neophytes.html)
3. Once you are really comfortable with the majority of Scala, read [Programming in Scala by Odersky](https://www.amazon.com/Programming-Scala-Updated-2-12/dp/0981531687/ref=sr_1_1?ie=UTF8&qid=1528782250&sr=8-1&keywords=odersky+scala)
4. If you use any Twitter code, do read about [Twitter util, a must read](https://twitter.github.io/util/)

Also
1. Do not take the Coursera classes on Scala or Functional Programming. I love Coursera but the Scala classes are garbage (that it a pitty!).
2. Always read the API docs on [scala-lang.org/api](https://www.scala-lang.org/api/)


**WARNING**: if you are using [Finatra](https://github.com/twitter/finatra), 
[Finagle](https://github.com/twitter/finagle) and other [Twitter open-source](https://github.com/twitter) 
projects (also available on [github](https://github.com/twitter/util)).
Twitter has its own way of doing things but as a very early Scala adopter, does not use some of the very basic scala libraries.
One good example is the Future, the implementation of the [Twitter Future](https://twitter.github.io/util/docs/com/twitter/util/Future.html) differs from the implementation
of the [Scala Future](https://www.scala-lang.org/api/current/scala/concurrent/Future.html) (and [you can convert one into the other](https://twitter.github.io/util/guide/util-cookbook/futures.html#conversions-between-twitter-s-future-and-scala-s-future))!

# Topics

## List usage

The [ListUsage.scala](src/main/scala/ListUsage.scala) file contains different usage patterns of the `List`
Scala class. It shows how `for` loops are translated into filter/map calls.


## Types Classes

The [TypeClasses.scala](src/main/scala/TypesClasses.scala) file contains usage for Types Classes.
You can find more information on 
[Daniel Westheide blog post](https://danielwestheide.com/blog/2013/02/06/the-neophytes-guide-to-scala-part-12-type-classes.html).

This code will definitively help you understand the underlying
concept of types classes and how to use it.


## Concurrency: Future and Promise

[Concurrency.scala](src/main/scala/Concurrency.scala) shows how
to use a [Future](https://www.scala-lang.org/api/current/scala/concurrent/Future.html)
and how it execute code asynchronously. It also shows
the principle of a [Promise](https://www.scala-lang.org/api/current/scala/concurrent/Promise.html)


## Optional

Scala does not have the concept of `null`. Instead, when you do not have a value, you 
return the singleton value `None`. At first, it does not seem very useful but this is a very powerful
programming concept.

[OptionUsage.scala](src/main/scala/OptionUsage.scala) shows how to use the 
[Option](https://www.scala-lang.org/api/current/scala/Option.html) class.

## Exception Handling

[ExceptionManagement.scala](src/main/scala/ExceptionManagement.scala) contains a good example
to handle exception in your code. It defines a function that can return an exception and
how to filter/print the exception.

## Implicit value

[ImplicitValUsage.scala](src/main/scala/ImplicitValUsage.scala) is an example
to declare implicit val and how to declare functions that uses implicit value.
The main implicit you are going to meet in many codebase is the `ExecutionContext`
that is defined in `scala.concurrent.ExecutionContext.Implicits.global`.

## Implicit Conversion

[ImplicitConversion.scala](src/main/scala/ImplicitConversion.scala) is an example
to declare implicit function that is used to implicitly convert from one type to another.
In that example, we convert from an `Int` to a `Metric`.

