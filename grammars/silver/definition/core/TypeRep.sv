grammar silver:definition:core;
import silver:definition:env;

synthesized attribute doDecorate :: Boolean;
synthesized attribute applicationDispatcher :: Production (Expr ::= Decorated Expr Exprs);
synthesized attribute accessDispatcher :: Production (Expr ::= Decorated Expr Dot_t Decorated QName);

attribute doDecorate, applicationDispatcher, accessDispatcher occurs on TypeRep;

aspect production i_prodTypeRep
top::TypeRep ::= it::[Decorated TypeRep] ot::Decorated TypeRep
{
  top.applicationDispatcher = productionApplicationDispatcher;
}

aspect production i_funTypeRep
top::TypeRep ::= it::[Decorated TypeRep] ot::Decorated TypeRep
{
  top.applicationDispatcher = functionApplicationDispatcher;
}

aspect production i_ntTypeRep
top::TypeRep ::= n::String
{
  top.doDecorate = true;
  top.accessDispatcher = undecoratedAccessDispatcher;
}

aspect production i_termTypeRep
top::TypeRep ::= n::String
{
  top.accessDispatcher = terminalAccessDispatcher;
}

aspect production i_refTypeRep
top::TypeRep ::= t::Decorated TypeRep
{
  top.accessDispatcher = decoratedAccessDispatcher;
}

aspect production i_defaultTypeRep
top::TypeRep ::= 
{
  top.doDecorate = false;
  top.applicationDispatcher = errorApplicationDispatcher;
  top.accessDispatcher = errorAccessDispatcher;
}

