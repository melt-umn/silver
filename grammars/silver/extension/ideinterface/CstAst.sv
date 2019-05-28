grammar silver:extension:ideinterface;

import silver:reflect; -- for serialization
import silver:reflect:concretesyntax;

synthesized attribute serializedInterface :: String;
nonterminal IDEInterfaceSyntaxRoot with serializedInterface;
synthesized attribute ideSyntaxRoot :: IDEInterfaceSyntaxRoot occurs on SyntaxRoot;

abstract production ideSyntaxRoot
top::IDEInterfaceSyntaxRoot ::= s::IDEInterfaceSyntax
{
  top.serializedInterface = 
      case serialize(new(top)) of 
    | left(msg) -> error("Fatal internal error generating interface file: \n" ++ msg)
    | right(txt) -> txt
    end;
}

aspect production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax  terminalPrefixes::[Pair<String String>]
{
  -- use s2 the normalized syntax
  top.ideSyntaxRoot = ideSyntaxRoot(s2.ideSyntax);
}

aspect default production
top::SyntaxRoot ::=
{
  top.ideSyntaxRoot = error("Shouldn't happen");
}
