{- A Universal set of common attributes for use in language descriptions -}
grammar silver:langutil;

import silver:langutil:pp;

{-
This has been "deprecated" for a very long time.  We should either not use 
this library or get rid of the message.

I've gotten rid of the message for now.


deprecated "This library is not deprecated, but users should be aware it is not stable and very subject to change!";
-}

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
 - For accumulating error/warning messages over a syntax tree
 -}
synthesized attribute errors :: [Message] with ++;

