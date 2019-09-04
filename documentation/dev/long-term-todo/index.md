---
layout: sv_wiki
title: Long term TODO list
---

This list is primarily for entertainment.  However, it might turn out to be useful should the unthinkable happen, and Silver needs to get into proper shape, fast, before it has too many users. ;)

A principle to start:
  * This list isn't a _general_ TODO list. It's specifically for things that would have serious effects on code written in Silver. i.e. things that should be fixed before there ever hits a critical mass of code that would make otherwise good changes impractical.

# Do

  * Replace the entire IO portion of the language. Fortunately this is as small as it is bad.
  * Full featured FFI. We should be able to 'import java ...' (Perhaps also structured in such a way that you can write 'import scala ...' as a pure extension.)
  * Package management built-in. Must include versioning support. (Things to look into: maven & OSGi, since integration with the ecosystem might be nice. What does Scala do? Anyone implementing this should look at Go, and consider any pitfalls from Hackage, too. Other comparisons? Perl CPAN? Ruby Gems?)
  * Fully solve Unicode and text handling.
  * Fully solve code documentation.
  * Uniform error/exception handling. Right now, error handling is awful. Trying to read from a non existent file means unrecoverable program termination.
  * Prelude redesign. Pretty much it's just accumulating at the moment. It should be actually **designed**. I also think IO shouldn't be in the prelude. Also need a better picture of what's actually in the standard library.
    * Fix up scoping a bit. The compiler could be a lot more efficient with a small tweak to module import semantics, I _think_... Certainly at the moment we're importing the prelude on a per-file basis which is kinda bogus!
    * Need efficient libraries for basic things. Arrays, sets, different kinds of maps. Need to de-emphasize lists. Again, the key here is design. The interface to standard libraries will affect user developed ones. In addition, we need to figure out how to deal with "variant proliferation." e.g. Problems likes haskell's "strict vs lazy, boxed vs unboxed, etc." combinatorial explosion.
  * Language features that would induce a dramatic redesign of standard library components:
    * "Pseudo attributes" that allow functions to be moved within the dot namespace of a type.  e.g. lists should not be munched with `head(l)` but with `l.head`, as this completely removes `head` from the global namespace. (bonus: this will aid completion, later, too.) (aside: well, okay, _really_ you should use pattern matching for lists, but you get the example.)
    * OO-style abstraction mechanism. Typeclasses, probably. Not certain just how fully-featured, yet. But certainly this will be used extensively in libraries. There are dragons here, though, the interaction with attributes needs careful thought. (The trouble is attributes sorta ARE one function typeclasses, without the ability to abstract i.e. there is no type for "anything with .attr". So a typeclass declaration should allow not just functions supplied in instances, but perhaps also let it ensure an attribute occurs on the type, too.)
    * There is also the matter of Location and possibly annotation, but this might actually be accomplished in the near term!
  * There is a substantial matter of dealing with state, but I suspect this is so difficult to do _really well_, and the pay off is too far on the practice side of "practice vs research," that it simply doesn't pay to do it until after this hypothetical critical mass, suffering whatever consequences of that it must. (State would play into doing well the things remote attributes, circular attributes, and threaded attributes are often tasked to do in other systems. The bonus of state is largely efficiency, and not much more than that.)

# Don't

  * There are very few features of the **language** itself that imply any kind of poor performance. The only possible one is the choice to be lazy by default. This should be reconsidered only carefully. Very, very carefully. Strictness could impact extensibility in unknown ways. (On the other hand, extensibility that relies on incidental strictness is bad.)

# Module system notes

We should probably:
  * Maintain "silver grammar = java package."
  * A jar file should consist of a "Silver element." Elements should declare version and versioned dependencies on other elements. And version of Silver.
  * Should probably be compatible with Java jar file schemes of some sort... OSGi/Maven2/Ivy? This needs investigation.
  * **Must** tolerate multiple versions of a library being "installed." At first, however, we could require that any particular build use just one version. Long term, however, we might even want to think about multiple versions functioning in parallel? (So long as their types are considered distinct.)
  * Metadata should probably be external to the package, though. It'd be **really** nice if we could have a CI server determine what versions are compatible, etc. (Also, we might have a unique ability to statically check semantic versioning, to some extent...)

# Milestone 1.0 thoughts

  * Much of the "do" list above should probably be taken care of. This could be summarized as making the Silver language more general purpose.
    * IO, Typeclasses, library redesign, namespace management, FFI, SilverDoc
  * Need packaging.
  * Need runtime composition. There for silver grammars, missing Copper!
  * Q: How far should the IDE component progress? At least mildly. Certainly many languages have a 1.0 without an IDE!
  * Q: Debugging? Perhaps not a show stopper for a 1.0?
