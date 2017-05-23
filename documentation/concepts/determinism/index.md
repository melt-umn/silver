---
layout: sv_wiki
title: Modular determinism 
---

## Running the analysis

With a file such as:

```
grammar determinism

import host:grammar;

copper_mda testGrammar(hostParser) {
   extension:grammmar;
}

copper_mda testGrammarTwo(hostParser) {
   extension:grammmar_two;
}

... etc 
```

Run ```silver --clean determinism```

Unlike in well-definedness, determinism tends to report errors quickly. Determinism will usually stop evaluating errors after the first error it reports, as a lot of errors are reported as bugs or crashes in copper (ideally this will change). Determinism will also not evaluate any grammars following one that fails in a list of ```copper_mda``` definitions, and will evaluate those definitions in order from top to bottom.

---

### Types of Errors Reported

1. <... a long stack trace> ... Requested head of nil.

   This is a bug in copper, where it doesn't check the length of a list before obtaining the first element of the list.

   ##### Potential solutions:

   Consider changing 'imports' to 'exports' on terminals from other grammars.

2. Missing Bridge Productions 

   Example:
   ```
   ... Invalid content was found starting with element 'ProductionRef'. No child element is expected at this point.
   ... [grammar ext]: Extension grammar is missing the following required attributes: [bridgeProductions]
   ```  

   This indicates that the automatic scan copper performs to find bridge productions, which are productions that begin with marking terminals, was unsuccessful. Either:  

   1. The grammar has no productions. Or  

   2. The grammar has productions, but none of them have a marking terminal at the front. Or  

   3. The grammar has productions, and some of them have a marking terminal at the front, but among those productions more than one marking terminal is used.  

   ##### Potential solutions:  

   1. Don't run a determinism test on this grammar. This should eventually change to be some sort of warning, indicating that the grammar passes but that the test is inneffective. 

   2. If this is intentional, move these productions to a grammar that also has a production with a marking terminal at the front. If this is unintentional (there are productions with a marking terminal, but they're binary operators, for example) then the grammar needs to be changed to move the marking terminal to the front of a production.

   3. Split the grammar into multiple grammars where each only has one used marking terminal. 

3. Not in IL-subset. 

   This is caused by creating a production of the same form as a host production that is more permissive, so where the host language uses some nonterminal A you use a nonterminal B that can be composed from A.

   ##### Potential solutions:

   After isolating the problematic production, change the nonterminals used to be less permissive. Consider   referencing the host language and looking for the production that your production is a subset of. 

4. Follow / Lookahead spillage

   Example:
   ```
   [copper] Nonterminal HostNonterminal has follow spillage of
   [copper]    [BadTerminal_t]
   [copper] DFA state 127, item 0 has lookahead spillage of
   [copper]    [BadTerminal_t]
   ```

   A non-marking terminal that has been added by your grammar follows a non terminal of the host language. Lookahead spillage tends to come as a result of follow spillage.

   ##### Potential solutions:

   Re-work the grammar so that this is no longer the case. 

5. [javac] ... error: cannot find symbol

   This is the result of outdated java definitions holding on to a production or terminal that has been removed. Silver --clean does not automatically do this removal right now. 

   ##### Potential solutions:

   Delete the subdirectory your grammars are represented by in silver/generated. 

6. T, designated as a marking terminal, must be referenced only as the first right-hand-side element in a bridge production.

   ##### Potential solutions:

   Re-work the grammar to remove any production that includes a marking terminal not as the first element of a production. One approach could be to try to add a second identical terminal that is not marking, and disambiguate which should appear in which contexts. 
