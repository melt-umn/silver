---
layout: sv_wiki
title: Silver
menu_title: Documentation
menu_weight: 50.0
---

The Silver documentation is currently being migrated from its original
wiki on is previous Google Code site, but most of it is in place and
this is the best source for learning how to use Silver.

Below are some pointers to help you find your way through the
documentation. 

* The [installation guide](install-guide) and [command line
  reference](command-line-reference) will get you started
  with Silver.

* The [tutorial (TODO)](tutorial) provides a gentle
  introduction to attribute grammars generally and Silver
  specifically.  If you are familiar with the attribute grammar
  formalism you may want to just skim this.

* The [reference guide](silver/doc/ref) describes the different
  constructs in the Silver specification language:

  * expressions

  * statements (though we should not call these statements.  We need
    another term)

  * declarations

* The [concepts](silver/doc/concepts) section describes some feature
  and concepts in Silver that are not found in many other AG systems
  or are a bit esoteric.

* The standard libraries in Silver include the `core` library that is
  automatically imported into every grammar and the `silver:util` and
  `silver:langutil` that need explicit imports.

  Documentation for these is automatically generated and can be found
  in the [gen](silver/doc/gen) section.

* A general [FAQ](silver/doc/fag) and [FAQ about
  errors](silver/doc/error-fac) as well as a [style
  guide](silver/doc/style-guide) may also be of interest.  

* The academic papers about Silver and Copper, along with their BibTeX
  entries can be found on the [Bibliography](silver/doc/bibliograph)
  page.

* Finally there is some information about [developing](silver/doc/dev)
  Silver that is most likely of interest to those writing extensions
  to Silver of working on the core language.
