---
layout: sv_wiki
title: Silver
menu_title: Silver
menu_weight: 30.0
---

# An extensible attribute grammar system

Silver is an extensible attribute grammar specification language and system that we have developed to investigate highly-modular attribute grammar-based language specifications.
The first paper to read about Silver is ["Silver: an Extensible Attribute Grammar System"](http://www-users.cs.umn.edu/~evw/pubs/vanwyk10scp/).


Silver supports many extensions to D. E. Knuth's original specification of attribute grammars.
These include:

* *Forwarding:* In our paper [Forwarding in Attribute Grammars for Modular Language Design](http://www-users.cs.umn.edu/~evw/pubs/vanwyk02cc/) we show how the notion of forwarding is integrated into attribute grammars.
  This feature is very useful in defining modular language extensions because it allows some aspects of a new constructs semantics to be specified implicitly via a translation to the host language.
  Semantics can be explicitly specified using traditional attribute definitions.

* *Higher-order attributes* as defined by Vogt and a form of *reference attributes* that are similar in spirit to Hedin's.

* *Collections* as specified by Boyland are also supported in Silver.
  These are implemented as a modular language extension to a core version of Silver.

* *Pattern matching* over syntax trees.
  This is similar to what is found in ML and Haskell.
  Syntax trees correspond to algebraic data type values and productions correspond to value constructors.

* *Parametric Polymorphism* similar to what is found in ML and Haskell.
  Familiar syntax for polymorphic lists is also provided.


An [SLE 2011 paper](http://www-users.cs.umn.edu/~evw/pubs/kaminski11sle/index.html) describes some of these features.


Silver also has a modular analysis ([see this SLE 2012 paper](http://www-users.cs.umn.edu/~evw/pubs/kaminski12sle/index.html)) that can be used in extensible languages to ensure that the composition of independently developed language extensions (that all pass this analysis) compose to form a *well-defined* attrbribute grammar.


Silver comes bundled with [Copper](../copper/index.html), our parser and context-aware scanner generator.


[AbleC](/ableC/index.html) is written in Silver.
Silver itself is bootstrapped and written in Silver.
We have also used Silver to implement extensible versions of Java (see this paper) and Promela, the specification language for the Spin model checker (see this paper).

