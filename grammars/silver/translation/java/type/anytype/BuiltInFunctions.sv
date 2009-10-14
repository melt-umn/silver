grammar silver:translation:java:type:anytype;
import silver:translation:java:core;
import silver:translation:java:env;
import silver:definition:core;
import silver:definition:env;
import silver:definition:type:anytype;

-- The cast construct --
------------------------
aspect production cast_t
top::Expr ::= e::Expr tr::Decorated TypeRep
{
  top.translation = if e.typerep.isAnyType && tr.isAnyType
	            then "(" ++ e.translation ++ ")"
		    else if e.typerep.isAnyType
		    then "((" ++ tr.transType ++ ")" ++ e.translation ++ ".getData())"
		    else "(new common.AnyType(" ++ e.translation ++ "))";
}
