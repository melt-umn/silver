---
layout: sv_wiki
title: Modules
---

## A grammar

Unlike most languages, a compilation unit in Silver is not tied to a file, but instead to a directory.  A grammar ("module") consists of the declarations and definitions found in all files with a "_`.sv`_" suffix in the directory identifying the grammar.

For example, `a:module:name` corresponds to `a/modules/name/*.sv` in the first matching directory in the grammar search path given to the Silver compiler.

The Silver files in a grammar optionally specify their grammar name as the first declaration of the file.  This practice is recommended, to avoid any potential problems with grammar search paths.

```
grammar a:module:name;
```

### Names

All declarations in a grammar have a name.  This name is sequence of alpha-numeric characters or underscores beginning with a letter.  This name is called the _short name_ of the construct.

The _full name_ of such a construct is formed by prepending the name of the grammar in which it is defined and an additional colon.

> _**Example:**_
```
 nonterminal Expr;
```
> in the grammar `a:module:name` will have short name `Expr` and full name `a:module:name:Expr`.

## Grammar-wide module statements

### Importing other grammars

Other grammars can be imported to every file in a grammar using the `imports` statement:

```
imports another:module:name;
```

All symbols in that grammar are imported, including any symbols in grammars that grammars exported by the imported grammar.

See [the later section on import modifiers](Concept_Modules#Import_modifiers.md).

### Exporting other grammars

Other grammars can be exported by a grammar using the `exports` statement:

```
exports more:modules;
```

No modifiers are possible on an `exports` statement.  There is no equivalent `export` statment.

### Conditional exports

Although not a part of Silver's focus on _extensibility_, Silver supports a notion of conditional exports to facilitate _modularity_. This allows, for example, code specific to supporting a feature to be factored out, to allow for builds that do not include that feature. (This is unnecessary for _extensions_ because they should forward to the host language. But modular specifications of a host language will likely take advantage of this feature, as they often cannot forward to the kernel "host" language.)

```
exports a:grammar:to:export with if:this:grammar;
```

This causes any grammar that imports the grammar this statement appear in to _also_ import `if:this:grammar` but ONLY if that grammar also imports `a:grammar:to:export`.  Either directly, by importing that grammar, or indirectly, by importing something that exports that grammar. (But importing something that merely imports that grammar will not cause this to trigger!)

Although this sounds as if it could cause problems, the feature is typically only used to resolve the "intersection problem," where glue code is necessary to fit two features together, and the above semantics "just work."  (Again, a reminder that this is for modular specifications of the host language. True _extensions_ require no glue code to compose, as forwarding takes care of the issue.)

**For parsers**, only those grammars included in the parser are used to trigger conditional exports. So if the grammar includes a trigger, it won't necessarily trigger for the parser, unless that grammar is also included in the parser's list of grammars.

## File specific module statements

### Importing a grammar

The simple form of an _`import`_ statement is
```
import <grammar-name> ;
```
and has the effect of making all exported names from the named grammar visible in the current file.  Note that both the full and short names are made visible in the importing grammar.  Typically this includes all the nonterminals, terminals, attributes, and productions that are declared in the grammar.

## Import modifiers

Both `import` and `imports` support different ways to control the _short names_ imported. Note that _full names_ are always imported.

### Only including some names

The import can be limited to including only a few short names with a `only` clause. For example:

```
imports silver:langutil only ast;
import core only Maybe, just, nothing;
```

### Excluding some names

The import can exclude some short names with a `hiding` clause:

```
imports silver:langutil hiding pp, unparse;
import core hiding implode;
```

### Renaming some short names

The import can rename some _short names_ using a `with` clause. Note that full names are again left unaffected.

```
imports silver:langutil:pp with implode as ppimplode;
import core with append as listappend, List as BuiltinList;
```

Renaming is also supported in conjuction with `only` and `hiding`:

```
import core only Maybe, orElse with orElse as maybeOrElse;
import core hiding List with Maybe as BuiltinMaybe;
```

### Qualified imports

The _short name_ assigned to each imported symbol can be qualified using the `as` clause:

```
import silver:langutil:pp as pp;
```

This will result in all the the _short names_ imported to be prefixed with `pp:`. For example, the `implode` symbol will now be `pp:implode`. Of course, full names are unaffected, as always.
