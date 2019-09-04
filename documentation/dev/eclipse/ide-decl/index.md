---
layout: sv_wiki
title: Eclipse IDE decl
menu_weight: 20.0
---

First, I suggest following the Getting Started guide to make sure you're able to build the Silver plugin for eclipse. That'll shake out any issues with the toolchain (e.g. not having the right version of maven, or having an incompatible eclipse version, if there is such a thing.)

## IDE declaration

Create a new grammar in a similar vein to how your current top-level grammar works. e.g. for `silver:composed:Default`, we have `silver:composed:idetest`.

Next, you introduce an IDE declaration block, instead of a `main` function:

```
temp_imp_ide_dcl svParse ".sv" { 
  builder analyze;
  postbuilder generate;
  exporter export;
  folder fold;

  property grammar_to_compile string required display="Grammar";

  wizard new file {
    stub generator getStubForNewFile; --a function whose signature must be "String ::= args::[IdeProperty]"
    property declared_grammar string required display="Grammar";
  }

  name "Silver";
  version "0.2.2";
  resource grammars "../../../../grammars/"; -- I have "../grammars" to be explicit about what's going on here.
  resource jars     "../../../../jars/";
}
```

This is Silver's. Here is a totally minimal one:

```
import ide;

temp_imp_ide_dcl svParse ".sv" { 
}
```

This should work, although here is a suggested minimal one:

```
import ide;

temp_imp_ide_dcl svParse ".sv" { 
  name "Silver";
  version "0.2.2";

  builder analyze;
}
```

Here, `svParse` is the name of the parser to use. And `analyze` is the name of function to call when files are saved in eclipse (and a build should be run.)

Right now, `analyze` should have the following signature:

```
function analyze
IOVal<[IdeMessage]> ::= project::IdeProject  args::[IdeProperty]  i::IO
```

Here, `args` will gives you properties (like `grammar_to_compile` in our original declaration block).

You can use `project` in a number of ways. Look into the `grammars/ide/IdeProject.sv` file Silver comes with.

Notable `IdeProject` functions include `getProjectPath` to find out where on disk to look for files to build.

To make colors work, you need color declarations next to your terminals:

```
temp_imp_ide_font font_keyword color(123, 0, 82) bold;
```

And you need to assign colors to terminal, we advice doing so via lexer classes, e.g. modifying your (probably existing already) KEYWORD lexer class as follows:

```
lexer class KEYWORD font = font_keyword;
```

## Build process

  1. Run `silver --clean the:grammar`. e.g. `silver --clean silver:composed:idetest`. Right now, `--clean` is necessary for an ide build.
  1. This will produce, where silver generates code `generated/ide/the.grammar/` (e.g. `generated/ide/silver.composed.idetest`). You should run `mvn package` under `updatesite/` This will build the plugin, and create a repo on disk eclipse can install from.
  1. Start eclipse, do software install and paste in the FULL PATH to `generated/ide/the.grammar/updatesite/target/repository`. It should find your software.

Should work.
