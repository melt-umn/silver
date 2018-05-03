---
layout: sv_wiki
title: Foreign functions
menu_title: FFI
---

## Adding dependencies to your Silver jar

There is a flag you can pass on the command line, when building a jar using Silver, to depend on another external jar file:

```
silver --include-jar ./path/to/extra/file.jar  some:grammar
```

This will include that jar in the class path and have the usual effect based on the current build mode:

  * default: the generated jar will have a full path to that jar in its classpath
  * `--relative-jar`: the generated jar will expect to be run with that jar placed in the same directory as itself (including the SilverRuntime and CopperRuntime jars, too.)
  * `--one-jar`: the generated jar will have the contents of the other jar included within itself.

i.e. the same effect these flags have on the runtime jars, will also happen to this jar passed via `--include-jar`.

  * **TODO** This is a terrible way to depend on other jars, but fixing it will have to wait until we have a proper Silver packaging system. Somehow, the code that needs a jar should itself require the jar, rather than the builder having to pass this along.

## Creating foreign types within Silver

Types can be declared as Java types within Silver by writing:

```
type MyType foreign;
```

  * **TODO** Currently, there is no way to specify the Java type, and as a result it is always passed around as Object and you have to do a lot of casting yourself. This should change, eventually.

## Creating foreign functions within Silver

A function can have its implementation be just Java source code using a declaration like the following:

```
function append
[a] ::= l1::[a] l2::[a]
{
  return error("Not Yet Implemented: append");
} foreign {
  "java" : return "common.AppendCell.append(%l1%, %?l2?%)";
}
```

  * Every Java type should be referred to using its full package path. There is no way to add addition "import" statements or the like.
  * Parameters referred to using `%name%` are _demanded_. That is, any thunk is evaluated before the result is used. As a return, the corresponding Java type is known (unless, of course, the parameter's type is a foreign type, which currently are all `Object`.)
  * Parameters referred to using `%?name?%` are _not_ demanded. The receiver may get the value directly, **or** it may get a thunk that will eval to the desired value. This allows foreign java functions to be lazy in an argument, if desired. (Above, `append` is strict in its first argument, but lazy in its second.) Because of this, these values are all of type `Object`.

  * **TODO** There is really no reason we should have a "normal" function body. We should remove that.

## Calling Silver from Java

This is semi-intentionally not documented here. The Silver runtime is subject to change, always.

However, there are good examples of doing so. Primarily, our silver-eclipse runtime calls from Java code into Silver, and there are examples in the silver runtime of doing so as well. Reading over the entirety of the `common` and `common.javainterop` packages in the SilverRuntime will be helpful. These should have some JavaDoc as well (if there is insufficient api documentation somewhere in there, please file a bug!)

General notes, however:

  * There is are `Init` calls that need to be made before Silver stuff can be used.
  * You need to take `Node` objects and convert to `DecoratedNode` in order to access attributes.

## Building Java code that depends on Silver code

You have a chicken, and an egg. This will be fun.

As an illustration, here is how we build the Silver runtime jar:

  1. Build the `core` grammar to java code, but pause before trying to build that.
  1. Add the generated `.java` files from `core` to the build path for the silver runtime, build all these java files at once.
  1. Take only those built `.class` files that are part of the runtime, not part of `core`, and put those into the `SilverRuntime.jar`

So, the general technique is to simultaneously build the silver-generated java files and your own java code, but then extract from that only your code's class files to distribute.

## Long term FFI TODO

We should some day be able to import java types directly, rather than have to write a lot of annoying foreign functions.

e.g. `foreign import java.lang.String;`

The idea is that this would:

  * Automatically create the appropriate foreign types in the Silver environment. Not just for `java.lang.String` but also for all types its methods reference.
  * Create all the foreign functions for static functions / methods / constructors of that type.
  * Mangle the types to adapt programming models. e.g. everything would be `Maybe<a>` where `a` is the corresponding foreign type, in order to accommodate nulls. All functions would be mangled into IO actions, because who knows what manipulates state.
  * Translate some common types automatically (strings, integers, booleans)
  * Recognize any references to Silver runtime types, and translate those back appropriately. (e.g. ConsCell, NIOVal)
  * Recognize Java annotations about null or function purity, and use those to avoid doing the above where possible.

Silver is missing a lot of features we'd want before we tried this, though.

This would eliminate the need to write strings of Java code that we do string substitution into. We'd also move type checking into the Silver compiler.

The model for using foreign functions wouldn't change too much though. You'd never directly use a foreign import in a normal program, instead a Silver library would do it, and then declare the desired (probably less-messy) API to expose. Basically, like you do now.

Haskell almost does this sort of thing. Instead of importing (and presumably using the Java reflection stuff to inspect the class), you run a preprocessor that generates appropriate Haskell declarations from a C header file. Then you design the library's API internally using calls to those functions.
