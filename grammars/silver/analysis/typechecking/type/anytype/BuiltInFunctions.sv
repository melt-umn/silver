grammar silver:analysis:typechecking:type:anytype;
import silver:analysis:typechecking:core;
import silver:definition:core;
import silver:definition:env;
import silver:definition:type:anytype;
import silver:definition:type:io;

-- The cast construct --
------------------------
aspect production cast_t
top::Expr ::= e::Expr tr::Decorated TypeRep
{
  top.typeErrors = e.typeErrors ++
	     if tr.isAnyType &&
                (e.typerep.isProduction || e.typerep.isIO)
             then [err(top.location, "Cast Error: 1st argument cannot be a production or IO type.")]
             else
             if e.typerep.isAnyType &&
                (tr.isProduction || tr.isIO)
             then [err(top.location, "Cast Error: 2nd argument cannot be a production or IO type.")]
             else
             if e.typerep.isAnyType && tr.isAnyType
             then [err(top.location, "Cast Error: both arguments cannot be AnyType.")]
             else
             if ! (tr.isAnyType || e.typerep.isAnyType)
             then [err(top.location, "Cast Error: one of the type or expression must be AnyType.")]
             else [] ;
}
