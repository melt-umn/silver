{- A Universal set of common attributes for use in language descriptions -}
grammar silver:langutil;

import silver:langutil:pp;

deprecated "This library is not deprecated, but users should be aware it is not stable and very subject to change!";

{--
 - The unparse of a syntax tree.
 -}
synthesized attribute unparse :: String;

{--
 - The pretty print of a syntax tree.
 -}
synthesized attribute pp :: Document;

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

