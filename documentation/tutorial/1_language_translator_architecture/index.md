---
layout: sv_wiki
title: The architecture and components of a language translator
menu_title: Language Translator Architecture
menu_weight: 10.0
---

A compiler or source-to-source translator are tools that read in a text file containing a program and
write out another text file containing the translation of that program.  For compilers, that generated
program  is  in  a  low-level  language  such  as  assembly  language,  byte-codes  for  a  virtual  machine
such as the Java virtual machine (JVM), or sometimes even C. For source-to-source translators the
generated program is a higher-level language, which may or may not be the same language as the
input program.  Thus, the name "compiler" is a bit of a misnomer; it is simply a translator that
generates low-level code.  Compilers and source-to-source translators are essentially the same kinds
of tools built using the same techniques.

Throughout this document we will use the term "translator" to indicate any tool that translates
a program from one language, the source language, to another, the target language.  In some cases
the  source  and  target  languages  are  the  same;  for  example  a  tool  that  reformats  a  program  to
comply with strict formatting guidelines.

It is common practice to construct language translators as a series or pipeline of components
that perform the major tasks in program translation.


# Scanning

The scanning process reads in program text and recognizes its lexical syntax.  This
process involve recognizing lexical constructs such as keywords (*e.g.* `while` or `if`), identifer names
(*e.g.* `x`, `area`, etc.), literal values (*e.g.* `1`, `3.14`, `"Hello"`, etc.)  and generating a sequence of
*tokens*,one for each such recognized construct.  This sequence of tokens is passed on to the parsing phase
that follows.  The scanner is also responsible for recognizing and removing comments from the input
stream; for these no token is generated.

For example, from the input stream `while ( x < 100 ) x = x * x ;` the following list of
tokens may be generated:

[ *While<sub>t</sub>*("while"),
*LParen<sub>t</sub>*("("),
*Id<sub>t</sub>*("x"),
*LT<sub>t</sub>*("<"),
*IntLit<sub>t</sub>*("100"),
*RParen<sub>t</sub>*(")"),
*Id<sub>t</sub>*("x"),
*EQ<sub>t</sub>*("="),
*Id<sub>t</sub>*("x"),
*Star<sub>t</sub>*("\*"),
*Id<sub>t</sub>*("x"),
*Semi<sub>t</sub>*(";")]


# Parsing

The parsing process recognizes the syntactic structure in the sequence of tokens produced
by the scanner.  This structure is represented as a tree.  For example, the parser would
create a tree with a root node labeled to indicate that it was a while-loop with two children; the
first being the tree representing the while-loop condition and the second being the tree representing
the while-loop body.

This tree is called the *concrete syntax tree* since it is based on the specification of the actual
concrete syntax, as opposed to a simplified abstract syntax that is sometimes used in the following
semantic analysis phase.


## Semantic analysis

This phases examines a tree representation of the program to perform analysis
such as type checking and error reporting.


## Code generation

This is the final phase in which some translation of the tree representation
is generated.  This may be byte-code, machine code, or a translation to a language such as C. In
most compilers this phase is preceded by an optimization phase.

Next Section: [Scanning](../2_scanning)

