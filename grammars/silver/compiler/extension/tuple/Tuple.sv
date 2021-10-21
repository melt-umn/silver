-- Note: We consider only tuples containing two or more elements
--       The empty tuple () forwards to unit

grammar silver:compiler:extension:tuple;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:definition:type;

imports silver:compiler:extension:patternmatching;

terminal IntConst /[0-9]+/;

nonterminal TupleList with location, unparse, translation;

-- used to convert the comma-separated list of expressions 
-- that make up the tuple into a pair expression:
synthesized attribute translation :: Expr;

concrete production emptyTuple
top::Expr ::= '(' ')'
{
  top.unparse = "()";
  top.typerep = tupleType([]);
  forwards to Silver_Expr { silver:core:unit() };
}

concrete production tupleExpr
top::Expr ::= '(' tl::TupleList ')'
{
  top.unparse = "(" ++ tl.unparse ++ ")";

  -- computing specialized tupleType from forward.typerep 
  -- is cleaner than performing type checking on TupleList
  -- (performSubstitution is needed because forward.typerep 
  -- isn't instantiated into a chain of pairs until upSubst is applied) 
  top.typerep = tupleType(performSubstitution(forward.typerep, forward.upSubst).tupleElems);
  
  forwards to tl.translation;
}

-- selects tuple element at index a
concrete production selector
top::Expr ::= tuple::Expr '.' a::IntConst
{

  -- Forward gets the substitution context of the tuple
  propagate downSubst, upSubst, freeVars;

  local accessIndex::Integer = toInteger(a.lexeme);

  top.unparse = tuple.unparse ++ "." ++ a.lexeme;
  top.errors <- tuple.errors;

  -- If tuple is decorated, the length of its tupleElems 
  -- will be 1, so we must pattern match to get at the 
  -- underlying tupleType and compute correct length of 
  -- its tupleElems
  local ty :: Type = performSubstitution(tuple.typerep, tuple.upSubst);
  local len::Integer = case ty of
    | decoratedType(t, _) -> length(t.tupleElems)
    | t -> length(t.tupleElems)
    end;
  
  forwards to if (accessIndex > len || accessIndex < 1) then
      errorExpr([err(top.location, "Invalid tuple selector index.")], location=top.location)
    -- exprRef prevents exponential type checking
    else select(exprRef(tuple, location=top.location), 1, accessIndex, len);

}

function select
-- i is the current index, a is the desired access index
-- len is the total length of the tuple
Expr ::= exp::Expr i::Integer a::Integer len::Integer
 {
  return 
    if i == a then
      if a == len then
        -- only if the access index is the length of the
        -- tuple do we simply return the expression itself
        Silver_Expr { $Expr{exp} } 
      else Silver_Expr { $Expr{exp}.fst }
    else select(Silver_Expr{ $Expr{exp}.snd }, i + 1, a, len);
}

-- TupleList cases:
-- There are two elements in the tuple
concrete production tupleList_2Elements
top::TupleList ::= fst::Expr ',' snd::Expr
{
  top.unparse = fst.unparse ++ ", " ++ snd.unparse;
  top.translation = Silver_Expr { silver:core:pair(fst=$Expr{fst}, snd=$Expr{snd}) };
}

-- There are more than two elements in the tuple
concrete production tupleList_nElements
top::TupleList ::= fst::Expr ',' snd::TupleList
{
  top.unparse = fst.unparse ++ ", " ++ snd.unparse;
  top.translation = Silver_Expr { silver:core:pair(fst=$Expr{fst}, snd=$Expr{snd.translation}) };
}
