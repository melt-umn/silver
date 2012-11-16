-- This file defines the error demanding function that can be interfaced by IDE plugin written in Java.

--grammar silver:analysis:binding:driver;
grammar silver:modification:impide;
import silver:driver;
import silver:util:cmdargs;

import silver:definition:core;
import silver:definition:env;

-- return a string list for now. May be improved in future.
synthesized attribute errorList :: [String] occurs on RunUnit;

aspect production run
top::RunUnit ::= iIn::IO args::[String]
{  
  top.errorList = getAllBindingErrors(grammars); 
}

function getAllBindingErrors
[String] ::= specs::[Decorated RootSpec]
{
  return if null(specs)
         then []
         --else [foldMessages(head(specs).errors)] ++ getAllBindingErrors(tail(specs));
         else rewriteMessages(head(specs).declaredName, head(specs).errors) ++ getAllBindingErrors(tail(specs));
}

function rewriteMessages
[String] ::= declaredName::String es::[Message]
{
  return if null(es)
         then []
         else [declaredName ++ "#" ++ head(es).pp] ++ rewriteMessages(declaredName, tail(es));
}

