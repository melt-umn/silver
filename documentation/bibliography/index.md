---
layout: sv_wiki
title: Bibliography
menu_title: Bibliography
menu_weight: 1000.0
---

This page is currently a draft! The bibtex may need massaging, and pdf links need to be filled in!



# The Silver papers

Here are some important publications directly related to Silver:

## Silver

This is the currently preferred citation when referencing Silver in general.

```
@ARTICLE{vanwyk2010scp,
  author = {Van Wyk, Eric and Bodin, Derek and Gao, Jimin and Krishnan, Lijesh},
  title = {Silver: an Extensible Attribute Grammar System},
  journal = {Science of Computer Programming},
  year = {2010},
  volume = {75},
  pages = {39--54},
  number = {1--2},
  month = {January},
  publisher = {Elsevier Science}
}
```

## Forwarding

This paper introduces the concept of forwarding, which is essential to our notion of language extension.

```
@INPROCEEDINGS{vanwyk2002cc,
  author = {Van Wyk, E. and de Moor, O. and Backhouse, K. and Kwiatkowski, P.},
  title = {Forwarding in Attribute Grammars for Modular Language Design},
  booktitle = {Proc. 11th Intl. Conf. on Compiler Construction},
  year = {2002},
  volume = {2304},
  series = lncs,
  pages = {128-142}
}
```

## Modular well-definedness

This paper introduces the modular well-definedness analysis for higher-order attribute grammars with forwarding. It is the essential component for allowing reliable language extension in Silver.

```
@INPROCEEDINGS{kaminski2012sle,
  author = {Kaminski, Ted and Van Wyk, Eric},
  title = {Modular well-definedness analysis for attribute grammars},
  booktitle = {Proceeding of the International Conference on Software Language Engineering},
  year = {2012},
  pages = {352--371},
  publisher = sv,
  series = lncs,
  volume = {7745}
}
```

## Types and pattern matching

This paper describes the type system, as well as pattern matching on attribute grammars, and its integration with forwarding to preserve extensibility.

```
@INPROCEEDINGS{kaminski2011sle,
  author = {Kaminski, Ted and Van Wyk, Eric},
  title = {Integrating attribute grammar and functional programming language
	features},
  booktitle = {Proceedings of 4th the International Conference on Software Language
	Engineering ({SLE} 2011)},
  year = {2011},
  volume = {6940},
  series = lncs,
  pages = {263--282},
  month = {July},
  publisher = sv
}
```

# The Copper papers

Here are some important papers related to Copper:

## Copper, context-aware scanning

This is currently the preferred citation when referencing Copper generally.  It introduces the notion of a _context-aware scanner_ that is essential to allowing language extension to function smoothly with an otherwise standard LALR parser.

```
@INPROCEEDINGS{vanwyk2007gpce,
  author = {Van Wyk, Eric R. and Schwerdfeger, August C.},
  title = {Context-aware scanning for parsing extensible languages},
  booktitle = {Proceedings of the 6th international conference on Generative programming
	and component engineering},
  year = {2007},
  series = {GPCE '07},
  pages = {63--72},
  address = {New York, NY, USA},
  publisher = {ACM},
  acmid = {1289983},
  doi = {10.1145/1289971.1289983},
  isbn = {978-1-59593-855-8},
  keywords = {context-aware scanning, extensible languages},
  location = {Salzburg, Austria},
  numpages = {10},
  url = {http://doi.acm.org/10.1145/1289971.1289983}
}
```

## Modular determinism analysis

This paper introduces the modular determinism analysis, which is the essential component that allows for safe extension of syntax. It is the syntax counterpart to the modular well-definedness analysis for attribute grammars.

Note that the analysis also gets refined slightly in the next paper, on parse table composition.

```
@INPROCEEDINGS{schwerdfeger2009pldi,
  author = {Schwerdfeger, August C. and Van Wyk, Eric R.},
  title = {Verifiable composition of deterministic grammars},
  booktitle = {Proceedings of the 2009 ACM SIGPLAN conference on Programming language
	design and implementation},
  year = {2009},
  series = {PLDI '09},
  pages = {199--210},
  address = {New York, NY, USA},
  publisher = {ACM},
  acmid = {1542499},
  doi = {10.1145/1542476.1542499},
  isbn = {978-1-60558-392-1},
  keywords = {context-aware scanning, extensible languages, grammar composition,
	language composition, lr parsing},
  location = {Dublin, Ireland},
  numpages = {12},
  url = {http://doi.acm.org/10.1145/1542476.1542499}
}
```

## Run-time parse table composition

This paper introduces direct parse table composition. It also relaxes on of the modular analysis' rules, as parse tables fragments can be LR(1) "at the seams" while remaining LALR internally.

Unfortunately, at this time (2013) this work needs to be re-implemented, and this feature is not yet present in Silver.

```
@INPROCEEDINGS{schwerdfeger2010sle,
  author = {Schwerdfeger, August and Van Wyk, Eric},
  title = {Verifiable Parse Table Composition for Deterministic Parsing},
  booktitle = {2nd International Conference on Software Language Engineering},
  year = {2010},
  volume = {5969},
  series = lncs,
  pages = {184--203},
  publisher = sv
}
```

# Related papers

These are a few background papers related to attribute grammars or Silver that we think are relevant. These papers are work by others, not related to Silver.

## Attribute grammars

We must, of course, include the beginning.

```
@ARTICLE{knuth1968mst,
  author = {Knuth, D. E.},
  title = {Semantics of Context-free Languages},
  journal = {Mathematical Systems Theory},
  year = {1968},
  volume = {2},
  pages = {127--145},
  number = {2},
  note = {Corrections in \textbf{5}(1971) pp.~95--96}
}
```

## Higher-order attributes

Introduces to producing and anchoring new trees in attribute grammars.

```
@INPROCEEDINGS{vogt1990,
  author = {Vogt, H. and Swierstra, S. D. and Kuiper, M. F.},
  title = {Higher-order Attribute Grammars},
  booktitle = {ACM Conf. on Prog. Lang. Design and Implementation (PLDI)},
  year = {1990},
  pages = {131--145}
}
```

## Reference attributes

Introduces references directly to other parts of the tree. To, for example, superimpose a graph structure on an abstract syntax tree.

```
@ARTICLE{hedin2000informatica,
  author = {Hedin, G.},
  title = {Reference Attribute Grammars},
  journal = {Informatica},
  year = {2000},
  volume = {24},
  pages = {301--317},
  number = {3}
}
```

# Applications

TODO: Some of the papers using Silver...

# Other interesting work

TODO: Some of the other systems like Silver. Kiama, CoCoCo, JastAdd, etc.
