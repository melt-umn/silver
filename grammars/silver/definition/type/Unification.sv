grammar silver:definition:type;

inherited attribute unifyWith :: TypeExp occurs on TypeExp;
synthesized attribute unify :: Substitution occurs on TypeExp;

--------------------------------------------------------------------------------
aspect production varTypeExp
top::TypeExp ::= tv::TyVar
{
  top.unify = case top.unifyWith of
               varTypeExp(j) -> if tyVarEqual(tv, j)
                                then emptySubst()
                                else subst( tv, top.unifyWith )
             | _ -> if containsTyVar(tv, top.unifyWith.freeVariables)
                    then errorSubst("Infinite type! Tried to unify with " ++ prettyType(top.unifyWith))
                    else subst(tv, top.unifyWith)
              end;
}

aspect production skolemTypeExp
top::TypeExp ::= tv::TyVar
{
  top.unify = case top.unifyWith of
               skolemTypeExp(otv) -> if tyVarEqual(tv, otv)
                                     then emptySubst()
                                     else errorSubst("Tried to unify skolem constant with incompatible skolem constant")
             | _ -> errorSubst("Tried to unify skolem constant with " ++ prettyType(top.unifyWith))
              end;
}

aspect production intTypeExp
top::TypeExp ::=
{
  top.unify = case top.unifyWith of
               intTypeExp() -> emptySubst()
             | _ -> errorSubst("Tried to unify Integer with " ++ prettyType(top.unifyWith))
              end;
}

aspect production boolTypeExp
top::TypeExp ::=
{
  top.unify = case top.unifyWith of
               boolTypeExp() -> emptySubst()
             | _ -> errorSubst("Tried to unify Boolean with " ++ prettyType(top.unifyWith))
              end;
}

aspect production floatTypeExp
top::TypeExp ::=
{
  top.unify = case top.unifyWith of
               floatTypeExp() -> emptySubst()
             | _ -> errorSubst("Tried to unify Float with " ++ prettyType(top.unifyWith))
              end;
}

aspect production stringTypeExp
top::TypeExp ::=
{
  top.unify = case top.unifyWith of
               stringTypeExp() -> emptySubst()
             | _ -> errorSubst("Tried to unify Boolean with " ++ prettyType(top.unifyWith))
              end;
}

aspect production nonterminalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  top.unify = case top.unifyWith of
               nonterminalTypeExp(ofn, op) -> if fn == ofn
                                            then unifyAll( params, op )
                                            else errorSubst("Tried to unify conflicting nonterminal types " ++ fn ++ " and " ++ ofn)
             | ntOrDecTypeExp(_,_) -> errorSubst("nte-nodte: try again")
             | _ -> errorSubst("Tried to unify nonterminal type " ++ fn ++ " with " ++ prettyType(top.unifyWith))
              end;
}

aspect production terminalTypeExp
top::TypeExp ::= fn::String
{
  top.unify = case top.unifyWith of
               terminalTypeExp(ofn) -> if fn == ofn
                                     then emptySubst()
                                     else errorSubst("Tried to unify conflicting terminal types " ++ fn ++ " and " ++ ofn)
             | _ -> errorSubst("Tried to unify terminal type " ++ fn ++ " with " ++ prettyType(top.unifyWith))
              end;
}

aspect production decoratedTypeExp
top::TypeExp ::= te::TypeExp
{
  top.unify = case top.unifyWith of
               decoratedTypeExp(ote) -> unify(te, ote)
             | ntOrDecTypeExp(_,_) -> errorSubst("dte-nodte: try again")
             | _ -> errorSubst("Tried to unify decorated type with " ++ prettyType(top.unifyWith))
              end;
}

aspect production ntOrDecTypeExp
top::TypeExp ::= nt::TypeExp  hidden::TypeExp
{
  -- If were being asked to unify, then we know hidden is still a type variable,
  -- since we shouldn't be unifying with anything but fully-substituted types.
  -- And we kill off this type once hidden is specialized.
  top.unify =
             case top.unifyWith of
               decoratedTypeExp(ote) ->
                      -- Ensure compatibility between Decorated nonterminal types, then specialize ourselves
                      unifyAllShortCircuit([ote, top.unifyWith],
                                           [nt,  hidden])
             | nonterminalTypeExp(_, _) ->
                      -- Ensure compatibility between nonterminal types, then specialize ourselves
                      unifyAllShortCircuit([top.unifyWith, top.unifyWith],
                                           [nt,            hidden])
             | ntOrDecTypeExp(ont1, ohidden1) ->
                      -- Ensure compatibility between nonterminal types, then merge our specializations
                      unifyAllShortCircuit([ont1, ohidden1],
                                           [nt,   hidden])
             | _ -> errorSubst("Tried to unify decorated type with " ++ prettyType(top.unifyWith))
              end;
}

aspect production functionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp] namedParams::[NamedArgType]
{
  top.unify = case top.unifyWith of
               functionTypeExp(oo, op, onp) -> unifyFunctions(out :: params, oo :: op, namedParams, onp)
             | _ -> errorSubst("Tried to unify function type with " ++ prettyType(top.unifyWith))
              end;
}

--------------------------------------------------------------------------------

function unify
Substitution ::= te1::TypeExp te2::TypeExp
{
  local attribute leftward :: Substitution;
  leftward = te1.unify;
  te1.unifyWith = te2;
  
  local attribute rightward :: Substitution;
  rightward = te2.unify;
  te2.unifyWith = te1;
  
  return if null(leftward.substErrors)
         then leftward   -- arbitrary choice if both work, but if they are confluent, it's okay
         else rightward; -- arbitrary choice of errors. Non-confluent!!
}

function unifyCheck
Substitution ::= te1::TypeExp te2::TypeExp s::Substitution
{
  return composeSubst( ignoreFailure(s), unify( performSubstitution(te1, s), performSubstitution(te2, s)));
}

function unifyDirectional
Substitution ::= fromte::TypeExp tote::TypeExp
{
  -- Currently, this is built on the assumption that the unification will not fail.
  -- Therefore, for now we will FRAGILEY just call unify 
  -- This is a possible source of bugs/unexpected behavior?
  return unify(fromte, tote);
}

function unifyAll
Substitution ::= te1::[TypeExp] te2::[TypeExp]
{
  local first :: Substitution = unify(head(te1), head(te2));
  
  return if null(te1) && null(te2)
         then emptySubst()
         else if null(te1) || null(te2)
         then errorSubst("Internal error: unifying mismatching numbers")
         else composeSubst(first, unifyAll( mapSubst(tail(te1), first),
                                            mapSubst(tail(te2), first) ));
}

function unifyAllShortCircuit
Substitution ::= te1::[TypeExp] te2::[TypeExp]
{
  local first :: Substitution = unify(head(te1), head(te2));
  
  return if null(te1) && null(te2)
         then emptySubst()
         else if null(te1) || null(te2)
         then errorSubst("Internal error: unifying mismatching numbers")
         else if first.failure
         then first -- terminate recursion!
         else composeSubst(first, unifyAllShortCircuit( mapSubst(tail(te1), first),
                                                        mapSubst(tail(te2), first) ));
}

function unifyAllNamed
Substitution ::= te1::[NamedArgType]  te2::[NamedArgType]
{
  local first :: Substitution = unify(head(te1).argType, head(te2).argType);
  
  return if null(te1) && null(te2)
         then emptySubst()
         else if null(te1) || null(te2)
         then errorSubst("Internal error: unifying mismatching numbers")
         else if head(te1).argName != head(te2).argName -- additionally check names
         then errorSubst("Mismatching named parameters")
         else composeSubst(first, unifyAllNamed( mapNamedSubst(tail(te1), first),
                                                 mapNamedSubst(tail(te2), first) ));  
}

function unifyFunctions
Substitution ::= te1::[TypeExp]  te2::[TypeExp]  n1::[NamedArgType]  n2::[NamedArgType]
{
  local first :: Substitution = unifyAll(te1, te2);
  local second :: Substitution = unifyAllNamed(mapNamedSubst(n1, first), mapNamedSubst(n2, first));
  
  return composeSubst(first, second);
}

