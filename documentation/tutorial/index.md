---
layout: sv_wiki
title: Silver Tutorial
menu_title: Tutorial
menu_weight: 30.0
---

This tutorial provides a brief introduction to Silver, an extensible attribute grammar system, and
Copper,  a parser and context-aware scanner generator.  These tools have been developed by the
MELT  group  at  the  University  of  Minnesota  for  specifying  extensible  languages  and  generating
compilers and translators from these language specifcations.

This tutorial is intended for readers with no experience in building language processors such as
compilers, source-to-source translators, or interpreters.  It assumes that readers are familiar with
basic notions from programming languages such as types,  control flow,  variable declaration,  etc.
Readers need have no experience with building language processors or with generating language
processors.  This includes students using Silver and Copper for the first time.

This tutorial may be too remedial for readers who have experience using grammarware tools.
Such tools include parser and scanner generators, such as Yacc/Lex, Bison/Flex, ANTLR, ASF+SDF,
SGLR, or Copper, attribute grammar systems, such as The Synthesizer Generator, JastAdd, LRC,
or Silver, and term rewriting system, such as ASF+SDF and Stratego.

This tutorial is also not self contained.  It has numerous pointers to other sources of information
as  it  makes  no  sense  to  duplicate  perfectly  good  descriptions  of  relevant  topics  found  in  other
sources.

The  primary  goal  of  this  document  is  to  help  new  users  begin  using  Silver  and  Copper.   In
Section 2 we describe the components in a traditional compiler pipeline:  scanner, parser, semantic
analyzer, and code generator.  In the following sections we describe how to specify each of these using
Silver and Copper.

### Contents

1. [Language Translation Architecture](1_language_translator_architecture)
2. [Scanning](2_scanning)
3. [Parsing](3_parsing)
4. [Attribute Grammars](4_attribute_grammars)
5. [Running Silver](5_running_silver)
6. [What Next?](6_what_next)

