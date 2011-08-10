{- A Universal set of common attributes for use in language descriptions -}
grammar lib:lang;

deprecated "This library is not deprecated, but users should be aware it is not stable and very subject to change!";

{--
 - The pretty print of a syntax tree.
 - FUTURE WARNING: In the future this may change from String to a PP library!
 -}
synthesized attribute pp :: String;

{--
 - For computing the abstract syntax tree from a concrete syntax tree
 -}
synthesized attribute ast<a> :: a;

{--
 - The location information associated with a syntax tree node
 -}
synthesized attribute location :: Location;

{--
 - For accumulating error/warning messages over a syntax tree
 -}
synthesized attribute errors :: [Message] with ++;

