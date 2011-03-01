grammar silver:modification:patternmatching:copper;

import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:modification:patternmatching;
import silver:translation:java:core;
import silver:definition:type;
import silver:translation:java:type;



aspect production patternMatchRuntimeIsProd
top::Expr ::= e::Expr t::String
{
  top.translation = "(" ++ e.translation ++ ".undecorate() instanceof " ++ makeClassName(t) ++ ")";
  top.isAppReference = false;
  top.appReference = error("pmrip demanded appreference");
}

aspect production patternMatchRuntimeGetChild
top::Expr ::= e::Expr c::Integer t::TypeExp
{
  top.translation =
       if t.isDecorable
       then {- type DecoratedNode -} e.translation ++ ".childDecorated(" ++ toString(c) ++ ")"
       else "((" ++ top.typerep.transType ++ ")" ++ e.translation ++ ".childAsIs(" ++ toString(c) ++ "))";
  top.isAppReference = false;
  top.appReference = error("pmrgc demanded appreference");
}
