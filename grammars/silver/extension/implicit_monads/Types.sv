grammar silver:extension:implicit_monads;


terminal Explicit_t 'Explicit';
terminal Implicit_t 'Implicit';

terminal Tilde_t '~';


concrete production explicitTypeExpr
top::TypeExpr ::= '~' 'Explicit' t::TypeExpr
{
  top.unparse = "~Explicit " ++ t.unparse;

  top.typerep = explicitType(t.typerep);

  top.errors := t.errors;

  forwards to t;
}



abstract production explicitType
top::Type ::= t::Type
{
  top.typepp = "~Explicit "  ++ t.typepp;

  top.substituted = explicitType(t.substituted);
  top.flatRenamed = explicitType(t.flatRenamed);

  forwards to t;
}



concrete production implicitTypeExpr
top::TypeExpr ::= '~' 'Implicit' t::TypeExpr
{
  top.unparse = "~Implicit " ++ t.unparse;

  top.typerep = if isMonad(t.typerep)
                then implicitType(t.typerep)
                else errorType();

  local nonMonadError::Message =
      err(top.location,
          "Implicit can only be applied to monad types, not " ++ t.unparse);

  top.errors := if isMonad(t.typerep)
                then []
                else [nonMonadError];

  forwards to if isMonad(t.typerep)
              then t
              else errorTypeExpr(top.errors, location=top.location);
}


abstract production implicitType
top::Type ::= t::Type
{
  top.typepp = "~Implicit " ++ t.typepp;

  top.substituted = implicitType(t.substituted);
  top.flatRenamed = implicitType(t.flatRenamed);

  forwards to if isMonad(t)
              then t
              else errorType();
}

