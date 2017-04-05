---
layout: sv_wiki
title: Editing the runtime in Eclipse
---

## Importing the project

I recommend just importing the project where you have it checked out, rather that attempting to use Eclipse's mercurial plugins or anything like that. This makes it easier to actually test/use the changes because Eclipse will then be editing the files where you have Silver checked out, instead of some separate workspace. (and because `./deep-rebuild` rebuilds the runtime automatically.)

File -> Import -> Existing projects into workspace

Select `runtime/java` wherever you have Silver checked out (e.g. `~/repos/silver/runtime/java`). It should find the project 'common'.

## Fixing configuration issues

You will probably have build errors at this point.

Right click on the project, select 'Properties'. Go to the 'Java Build Path' panel, and click the 'Libraries' tab on the right side of the window.

Edit both 'bin' and 'CopperRuntime.jar'. (It should be obvious from the paths.)'

(At this point, I had Eclipse bug out and not let me select a directory for bin. You may have to delete this entry and click 'Add external class folder'. Editing it alone may not work for some reason.)

**Try to avoid committing the changes to the 'runtime/java/.classpath' file, so we don't just step all over each other with this change.**

## Make sure Silver is built

Run `deep-rebuild` once, to make sure 'bin' gets populated. Then click 'refresh' on the project to have it see the changes.

At this point, everything should be built and working in Eclipse.

## A tour of the runtime

| silver | A main function for invoking the Silver compiler. (To set up the class path to find the runtime jars, etc.) |
|:-------|:------------------------------------------------------------------------------------------------------------|
| common.rawlib | Adaptors for some Java datastructures, so they can be called easier from Silver.                            |
| common.javainterop | Some utilities for working with Silver stuffs from Java code. (currently iterator for lists and comparator from silver functions.) |
| common.exceptions | Contains the Silver-specific exceptions. See the JavaDocs for the class for details.                        |
| common | See below                                                                                                   |

## The common package

The basic mechanics:

  * **Lazy** - Essentially just a function pointer. Given a context to evaluate.
  * **Thunk** - Thunk. No context necessary.
  * There's also **PatternLazy** - just a detail about pattern matching translation
  * Also **CollectionAttribute** - A **Lazy**, that allows contributions to be mutated in.

The basic data structures:

  * **StringCatter** - A special string representation to make appends efficient.
  * **ConsCell** - List cons cells. (They're special-cased because lists are so common that it's important to be space efficient. And also to have efficient appends...) (**NilConsCell** is a nested class inside ConsCell, and not referenceable.)
  * **AppendCell** - Actually implements the special optimization for list appends.

Attribute-grammary data structures:

  * **Terminal** - Representation for terminals.
  * **Node** - represents _undecorated_ nodes. (i.e. given children, but nothing more.)
  * **DecoratedNode** - Given inherited attribute values. Can be examined for attributes, etc.

Related attribute-grammary stuffs:

  * **FunctionNode** - Closes off parts of Node that don't apply for functions.
  * **NodeFactory** - Represents the type of Silver functions.
  * **AttributeSection** - A kind **NodeFactory** for accessing an attribute (the (.pp) syntax)
  * **PartialNodeFactory** - Implements partial application.
  * **TopNode** - A decorated node that errors if it's ever used. Maybe slightly nicer than using 'null' whenever a 'context' is required, but nonexistant.

**Util** is just a random collection of static methods, often used by the 'core' library.

Ignore this crap:

  * **Decorator** - will go away. Currently just junk about how autocopy gets implemented.
  * **IOToken** - not yet used, but will eventually just be the '`IO`' value.
  * **Statistics** - ignore it. Nasty.

## Generated class info

  * Every **nonterminal** is class that inherits from **Node**
  * Every **production** is a class that inherits from the **nonterminal** class
  * Every **production** and **function** has a static 'invoke' method, and a 'factory' field that is the **NodeFactory** value for that function.
  * The Init.java for every grammar has a count of number of syn/inh on every nonterminal and locals on every production.
    * This is used during initialization to give indexes into arrays for all syn/inh/locals. (Typically given names like occurs.grammar.name.Init.attribute\_grammar\_attrONnonterminal\_grammar\_nt)

## General rule

Grep for things to see how they're used.
